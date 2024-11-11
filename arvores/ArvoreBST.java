package ProjEDII.arvores;

public class ArvoreBST{
    private No raiz = null;
    private int numInsercoes;
    private int numRemocoes;
    private int numBuscas;

    public ArvoreBST(){
        this.raiz = null;
        this.numInsercoes = 0;
        this.numRemocoes = 0;
        this.numBuscas = 0;
    }

    public No getRaiz(){
        return this.raiz;
    }

    public int getNumInsercoes(){
        return this.numInsercoes;
    }

    public int getNumRemocoes(){
        return this.numRemocoes;
    }

    public int getNumBuscas(){
        return this.numBuscas;
    }

    public void setRaiz(No no){
        this.raiz = no;
    }

    public void setNumInsercoes(int numInsercoes){
        this.numInsercoes = numInsercoes;
    }

    public void setNumRemocoes(int numRemocoes){
        this.numRemocoes = numRemocoes;
    }

    public void setNumBuscas(int numBuscas){
        this.numBuscas = numBuscas;
    }

    public boolean isEmpty(){
        return getRaiz() == null;
    }

    public void inserir(No no){
        int comparacoes = 1;
        if (getRaiz() == null) setRaiz(no);
        else{
            No atual = getRaiz();
            No pai = null;

            while(atual != null){
                pai = atual;
                comparacoes++;
                if(no.getNomeEscola().compareTo(atual.getNomeEscola()) <= 0) atual = atual.getEsquerda();
                else atual = atual.getDireita();
            }

            if(no.getNomeEscola().compareTo(pai.getNomeEscola()) <= 0){
                pai.setEsquerda(no);
                no.setPai(pai);
            }else{
                pai.setDireita(no);
                no.setPai(pai);
            }
        }
        setNumInsercoes(getNumInsercoes()+comparacoes);
    }

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
                    getRaiz().setPai(null);
                    return true;
                }
                else if(atual.getPai().getEsquerda() == atual) atual.getPai().setEsquerda(atual.getEsquerda());
                else pai.setDireita(atual.getEsquerda());
                atual.getEsquerda().setPai(atual.getPai());
            }

            else if (atual.getEsquerda() == null){
                if(atual == getRaiz()){
                    setRaiz(atual.getDireita());
                    getRaiz().setPai(null);
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
            return true;
        }
    }
    
    protected No menorMaior(No no){
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    public No buscar(String nome){
        int[] comparacoes ={1};
        No aux = buscar(getRaiz(), nome, comparacoes);
        setNumBuscas(comparacoes[0]);
        return aux;
    }

    private No buscar(No raiz, String nome, int[] comparacoes){
        if (raiz == null) return null;
        comparacoes[0]++;
        if (nome.compareTo(raiz.getNomeEscola()) == 0) return raiz;
        else if (nome.compareTo(raiz.getNomeEscola()) < 0) return buscar(raiz.getEsquerda(), nome, comparacoes);
        return buscar(raiz.getDireita(), nome, comparacoes);
    }

    protected int altura(No no){
        if (no == null) return 0;

        int esquerda = 0;
        int direita = 0;

        No atual = no;
        while (atual != null){
            esquerda++;
            atual = atual.getEsquerda();
        }

        atual = no;
        while (atual != null){
            direita++;
            atual = atual.getDireita();
        }

        return esquerda > direita ? esquerda : direita;
    }

    public void printTree(){
        if (isEmpty()) System.out.print("Árvore vazia.\n");
        else printTree(raiz, 0);
    }

    private void printTree(No no, int nivel){
        if (no != null){
            printTree(no.getDireita(), nivel+1);
            
            for (int i = 0; i < nivel; i++){
                System.out.print("        ");
            }

            System.out.println(no.getNomeEscola());
            printTree(no.getEsquerda(), nivel+1);
        }
    }
}