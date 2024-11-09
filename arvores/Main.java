package ProjEDII.arvores;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ArvoreAVL arvore = new ArvoreAVL();
        Scanner s = new Scanner(System.in);
        int opcao;
        String nome;

        while (true){
            System.out.print("\n\nMENU\n\n1. Inserir número na árvore\n"+
            "2. Remover número da árvore\n"+
            "3. Buscar número na árvore\n"+
            "4. Mostrar árvore\n"+
            "5. Altura da árvore\n"+
            "6. Sair\n");
            
            System.out.print("\nDigite sua opção: ");
            opcao = s.nextInt();
            s.nextLine();

            switch(opcao){
                case 1:
                    System.out.print("Digite o nome da escola: ");
                    nome = s.nextLine();
                    arvore.inserir(new No(nome));
                    
                    break;
                case 2:
                    System.out.print("Digite o nome da escola a ser removida: ");
                    nome = s.nextLine();
                    arvore.remover(nome);
                    break;
                case 3:
                    System.out.print("Digite o nome da escola para buscar: ");
                    nome = s.nextLine();
                    No no = arvore.buscar(nome);
                    System.out.print("Nó encontrado!\n" + no);
                    break;
                case 4:
                    try {
                        arvore.printTree();
                    } catch (Exception e) {
                        System.out.println("Erro ao exibir a árvore: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Áltura da árvore: " + arvore.altura());
                    break;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }    
}
