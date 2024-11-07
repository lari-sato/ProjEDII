package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST{
    public ArvoreAVL(){
        setRoot(null);
    }

    public boolean ehAVL() {
        return ehAVL(getRoot());
    }

    private boolean ehAVL(No no) {
        if (no == null) return true;

        int alturaEsquerda = altura(no.esquerda);
        int alturaDireita = altura(no.direita);

        int fatorBalanceamento = Math.abs(alturaEsquerda - alturaDireita);

        if (fatorBalanceamento <= 1 && ehAVL(no.esquerda) && ehAVL(no.direita)) return true;

        return false;
    }
}
