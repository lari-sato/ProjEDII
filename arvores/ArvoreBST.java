package ProjEDII.arvores;

public class ArvoreBST {
    private No raiz = null;
    private int numInsercoes;
    private int numRemocoes;
    private int numBuscas;

    public ArvoreBST() {
        this.raiz = null;
        this.numInsercoes = 0;
        this.numRemocoes = 0;
        this.numBuscas = 0;
    }

    public No getRaiz() {
        return this.raiz;
    }

    public int getNumInsercoes() {
        return this.numInsercoes;
    }

    public int getNumRemocoes() {
        return this.numRemocoes;
    }

    public int getNumBuscas() {
        return this.numBuscas;
    }

    public void setRaiz(No no) {
        this.raiz = no;
    }

    public void setNumInsercoes(int numInsercoes) {
        this.numInsercoes = numInsercoes;
    }

    public void setNumRemocoes(int numRemocoes) {
        this.numRemocoes = numRemocoes;
    }

    public void setNumBuscas(int numBuscas) {
        this.numBuscas = numBuscas;
    }

    public boolean isEmpty() {
        return getRaiz() == null;
    }

    public void inserir(No no) {
        int comparacoes = 1;
        if (getRaiz() == null) setRaiz(no);
        else {
            No atual = getRaiz();
            No pai = null;

            while (atual != null) {
                pai = atual;
                comparacoes++;
                if (no.getNomeEscola().compareTo(atual.getNomeEscola()) < 0) {
                    atual = atual.getEsquerda();
                } else {
                    atual = atual.getDireita();
                }
            }

            if (no.getNomeEscola().compareTo(pai.getNomeEscola()) < 0) {
                pai.setEsquerda(no);
            } else {
                pai.setDireita(no);
            }
            no.setPai(pai);
        }
        setNumInsercoes(getNumInsercoes() + comparacoes);
    }

    public boolean remover(String nome) {
        int comparacoes = 1;
        if (getRaiz() == null) return false;

        No atual = getRaiz();
        No pai = null;

        while (atual != null && !atual.getNomeEscola().equals(nome)) {
            pai = atual;
            comparacoes++;
            if (nome.compareTo(atual.getNomeEscola()) < 0) atual = atual.getEsquerda();
            else atual = atual.getDireita();
        }

        if (atual == null) return false;

        // Caso 1: Nó sem filhos
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            if (atual == getRaiz()) setRaiz(null);
            else if (pai.getEsquerda() == atual) pai.setEsquerda(null);
            else pai.setDireita(null);
        }
        // Caso 2: Nó com apenas um filho
        else if (atual.getDireita() == null) {
            if (atual == getRaiz()) {
                setRaiz(atual.getEsquerda());
                getRaiz().setPai(null);
            } else if (pai.getEsquerda() == atual) pai.setEsquerda(atual.getEsquerda());
            else pai.setDireita(atual.getEsquerda());
            atual.getEsquerda().setPai(pai);
        } else if (atual.getEsquerda() == null) {
            if (atual == getRaiz()) {
                setRaiz(atual.getDireita());
                getRaiz().setPai(null);
            } else if (pai.getEsquerda() == atual) pai.setEsquerda(atual.getDireita());
            else pai.setDireita(atual.getDireita());
            atual.getDireita().setPai(pai);
        }
        // Caso 3: Nó com dois filhos
        else {
            No sucessor = menorMaior(atual.getDireita());
            atual.copiarValores(sucessor);

            // Remover o sucessor (que não terá dois filhos, sendo um caso 1 ou caso 2)
            if (sucessor.getPai().getEsquerda() == sucessor) {
                sucessor.getPai().setEsquerda(sucessor.getDireita());
            } else {
                sucessor.getPai().setDireita(sucessor.getDireita());
            }
            if (sucessor.getDireita() != null) {
                sucessor.getDireita().setPai(sucessor.getPai());
            }
        }

        setNumRemocoes(getNumRemocoes() + comparacoes);
        return true;
    }

    protected No menorMaior(No no) {
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    public No buscar(String nome) {
        int[] comparacoes = {1};
        No aux = buscar(getRaiz(), nome, comparacoes);
        setNumBuscas(comparacoes[0]);
        return aux;
    }

    private No buscar(No raiz, String nome, int[] comparacoes) {
        if (raiz == null) return null;
        comparacoes[0]++;
        if (nome.compareTo(raiz.getNomeEscola()) == 0) return raiz;
        else if (nome.compareTo(raiz.getNomeEscola()) < 0) return buscar(raiz.getEsquerda(), nome, comparacoes);
        return buscar(raiz.getDireita(), nome, comparacoes);
    }

    public int altura(No no) {
        if (no == null) {
            return -1;
        }

        int alturaEsquerda = altura(no.getEsquerda());
        int alturaDireita = altura(no.getDireita());

        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public int calcularAltura() {
        return altura(getRaiz());
    }

    public void printTree() {
        if (isEmpty()) System.out.print("Árvore vazia.\n");
        else printTree(raiz, 0);
    }

    private void printTree(No no, int nivel) {
        if (no != null) {
            printTree(no.getDireita(), nivel + 1);

            for (int i = 0; i < nivel; i++) {
                System.out.print("        ");
            }

            System.out.println(no.getNomeEscola());
            printTree(no.getEsquerda(), nivel + 1);
        }
    }
}
