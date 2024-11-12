package ProjEDII.arvores;

public class ArvoreAVL extends ArvoreBST {
    public ArvoreAVL() {
        setRaiz(null);
        setNumBuscas(0);
        setNumInsercoes(0);
        setNumRemocoes(0);
    }

    @Override
    public void inserir(No no) {
        int comparacoes = 1; // Inicializa a contagem de comparações
        if (getRaiz() == null) {
            setRaiz(no);
            setNumInsercoes(getNumInsercoes() + comparacoes); // Atualiza a contagem de comparações
            return;
        }

        No atual = getRaiz();
        No pai = null;

        // Percorre a árvore para encontrar a posição do novo nó
        while (atual != null) {
            pai = atual;
            comparacoes++;

            if (no.getNomeEscola().compareTo(atual.getNomeEscola()) < 0) {
                atual = atual.getEsquerda();
            } else if (no.getNomeEscola().compareTo(atual.getNomeEscola()) > 0) {
                atual = atual.getDireita();
            } else {
                setNumInsercoes(getNumInsercoes() + comparacoes); // Atualiza as comparações
                return; // Nó já existe, não precisa ser inserido novamente
            }
        }

        // Inserção do nó no lugar correto
        if (no.getNomeEscola().compareTo(pai.getNomeEscola()) < 0) {
            pai.setEsquerda(no);
        } else {
            pai.setDireita(no);
        }
        no.setPai(pai);

        // Atualiza o número total de comparações para inserções
        setNumInsercoes(getNumInsercoes() + comparacoes);

        // Balancear a árvore após a inserção
        while (pai != null) {
            pai = balancear(pai);
            pai = pai.getPai();
        }
    }

    @Override
    public boolean remover(String nome) {
        int comparacoes = 1; // Inicializa a contagem de comparações
        if (getRaiz() == null) {
            return false;
        }

        No atual = getRaiz();
        No pai = null;

        // Encontra o nó que precisa ser removido
        while (atual != null && !nome.equals(atual.getNomeEscola())) {
            pai = atual;
            comparacoes++; // Incrementa o número de comparações
            if (nome.compareTo(atual.getNomeEscola()) < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        if (atual == null) {
            setNumRemocoes(getNumRemocoes() + comparacoes); // Atualiza o número de comparações antes de retornar falso
            return false;
        }

        // Caso 1: Nó sem filhos (nó folha)
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            if (atual == getRaiz()) {
                setRaiz(null);
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(null);
            } else {
                pai.setDireita(null);
            }
        }
        // Caso 2: Nó com apenas um filho
        else if (atual.getDireita() == null) {
            if (atual == getRaiz()) {
                setRaiz(atual.getEsquerda());
                getRaiz().setPai(null);
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(atual.getEsquerda());
            } else {
                pai.setDireita(atual.getEsquerda());
            }
            atual.getEsquerda().setPai(pai);
        } else if (atual.getEsquerda() == null) {
            if (atual == getRaiz()) {
                setRaiz(atual.getDireita());
                getRaiz().setPai(null);
            } else if (pai.getEsquerda() == atual) {
                pai.setEsquerda(atual.getDireita());
            } else {
                pai.setDireita(atual.getDireita());
            }
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

            // Atualizar o número de comparações para o sucessor removido
            comparacoes++; // Incrementa para a remoção do sucessor
        }

        // Atualiza o número total de comparações para remoções
        setNumRemocoes(getNumRemocoes() + comparacoes);

        // Balancear a árvore a partir do pai do nó removido
        while (pai != null) {
            pai = balancear(pai);
            pai = pai.getPai();
        }

        return true;
    }

    @Override
    public No buscar(String nome) {
        int comparacoes = 1; // Inicializa a contagem de comparações
        No atual = getRaiz();

        // Busca o nó na árvore
        while (atual != null) {
            if (nome.equals(atual.getNomeEscola())) {
                setNumBuscas(comparacoes); // Atualiza o número total de comparações
                return atual;
            }
            comparacoes++;
            if (nome.compareTo(atual.getNomeEscola()) < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        setNumBuscas(comparacoes); // Atualiza o número total de comparações
        return null;
    }

    private No balancear(No no) {
        if (no == null) return null;

        int fb = fatorBalanceamento(no);

        // Balanceamento à direita
        if (fb > 1) {
            if (fatorBalanceamento(no.getEsquerda()) < 0) {
                no.setEsquerda(rotacionarEsquerda(no.getEsquerda()));
            }
            return rotacionarDireita(no);
        }
        // Balanceamento à esquerda
        else if (fb < -1) {
            if (fatorBalanceamento(no.getDireita()) > 0) {
                no.setDireita(rotacionarDireita(no.getDireita()));
            }
            return rotacionarEsquerda(no);
        }

        return no;
    }

    private No rotacionarDireita(No no) {
        No aux = no.getEsquerda();
        no.setEsquerda(aux.getDireita());
        if (aux.getDireita() != null) aux.getDireita().setPai(no);
        aux.setDireita(no);
        aux.setPai(no.getPai());

        if (no.getPai() != null) {
            if (no.getPai().getEsquerda() == no) {
                no.getPai().setEsquerda(aux);
            } else {
                no.getPai().setDireita(aux);
            }
        } else {
            setRaiz(aux);
        }

        no.setPai(aux);

        return aux;
    }

    private No rotacionarEsquerda(No no) {
        No aux = no.getDireita();
        no.setDireita(aux.getEsquerda());
        if (aux.getEsquerda() != null) aux.getEsquerda().setPai(no);
        aux.setEsquerda(no);
        aux.setPai(no.getPai());

        if (no.getPai() != null) {
            if (no.getPai().getEsquerda() == no) {
                no.getPai().setEsquerda(aux);
            } else {
                no.getPai().setDireita(aux);
            }
        } else {
            setRaiz(aux);
        }

        no.setPai(aux);

        return aux;
    }

    private int fatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.getEsquerda()) - altura(no.getDireita());
    }

    @Override
    protected int altura(No no) {
        if (no == null) return 0;

        int alturaEsquerda = altura(no.getEsquerda());
        int alturaDireita = altura(no.getDireita());

        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }
}
