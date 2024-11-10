package ProjEDII;

import java.util.InputMismatchException;
import java.util.Scanner;

import ProjEDII.arvores.ArvoreAVL;
import ProjEDII.arvores.ArvoreBST;
import ProjEDII.arquivo.Arquivo;

public class Main{
    public static void main(String[] args){
        String nomeArquivo = "ProjEDII\\arquivo\\dataset.csv", nome;
        Arquivo arquivo = new Arquivo();
        ArvoreBST BST = new ArvoreBST();
        ArvoreAVL AVL = new ArvoreAVL();
        Scanner s = new Scanner(System.in);
        int opcao;

        while (true){
            System.out.print("\n\nMENU\n\n1. Inserir dados nas árvores\n"+
            "2. Remover dado das árvores\n"+
            "3. Buscar dado nas árvores\n"+
            "4. Comparação de desempenho entre as árvores\n"+
            "5. Sair\n");
            
            System.out.print("\nDigite sua opção: ");
            try{
                opcao = s.nextInt();
                s.nextLine();

                switch(opcao){
                    case 1:
                        arquivo.inserirDados(nomeArquivo, BST, AVL);
                        System.out.println("Número de comparações na BST: " + BST.getNumInsercoes());
                        System.out.println("Número de comparações na AVL: " + AVL.getNumInsercoes());
                        break;
                        
                    case 2:
                        if (BST.isEmpty() || AVL.isEmpty()){
                            System.out.println("Dados não inseridos!");
                            break;
                        }else{
                            System.out.print("Insira o nome da escola a ser removida: ");
                            nome = s.nextLine();
                            BST.remover(nome);
                            AVL.remover(nome);

                            System.out.println("Número de comparações na BST: " + BST.getNumRemocoes());
                            System.out.println("Número de comparações na AVL: " + AVL.getNumRemocoes());
                            break;
                        }

                    case 3:
                        if (BST.isEmpty() || AVL.isEmpty()){
                            System.out.println("Dados não inseridos!");
                            break;
                        }else{
                            System.out.print("Insira o nome da escola a ser buscada: ");
                            nome = s.nextLine();
                            BST.buscar(nome);
                            AVL.buscar(nome);

                            System.out.println("Número de comparações na BST: " + BST.getNumBuscas());
                            System.out.println("Número de comparações na AVL: " + AVL.getNumBuscas());
                            break;
                        }

                    case 4:

                    case 5:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    
                    default:
                        System.out.println("Opção inválida!");
                }
            }catch (InputMismatchException e){
                System.out.println("Entrada inválida: " + e.getMessage());
                s.nextLine();
            }
        }
    }    
}
