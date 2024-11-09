package ProjEDII.arvores;

public class ArvoreBST{
    private No raiz = null;

    public ArvoreBST(){
        this.raiz = null;
    }

    public void setRoot(No no){
        this.raiz = no;
    }

    public No getRoot(){
        return this.raiz;
    }

    public boolean isEmpty(){
        return getRoot() == null;
    }

    public void inserir(No no){
        if (getRoot() == null) setRoot(no);
        else inserir(getRoot(), no);
    }

    private void inserir(No raiz, No no){
        if (no.getNomeEscola().compareTo(raiz.getNomeEscola()) <= 0){
            if (raiz.getEsquerda() == null){
                raiz.setEsquerda(no);
                no.setPai(raiz);
            }else inserir(raiz.getEsquerda(), no);
        }else{
            if (raiz.getDireita() == null){
                raiz.setDireita(no);
                no.setPai(raiz);
            }else inserir(raiz.getDireita(), no);
        }
    }

    public void remover(String nome){
        remover(getRoot(), nome);
    }

    private void remover(No raiz, String nome){
        if (raiz == null) return;
        if (nome.compareTo(raiz.getNomeEscola()) < 0) remover(raiz.getEsquerda(), nome);
        else if (nome.compareTo(raiz.getNomeEscola()) > 0) remover(raiz.getDireita(), nome);
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
                remover(raiz.getDireita(), aux.getNomeEscola());
                raiz.copiarValores(aux);
            }
        }
        
    }
    
    protected No menorMaior(No no){
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    public No buscar(String nome){
        return buscar(getRoot(), nome);
    }

    private No buscar(No raiz, String nome){
        if (raiz == null) return null;
        else if (nome.compareTo(raiz.getNomeEscola()) == 0) return raiz;
        else if (nome.compareTo(raiz.getNomeEscola()) <= 0) return buscar(raiz.getEsquerda(), nome);
        return buscar(raiz.getDireita(), nome);
    }

    public int altura(){
        return altura(raiz);
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