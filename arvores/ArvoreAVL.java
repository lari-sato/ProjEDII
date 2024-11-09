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

        int alturaEsquerda = altura(no.getEsquerda());
        int alturaDireita = altura(no.getDireita());

        int fatorBalanceamento = Math.abs(alturaEsquerda - alturaDireita);

        if (fatorBalanceamento <= 1 && ehAVL(no.getEsquerda()) && ehAVL(no.getDireita())) return true;

        return false;
    }
}
