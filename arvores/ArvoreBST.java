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
        return raiz;
    }

    public boolean isEmpty(){
        return raiz == null;
    }

    public void inserir(int valor){
        No no = new No(valor);

        if (raiz == null){
            raiz = no;
            return;
        }

        inserir(getRoot(), no);
    }

    private void inserir(No raiz, No no){
        if (no.getValor() <= raiz.getValor()){
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

    public void remover(int valor){
        remover(getRoot(), new No(valor));
    }

    private void remover(No raiz, No no){
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
        
    }
    
    private No menorMaior(No no){
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    public No buscar(int valor){
        return buscar(getRoot(), new No(valor));
    }

    private No buscar(No raiz, No no){
        if (raiz == null) return null;
        else if (raiz.getValor() == no.getValor()) return raiz;
        else if (no.getValor() <= raiz.getValor()) return buscar(raiz.getEsquerda(), no);
        return buscar(raiz.getDireita(), no);
    }

    public int altura(){
        return altura(raiz);
    }

    private int altura(No no){
        if (no == null) return -1;
        else if (no.esquerda == null && no.direita == null) return 0;
        else{
            int esquerda = altura(no.esquerda);
            int direita = altura(no.direita);
            return esquerda > direita ? esquerda + 1 : direita + 1;
        }
    }

   public boolean ehBinaria(){
        return ehBinaria(raiz);
   }

   private boolean ehBinaria(No no){
        if (no == null) return true;
        else if(no.esquerda == null && no.direita == null) return true;
        else if ((no.esquerda != null && no.esquerda.valor > no.valor) || (no. direita != null && no.direita.valor < no.valor)) return false;
        return ehBinaria(no.esquerda) && ehBinaria(no.direita);
   }

    public void printTree(){
        if (isEmpty()){
            System.out.print("Árvore vazia.\n");
        }else{
            printTree(raiz, 0);
        }
    }

    private void printTree(No no, int level){
        if (no != null){
            printTree(no.direita, level + 1);
            for (int i = 0; i < level; i++){
                System.out.print("    ");
            }
            System.out.println(no.valor);
            printTree(no.esquerda, level + 1);
        }
    }
}