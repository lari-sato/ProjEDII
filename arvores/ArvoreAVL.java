package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST{

    public ArvoreAVL(){
        setRoot(null);
    }

    @Override
    public void inserir(int valor){
        No no = new No(valor);
        if (getRoot() == null) setRoot(no);
        else inserir(getRoot(), no);
    }

    private void inserir(No raiz, No no){
        if (no.getValor() <= raiz.getValor()){
            if (raiz.getEsquerda() == null){
                raiz.setEsquerda(no);
                no.setPai(raiz);
            }else inserir(raiz.getEsquerda(), no);
        } else{
            if (raiz.getDireita() == null){
                raiz.setDireita(no);
                no.setPai(raiz);
            } else inserir(raiz.getDireita(), no);
        }
        balancear(raiz);
    }

    @Override
    public void remover(int valor) {
        remover(getRoot(), new No(valor));
    }

    private void remover(No raiz, No no) {
        if (raiz == null) return;
        if (no.getValor() < raiz.getValor()) remover(raiz.getEsquerda(), no);
        else if (no.getValor() > raiz.getValor()) remover(raiz.getDireita(), no);
        else{
            if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
                if (raiz.getPai() != null){
                    if (raiz == raiz.getPai().getEsquerda()) raiz.getPai().setEsquerda(null);
                    else raiz.getPai().setDireita(null);
                } else setRoot(null);
            }

            else if (raiz.getEsquerda() == null){
                if (raiz.getPai() != null){
                    if (raiz == raiz.getPai().getEsquerda()) raiz.getPai().setEsquerda(raiz.getDireita());
                    else raiz.getPai().setDireita(raiz.getDireita());
                    raiz.getDireita().setPai(raiz.getPai());
                } else{
                    setRoot(raiz.getDireita());
                    raiz.setPai(null);
                }
            }
            
            else if (raiz.getDireita() == null){
                if (raiz.getPai() != null) {
                    if (raiz == raiz.getPai().getEsquerda()) raiz.getPai().setEsquerda(raiz.getEsquerda());
                    else raiz.getPai().setDireita(raiz.getEsquerda());
                    raiz.getEsquerda().setPai(raiz.getPai());
                } else{
                    setRoot(raiz.getEsquerda());
                    raiz.setPai(null);
                }
            }

            else{
                No aux = menorMaior(raiz.getDireita());
                remover(raiz.getDireita(), aux);
                raiz.setValor(aux.getValor());
            }
        }
        balancear(raiz);
    }

    private void balancear(No no){
        int fb = fatorBalanceamento(no);

        if (fb > 1){
            if (fatorBalanceamento(no.getEsquerda()) < 0) rotacionarEsquerda(no.getEsquerda());
            rotacionarDireita(no);
        } else if (fb < -1){
            if (fatorBalanceamento(no.getDireita()) > 0) rotacionarDireita(no.getDireita());
            rotacionarEsquerda(no);
        }
        if (no.getPai() == null) setRoot(no);
    }

    private void rotacionarDireita(No no){
        No aux = no.getEsquerda();
        no.setEsquerda(aux.getDireita());
        if (aux.getDireita() != null) aux.getDireita().setPai(no);
        aux.setDireita(no);
        aux.setPai(no.getPai());

        if (no.getPai() != null){
            if (no.getPai().getEsquerda() == no) no.getPai().setEsquerda(aux);
            else no.getPai().setDireita(aux);
        }else setRoot(aux);

        no.setPai(aux);
    }

    private void rotacionarEsquerda(No no){
        No aux = no.getDireita();
        no.setDireita(aux.getEsquerda());
        if (aux.getEsquerda() != null) aux.getEsquerda().setPai(no);
        aux.setEsquerda(no);
        aux.setPai(no.getPai());

        if (no.getPai() != null){
            if (no.getPai().getEsquerda() == no) no.getPai().setEsquerda(aux);
            else no.getPai().setDireita(aux);
        }else setRoot(aux);

        no.setPai(aux);
    }
    
    private int fatorBalanceamento(No no){
        return no == null ? 0 : altura(no.getEsquerda()) - altura(no.getDireita());
    }
}
