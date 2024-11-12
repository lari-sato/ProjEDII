package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST{
    public ArvoreAVL(){
        setRaiz(null);
        setNumBuscas(0);
        setNumInsercoes(0);
        setNumRemocoes(0);
    }

    @Override
public void inserir(No no){
    int comparacoes = 1;
    if (getRaiz() == null){
        setRaiz(no);
        return;
    }

    No atual = getRaiz();
    No pai = null;

    while (atual != null){
        pai = atual;
        comparacoes++;

        if (no.getNomeEscola().compareTo(atual.getNomeEscola()) < 0) atual = atual.getEsquerda();
        else if (no.getNomeEscola().compareTo(atual.getNomeEscola()) > 0) atual = atual.getDireita();
        else return;
    }

    if (no.getNomeEscola().compareTo(pai.getNomeEscola()) < 0) pai.setEsquerda(no);
    else pai.setDireita(no);

    setNumInsercoes(getNumInsercoes()+comparacoes);

    balancear(pai);
}

    @Override
    public boolean remover(String nome){
        int comparacoes = 1;
        if(getRaiz() == null) return false;
        else{
            No atual = getRaiz();
            No pai = null;

            while (atual != null && atual.getNomeEscola() != nome){
                pai = atual;
                comparacoes++;
                if (nome.compareTo(atual.getNomeEscola()) < 0) atual = atual.getEsquerda();
                else atual = atual.getDireita();
            }

            if (atual == null) return false;

            if (atual.getEsquerda() == null && atual.getDireita() == null){
                if(atual == getRaiz()) setRaiz(null);
                else if(atual.getPai().getEsquerda() == atual) atual.getPai().setEsquerda(null);
                else atual.getPai().setDireita(null);
            }

            else if (atual.getDireita() == null){
                if(atual == getRaiz()){
                    setRaiz(atual.getEsquerda());
                    return true;
                }
                else if(atual.getPai().getEsquerda() == atual) atual.getPai().setEsquerda(atual.getEsquerda());
                else pai.setDireita(atual.getEsquerda());
                atual.getEsquerda().setPai(atual.getPai());
            }

            else if (atual.getEsquerda() == null){
                if(atual == getRaiz()){
                    setRaiz(atual.getDireita());
                    return true;
                }
                else if(atual.getPai().getEsquerda() == atual) atual.getPai().setEsquerda(atual.getDireita());
                else pai.setDireita(atual.getDireita());
                atual.getDireita().setPai(atual.getPai());
            }

            else{
                No menorMaior = menorMaior(atual.getDireita());
                atual.copiarValores(menorMaior);

                remover(menorMaior.getNomeEscola());
            }
            setNumRemocoes(comparacoes);
            atual = atual.getPai();
            while (atual != null) {
                atual = balancear(atual);
                atual = atual.getPai();
            }
            return true;
        }
    }

    private No balancear(No no){
        if (no == null) return null;
        
        int fb = fatorBalanceamento(no);

        if (fb > 1){
            if (fatorBalanceamento(no.getEsquerda()) < 0) no.setEsquerda(rotacionarEsquerda(no.getEsquerda()));
            return rotacionarDireita(no);
        }else if (fb < -1){
            if (fatorBalanceamento(no.getDireita()) > 0) no.setDireita(rotacionarDireita(no.getDireita()));
            return rotacionarEsquerda(no);
        }
        if (no.getPai() == null) setRaiz(no);
        return no;
    }

    private No rotacionarDireita(No no){
        No aux = no.getEsquerda();
        no.setEsquerda(aux.getDireita());
        if (aux.getDireita() != null) aux.getDireita().setPai(no);
        aux.setDireita(no);
        aux.setPai(no.getPai());

        if (no.getPai() != null){
            if (no.getPai().getEsquerda() == no) no.getPai().setEsquerda(aux);
            else no.getPai().setDireita(aux);
        }else setRaiz(aux);

        no.setPai(aux);

        return aux;
    }

    private No rotacionarEsquerda(No no){
        No aux = no.getDireita();
        no.setDireita(aux.getEsquerda());
        if (aux.getEsquerda() != null) aux.getEsquerda().setPai(no);
        aux.setEsquerda(no);
        aux.setPai(no.getPai());

        if (no.getPai() != null){
            if (no.getPai().getEsquerda() == no) no.getPai().setEsquerda(aux);
            else no.getPai().setDireita(aux);
        }else setRaiz(aux);

        no.setPai(aux);

        return aux;
    }
    
    private int fatorBalanceamento(No no){
        return no == null ? 0 : altura(no.getEsquerda()) - altura(no.getDireita());
    }
}
