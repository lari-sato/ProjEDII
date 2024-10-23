package arvoreBST;

import java.util.Scanner;

public class Main{
    public static void main(String []args){
        ArvoreBST arvore = new ArvoreBST();
        Scanner s = new Scanner(System.in);
        boolean continua = true;
        int opcao, valor;

        do{
            System.out.println("\n\n1 - insira um nó");
            System.out.println("2 - remova um nó");
            System.out.println("3 - buscar um valor");
            System.out.println("4 - percorra a árvore em ordem");
            System.out.println("5 - percorra a árvore em pré ordem");
            System.out.println("6 - percorra a árvore em pós ordem");
            System.out.println("7 - percorra a árvore em largura");
            System.out.println("8 - sair\n");
            System.out.print("\nDigite sua opção: ");
            opcao = s.nextInt();

            switch(opcao){
                case 1:
                    System.out.print("Digite o valor a ser inserido na árvore: ");
                    valor = s.nextInt();
                    arvore.inserir(valor);
                    break;

                case 2:
                    System.out.print("Digite o valor a ser removido da árvore: ");
                    valor = s.nextInt();

                    System.out.print(arvore.remover(valor) == true ? "Nó removido da árvore." : "Valor não encontrado.");
                    break;
                
                case 3:
                    System.out.print("Digite o valor a ser enconrtado na árvore: ");
                    valor = s.nextInt();

                    System.out.print(arvore.buscar(valor) != null ? "Nó encontrado na árvore." : "Valor não encontrado.");
                    break;

                case 4:
                    System.out.print("\nEm ordem: ");
                    arvore.emOrdem();
                    break;

                case 5:
                    System.out.print("\nEm pré-ordem: ");
                    arvore.preOrdem();
                    break;
                
                case 6:
                    System.out.print("\nEm pós-ordem: ");
                    arvore.posOrdem();
                    break;

                case 7:
                    System.out.print("\nEm largura: ");
                    arvore.emLargura();
                    break;

                case 8:
                    continua = false;
                    break;
                
                default:
                    System.out.println("\nDigite uma opção válida!\n");
            }
        }while(continua);

        s.close();
    }
}