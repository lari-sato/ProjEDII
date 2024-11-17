//Giovana Simões Franco     RA: 10417646
//Julia Santos Oliveira     RA: 10417672
//Larissa Yuri Sato         RA: 10418318

package ProjEDII.arvores;

public class ArvoreBST{
    private No raiz = null;
    private int quantDados;
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

    public int getDados(){
        return this.quantDados;
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

    public void setDados(int quantDados){
        this.quantDados = quantDados;
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
        if (getRaiz() == null){
            setRaiz(no);
            setNumInsercoes(getNumInsercoes()+1);
            setDados(getDados() + 1);
        }
        else{
            setNumInsercoes(getNumInsercoes() + inserir(getRaiz(), no, 1));
            setDados(getDados() + 1);
        }
    }
    
    private int inserir(No raiz, No no, int comparacoes){
        if (raiz == null) return comparacoes;
        
        comparacoes++;
        
        if (no.getNomeEscola().compareTo(raiz.getNomeEscola()) <= 0){
            if (raiz.getEsquerda() == null) raiz.setEsquerda(no);
            else comparacoes = inserir(raiz.getEsquerda(), no, comparacoes);
        }else{
            if (raiz.getDireita() == null) raiz.setDireita(no);
            else comparacoes = inserir(raiz.getDireita(), no, comparacoes);
        }
        return comparacoes;
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
        return raiz;
    }

    protected No menorMaior(No no){
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    public No buscar(String nome){
        int[] comparacoes ={1};
        boolean[] encontrado = {false};
        No aux = buscar(getRaiz(), nome, comparacoes, encontrado);
        setNumBuscas(encontrado[0] == true ? comparacoes[0] : 0);
        return aux;
    }

    private No buscar(No raiz, String nome, int[] comparacoes, boolean[] encontrado){
        if (raiz == null) return null;
        comparacoes[0]++;
        if (nome.compareTo(raiz.getNomeEscola()) == 0){
            encontrado[0] = true;
            return raiz;
        } 

        else if (nome.compareTo(raiz.getNomeEscola()) < 0) return buscar(raiz.getEsquerda(), nome, comparacoes, encontrado);
        return buscar(raiz.getDireita(), nome, comparacoes, encontrado);
    }

    public int altura(No no){
        return (no == null) ? -1 : no.getAltura();
    }

    public int calcularAltura(){
        return altura(getRaiz());
    }

    public void printTree(){
        if (isEmpty()) System.out.print("Árvore vazia.\n");
        else printTree(raiz, 0);
    }

    private void printTree(No no, int nivel){
        if (no != null){
            printTree(no.getDireita(), nivel + 1);

            for (int i = 0; i < nivel; i++){
                System.out.print("        ");
            }

            System.out.println(no.getNomeEscola());
            printTree(no.getEsquerda(), nivel + 1);
        }
    }
    
}
