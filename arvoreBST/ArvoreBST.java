package arvoreBST;

public class ArvoreBST{
    private No raiz;

    public void inserir(int elemento){
        No novoNo = new No();
        novoNo.valor = elemento;
        if(this.raiz == null) raiz = novoNo;
        else{
            No atual = raiz;
            No pai;
            while(true){
                pai = atual;
                if(elemento <= atual.valor){
                    atual = atual.esquerda;
                    if(atual == null){
                        pai.esquerda = novoNo;
                        novoNo.pai = pai;
                        return;
                    }
                }
                else{
                    atual = atual.direita;
                    if(atual == null){
                        pai.direita = novoNo;
                        novoNo.pai = pai;
                        return;
                    }
                }
            }
        }
    }

        public boolean remover(int elemento){
            if(this.raiz == null) return false;
            else{
                No atual = raiz;
                No pai;
                while(true){
                    pai = atual.pai;
                    if (elemento == atual.valor){
                        if (atual.esquerda == null && atual.direita == null){
                            if(pai.esquerda == atual) pai.esquerda = null;
                            else pai.direita = null;
                        }
                        else if (atual.direita == null){
                            if(pai.esquerda == atual)pai.esquerda = atual.esquerda;
                            else pai.direita = atual.esquerda;
                            atual.esquerda.pai = pai;
                        }
                        else if (atual.esquerda == null){
                            if(pai.esquerda == atual) pai.esquerda = atual.direita;
                            else pai.direita = atual.direita;
                            atual.direita.pai = pai;
                        }
                        else{
                            No menorMaior = atual.direita;
                            while (true){
                                if (menorMaior.esquerda != null) menorMaior = menorMaior.esquerda;
                                else break;
                            }
                            atual.valor = menorMaior.valor;
                            if (menorMaior.direita != null){
                                menorMaior.pai.esquerda = menorMaior.direita;
                                menorMaior.direita.pai = menorMaior.pai;
                                menorMaior.direita = null;
                            }
                            else menorMaior.pai.esquerda = null;
                            menorMaior.pai = null;
                        }
                        atual.pai = null;
                        return true;
                    }
                    else if(elemento < atual.valor){
                        atual = atual.esquerda;
                        if(atual == null) return false;
                    }
                    else{
                        atual = atual.direita;
                        if(atual == null) return false;
                    }
                }
            }
        }

        public No buscar(int elemento){
            No atual = raiz;
            while(atual != null){
                if (atual.valor == elemento) return atual;
                if (atual.valor < elemento) atual = atual.direita;
                else atual = atual.esquerda;
            }
            return null;
        }

    public void emOrdem(){
        visitarEmOrdem(raiz);
    }

    private void visitarEmOrdem(No no){
        if(no != null){
            visitarEmOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            visitarEmOrdem(no.direita);
        }
    }

    public void preOrdem(){
        visitarPreOrdem(raiz);
    }

    private void visitarPreOrdem(No no){
        if(no != null){
            System.out.print(no.valor + " ");
            visitarPreOrdem(no.esquerda);
            visitarPreOrdem(no.direita);
        }
    }

    public void posOrdem(){
        visitarPosOrdem(raiz);
    }

     private void visitarPosOrdem(No no){
        if(no != null){
            visitarPosOrdem(no.esquerda);
            visitarPosOrdem(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    public void emLargura(){
        int alt = altura(raiz);
        for (int i = 0; i < alt; i++){
            visitarEmLargura(raiz, i);
        }
    }

    private int altura(No no){
        if (no == null){
            return 0;
        }
        int alturaEsquerda = altura(no.esquerda);
        int alturaDireita = altura(no.direita);
        return alturaEsquerda > alturaDireita ? alturaEsquerda+1 : alturaDireita+1;
    }

    private void visitarEmLargura(No no, int nivel){
        if (no == null){
            return;
        }
        if (nivel == 0){
            System.out.print(no.valor + " ");
        }else if (nivel > 0) {
            visitarEmLargura(no.esquerda, nivel-1);
            visitarEmLargura(no.direita, nivel-1);
        }
    }
}