package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST{
    public ArvoreAVL(){
        setRaiz(null);
        setNumBuscas(0);
        setNumInsercoes(0);
        setNumRemocoes(0);
    }

    public int maior(int altura1, int altura2){
        return (altura1 > altura2) ? altura1 : altura2;
    }

    @Override
    public void inserir(No no){
        int[] comparacoes = {1};
        setRaiz(inserir(getRaiz(), no, comparacoes));
        setNumInsercoes(getNumInsercoes()+comparacoes[0]);
    }
    
    private No inserir(No raiz, No no, int[] comparacoes){
        if (raiz == null) return no;
        comparacoes[0]++;
    
        if (no.getNomeEscola().compareTo(raiz.getNomeEscola()) <= 0){
            raiz.setEsquerda(inserir(raiz.getEsquerda(), no, comparacoes));
            raiz = balancear(raiz, no);
        } else {
            raiz.setDireita(inserir(raiz.getDireita(), no, comparacoes));
            raiz = balancear(raiz, no);
        }
    
        return raiz;
    }
    
    private No balancear(No raiz, No no){
        if ((altura(raiz.getEsquerda()) - altura(raiz.getDireita())) > 1){
            if (no.getNomeEscola().compareTo(raiz.getEsquerda().getNomeEscola()) <= 0) raiz = rotacionarDireita(raiz);
            else raiz = rotacionarEsquerdaDireita(no);
        }
        else if ((altura(raiz.getEsquerda()) - altura(raiz.getDireita())) < -1){
            if (no.getNomeEscola().compareTo(raiz.getDireita().getNomeEscola()) > 0) raiz = rotacionarEsquerda(raiz);
            else raiz = rotacionarDireitaEsquerda(no);
        }
        raiz.setAltura(maior(altura(raiz.getEsquerda()), altura(raiz.getDireita()))+1);
        return raiz;
    }
    
    private No rotacionarDireita(No no){
        No aux = no.getEsquerda();
        no.setEsquerda(aux.getDireita());
        aux.setDireita(no);
        no.setAltura(maior(altura(no.getEsquerda()), altura(no.getDireita()))+1);
        aux.setAltura(maior(altura(aux.getEsquerda()), altura(aux.getDireita()))+1);
        return aux;
    }
    
    private No rotacionarEsquerda(No no){
        No aux = no.getDireita();
        no.setDireita(aux.getEsquerda());
        aux.setEsquerda(no);
        no.setAltura(maior(altura(no.getEsquerda()), altura(no.getDireita()))+1);
        aux.setAltura(maior(altura(aux.getEsquerda()), altura(aux.getDireita()))+1);
        return aux;
    }
    
    private No rotacionarEsquerdaDireita(No no){
        no.setEsquerda(rotacionarEsquerda(no.getEsquerda()));
        return rotacionarDireita(no);
    }
    
    private No rotacionarDireitaEsquerda(No no){
        no.setDireita(rotacionarDireita(no.getDireita()));
        return rotacionarEsquerda(no);
    }
}