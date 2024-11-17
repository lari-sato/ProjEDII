//Giovana Simões Franco     RA: 10417646
//Julia Santos Oliveira     RA: 10417672
//Larissa Yuri Sato         RA: 10418318

package ProjEDII;

import java.util.InputMismatchException;
import java.util.Scanner;
import ProjEDII.arvores.ArvoreAVL;
import ProjEDII.arvores.ArvoreBST;
import ProjEDII.arvores.No;
import ProjEDII.arquivo.Arquivo;
import ProjEDII.perguntas.Perguntas;

public class Main{
    public static void testes(ArvoreBST BST, ArvoreAVL AVL){
        Arquivo arquivo = new Arquivo();

        if (BST.isEmpty() || AVL.isEmpty()){
            arquivo.inserirBST("ProjEDII\\arquivo\\dataset.csv", BST);
            arquivo.inserirAVL("ProjEDII\\arquivo\\dataset.csv", AVL);
        }

        System.out.println("\n\nINSERÇÃO\n");
        System.out.println("Número de comparações na BST: " + BST.getNumInsercoes());
        System.out.println("Número de comparações na AVL: " + AVL.getNumInsercoes());
        
        int somaBST = 0;
        int somaAVL = 0;

        System.out.println("\n\nBUSCA\n");
        System.out.println("Nome a ser buscado: PARQUE ANHANGUERA");
        BST.buscar("PARQUE ANHANGUERA");
        AVL.buscar("PARQUE ANHANGUERA");
        System.out.println("Número de comparações na BST: " + BST.getNumBuscas());
        System.out.println("Número de comparações na AVL: " + AVL.getNumBuscas());
        somaBST += BST.getNumBuscas();
        somaAVL += AVL.getNumBuscas();

        System.out.println("\nNome a ser buscado: CLOTILDE ALVARES DORATIOTO");
        BST.buscar("CLOTILDE ALVARES DORATIOTO");
        AVL.buscar("CLOTILDE ALVARES DORATIOTO");
        System.out.println("Número de comparações na BST: " + BST.getNumBuscas());
        System.out.println("Número de comparações na AVL: " + AVL.getNumBuscas());
        somaBST += BST.getNumBuscas();
        somaAVL += AVL.getNumBuscas();

        System.out.println("\nNome a ser buscado: DURVALINO GRION PROF");
        BST.buscar("DURVALINO GRION PROF");
        AVL.buscar("DURVALINO GRION PROF");
        System.out.println("Número de comparações na BST: " + BST.getNumBuscas());
        System.out.println("Número de comparações na AVL: " + AVL.getNumBuscas());
        somaBST += BST.getNumBuscas();
        somaAVL += AVL.getNumBuscas();

        System.out.println("\nMédia de desempenho da BST: " + somaBST/3 + " comparações");
        System.out.println("Média de desempenho da AVL: " + somaAVL/3 + " comparações");

        somaBST = 0;
        somaAVL = 0;

        System.out.println("\n\nREMOCÃO\n");
        System.out.println("Nome a ser removido: JORNALISTA RUY MESQUITA");
        BST.remover("JORNALISTA RUY MESQUITA");
        AVL.remover("JORNALISTA RUY MESQUITA");
        System.out.println("Número de comparações na BST: " + BST.getNumRemocoes());
        System.out.println("Número de comparações na AVL: " + AVL.getNumRemocoes());
        somaBST += BST.getNumRemocoes();
        somaAVL += AVL.getNumRemocoes();

        System.out.println("\nNome a ser removido: ECA DE QUEIROZ");
        BST.remover("ECA DE QUEIROZ");
        AVL.remover("ECA DE QUEIROZ");
        System.out.println("Número de comparações na BST: " + BST.getNumRemocoes());
        System.out.println("Número de comparações na AVL: " + AVL.getNumRemocoes());
        somaBST += BST.getNumRemocoes();
        somaAVL += AVL.getNumRemocoes();

        System.out.println("\nNome a ser removido: MARIA APARECIDA LOPES PROFA");
        BST.remover("MARIA APARECIDA LOPES PROFA");
        AVL.remover("MARIA APARECIDA LOPES PROFA");
        System.out.println("Número de comparações na BST: " + BST.getNumRemocoes());
        System.out.println("Número de comparações na AVL: " + AVL.getNumRemocoes());
        somaBST += BST.getNumRemocoes();
        somaAVL += AVL.getNumRemocoes();

        System.out.println("\nMédia de desempenho da BST: " + somaBST/3 + " comparações");
        System.out.println("Média de desempenho da AVL: " + somaAVL/3 + " comparações");
    }
            
    
        public static void main(String[] args){
            String nomeArquivo = "ProjEDII\\arquivo\\dataset.csv", nome;
            Arquivo arquivo = new Arquivo();
            Perguntas perguntas = new Perguntas();
            ArvoreBST BST = new ArvoreBST();
            ArvoreAVL AVL = new ArvoreAVL();
            
            try (Scanner s = new Scanner(System.in)){
                int opcao;
    
                while (true){
                    System.out.print("\n----------MENU----------\n1. Inserir dados nas árvores\n"+
                    "2. Remover dado das árvores\n"+
                    "3. Buscar dado nas árvores\n"+
                    "4. Comparação de desempenho entre as árvores\n"+
                    "5. Dados das árvores para as perguntas exploratórias\n"+
                    "6. Sair\n------------------------\n");
                    
                    System.out.print("\nDigite a opção desejada: ");
                    try{
                        opcao = s.nextInt();
                        s.nextLine();
    
                        switch(opcao){
                            case 1:
                                if (!BST.isEmpty() || !AVL.isEmpty()) System.out.println("Dados já inseridos!");
                                else{
                                    arquivo.inserirBST(nomeArquivo, BST);
                                    arquivo.inserirAVL(nomeArquivo, AVL);
                                    System.out.println("Número de comparações na BST: " + BST.getNumInsercoes());
                                    System.out.println("Número de comparações na AVL: " + AVL.getNumInsercoes());
                                }
                                
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
    
                                    if (BST.getNumRemocoes() == 0 || AVL.getNumRemocoes() == 0) System.out.println("Escola não encontrada!");
                                    else{
                                        System.out.println("Número de comparações na BST: " + BST.getNumRemocoes());
                                        System.out.println("Número de comparações na AVL: " + AVL.getNumRemocoes());
                                    }
                                    
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
    
                                    if (BST.getNumBuscas() == 0 || AVL.getNumBuscas() == 0) System.out.println("Escola não encontrada!");
                                    else{
                                        System.out.println("Número de comparações na BST: " + BST.getNumBuscas());
                                        System.out.println("Número de comparações na AVL: " + AVL.getNumBuscas());
                                    }
                                    break;
                                }
    
                            case 4:
                                testes(BST, AVL);
                            break;

                        case 5:
                            if (BST.isEmpty() || AVL.isEmpty()){
                                System.out.println("Dados não inseridos!");
                                break;
                            }else{
                                System.out.println("Perguntas exploratórias:");
                                System.out.println("Pergunta 1: como as taxas de abandono e repetência se comportam ao longo dos anos letivos?"+
                                                "\nPergunta 2: como a relação entre aprovação e repetência varia ao longo das séries (do 1º ao 3º ano)?"+
                                                "\nPergunta 3: quais diretorias de ensino têm as maiores taxas de repetência e como isso se relaciona com outros fatores (como infraestrutura)?" +
                                                "\nPergunta 4: existe uma diferença significativa entre as taxas de abandono escolar nas diferentes regiões administrativas?"+
                                                "\nPergunta 5: qual o impacto do número de escolas em cada município nas taxas de abandono e aprovação?");
                                
                                System.out.print("\nDigite o número da pergunta desejada: ");
                                try{
                                    int pergunta = s.nextInt();
                                    s.nextLine();

                                    switch(pergunta){
                                        case 1:
                                        case 2:
                                            System.out.println("\nDados para a pergunta 1 e 2");
                                            perguntas.taxas(AVL);
                                            break;
                                        case 3:
                                        case 4:
                                            System.out.println("\nDados para a pergunta 3 e 4");
                                            perguntas.diretoria(AVL);
                                            break;
                                        case 5:
                                            System.out.println("\nDados para a pergunta 5");
                                            perguntas.escola(AVL);
                                            break;
                                        default:
                                            System.out.println("Opção inválida!");
                                    }
                                }catch (InputMismatchException e){
                                    System.out.println("Entrada inválida: " + e.getMessage());
                                    s.nextLine();
                                }
                            }
                            break;

                        case 6:
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
        }catch (Exception e){
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }   
}
