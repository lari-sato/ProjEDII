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

    public No getRoot(){
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

    public void setRoot(No no){
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
        return getRoot() == null;
    }

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
            }else return inserir(raiz.getEsquerda(), no, comparacoes);
        }else{
            if (raiz.getDireita() == null){
                raiz.setDireita(no);
                no.setPai(raiz);
                return comparacoes;
            }else return inserir(raiz.getDireita(), no, comparacoes);
        }
    }

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
        return comparacoes;
    }
    
    protected No menorMaior(No no){
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    public No buscar(String nome){
        int[] comparacoes ={1};
        No aux = buscar(getRoot(), nome, comparacoes);
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

    public int altura(){
        return altura(getRoot());
    }

    protected int altura(No no){
        if (no == null) return -1;
        else if (no.getEsquerda() == null && no.getDireita() == null) return 0;
        else{
            int esquerda = altura(no.getEsquerda());
            int direita = altura(no.getDireita());
            return esquerda > direita ? esquerda+1 : direita+1;
        }
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