//Giovana Simões Franco     RA: 10417646
//Julia Santos Oliveira     RA: 10417672
//Larissa Yuri Sato         RA: 10418318

package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST{
    public ArvoreAVL(){
        setRaiz(null);
        setDados(0);
        setNumBuscas(0);
        setNumInsercoes(0);
        setNumRemocoes(0);
    }

    public int maior(int altura1, int altura2){
        return (altura1 > altura2) ? altura1 : altura2;
    }

    @Override
    public void inserir(No no){
        int[] comparacoes ={1};
        setRaiz(inserir(getRaiz(), no, comparacoes));
        setNumInsercoes(getNumInsercoes() + comparacoes[0]);
        setDados(getDados() + 1);
    }
    
    private No inserir(No raiz, No no, int[] comparacoes){
        if (raiz == null) return no;

        comparacoes[0]++;
        
        if (no.getNomeEscola().compareTo(raiz.getNomeEscola()) <= 0) raiz.setEsquerda(inserir(raiz.getEsquerda(), no, comparacoes));
        else raiz.setDireita(inserir(raiz.getDireita(), no, comparacoes)); 
        
        return balancear(raiz);
    }

    public void remover(String nome){
        int[] comparacoes = {1};
        boolean[] removido = {false};
        setRaiz(remover(getRaiz(), nome, comparacoes, removido));
        setNumRemocoes(removido[0] == true ? comparacoes[0] : 0);
    }
    
    private No remover(No raiz, String nome, int[] comparacoes, boolean[] removido){
        if (raiz == null) return null;

        comparacoes[0]++;
    
        if (nome.compareTo(raiz.getNomeEscola()) < 0) raiz.setEsquerda(remover(raiz.getEsquerda(), nome, comparacoes, removido));
        else if (nome.compareTo(raiz.getNomeEscola()) > 0) raiz.setDireita(remover(raiz.getDireita(), nome, comparacoes, removido));
        else{
            removido[0] = true;
            setDados(getDados() - 1);

            // Nó com apenas um filho
            if (raiz.getEsquerda() == null) return raiz.getDireita();
            else if (raiz.getDireita() == null) return raiz.getEsquerda();
    
            // Nó com dois filhos
            No aux = menorMaior(raiz.getDireita()); // Escolher o sucessor do nó
            raiz.copiarValores(aux); // Copia os valores do sucessor para o nó
            raiz.setDireita(remover(raiz.getDireita(), aux.getNomeEscola(), comparacoes, removido)); //Deletear o sucessor
        }
        return balancear(raiz);
    }
    
    private No balancear(No raiz){
        if ((altura(raiz.getEsquerda()) - altura(raiz.getDireita())) > 1){
            if (altura(raiz.getEsquerda().getEsquerda()) >= altura(raiz.getEsquerda().getDireita())) raiz = rotacionarDireita(raiz);
            else raiz = rotacionarEsquerdaDireita(raiz);
        }
        else if ((altura(raiz.getEsquerda()) - altura(raiz.getDireita())) < -1){
            if (altura(raiz.getDireita().getDireita()) >= altura(raiz.getDireita().getEsquerda())) raiz = rotacionarEsquerda(raiz);
            else raiz = rotacionarDireitaEsquerda(raiz);
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