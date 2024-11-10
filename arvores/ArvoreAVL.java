package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST{
    public ArvoreAVL(){
        setRoot(null);
        setNumBuscas(0);
        setNumInsercoes(0);
        setNumRemocoes(0);
    }

    @Override
    public void inserir(No no){
        if (getRoot() == null)  setRoot(no);
        else setNumInsercoes(getNumInsercoes()+inserir(getRoot(), no, 1));
    }

    private int inserir(No raiz, No no, int comparacoes){
        comparacoes++;

        if (no.getNomeEscola().compareTo(raiz.getNomeEscola()) <= 0){
            if (raiz.getEsquerda() == null){
                raiz.setEsquerda(no);
                no.setPai(raiz);
                return comparacoes;
            }else inserir(raiz.getEsquerda(), no, comparacoes);
        }else{
            if (raiz.getDireita() == null){
                raiz.setDireita(no);
                no.setPai(raiz);
                return comparacoes;
            }else inserir(raiz.getDireita(), no, comparacoes);
        }
        balancear(raiz);
        return comparacoes;
    }

    @Override
    public void remover(String nome){
        setNumRemocoes(remover(getRoot(), nome, 1));
    }

    private int remover(No raiz, String nome, int comparacoes){
        if (raiz == null) return comparacoes;

        comparacoes++;

        if (nome.compareTo(raiz.getNomeEscola()) < 0) return remover(raiz.getEsquerda(), nome, comparacoes);
        else if (nome.compareTo(raiz.getNomeEscola()) > 0) return remover(raiz.getDireita(), nome, comparacoes);
        else{
            if (raiz.getEsquerda() == null && raiz.getDireita() == null){
                if (raiz.getPai() != null){
                    if (raiz == raiz.getPai().getEsquerda()) raiz.getPai().setEsquerda(null);
                    else raiz.getPai().setDireita(null);
                }else setRoot(null);
            }

            else if (raiz.getEsquerda() == null){
                if (raiz.getPai() != null){
                    if (raiz == raiz.getPai().getEsquerda()) raiz.getPai().setEsquerda(raiz.getDireita());
                    else raiz.getPai().setDireita(raiz.getDireita());
                    raiz.getDireita().setPai(raiz.getPai());
                }else{
                    setRoot(raiz.getDireita());
                    raiz.setPai(null);
                }
            }
            
            else if (raiz.getDireita() == null){
                if (raiz.getPai() != null){
                    if (raiz == raiz.getPai().getEsquerda()) raiz.getPai().setEsquerda(raiz.getEsquerda());
                    else raiz.getPai().setDireita(raiz.getEsquerda());
                    raiz.getEsquerda().setPai(raiz.getPai());
                }else{
                    setRoot(raiz.getEsquerda());
                    raiz.setPai(null);
                }
            }

            else{
                No aux = menorMaior(raiz.getDireita());
                remover(raiz.getDireita(), aux.getNomeEscola(), comparacoes);
                raiz.copiarValores(aux);
            }
        }
        balancear(raiz);
        return comparacoes;
    }

    private void balancear(No no){
        int fb = fatorBalanceamento(no);

        if (fb > 1){
            if (fatorBalanceamento(no.getEsquerda()) < 0) rotacionarEsquerda(no.getEsquerda());
            rotacionarDireita(no);
        }else if (fb < -1){
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
