//Giovana Simões Franco     RA: 10417646
//Julia Santos Oliveira     RA: 10417672
//Larissa Yuri Sato         RA: 10418318

package ProjEDII.arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ProjEDII.arvores.ArvoreAVL;
import ProjEDII.arvores.ArvoreBST;
import ProjEDII.arvores.No;

public class Arquivo {
    public Arquivo() {

    }

    public void inserirAVL(String arquivo, ArvoreAVL AVL) {
        String linha;
        try (BufferedReader buffer = new BufferedReader(new FileReader(arquivo))) {
            buffer.readLine(); // Pula a primeira linha com títulos
            while ((linha = buffer.readLine()) != null) {
                String[] dados = linha.split(";");
                No no = new No();
                no.setAno(Integer.parseInt(dados[0]));
                no.setCodDiretoria(Integer.parseInt(dados[1]));
                no.setNomeDiretoria(dados[2]);
                no.setMunicipio(dados[3]);
                no.setCodRede(Integer.parseInt(dados[4]));
                no.setCodEscola(Integer.parseInt(dados[5]));
                no.setNomeEscola(dados[6]);
                no.setCodTipoEscola(Integer.parseInt(dados[7]));
                no.setAPR_1(Float.parseFloat(dados[8]));
                no.setREP_1(Float.parseFloat(dados[9]));
                no.setABA_1(Float.parseFloat(dados[10]));
                no.setAPR_2(Float.parseFloat(dados[11]));
                no.setREP_2(Float.parseFloat(dados[12]));
                no.setABA_2(Float.parseFloat(dados[13]));
                no.setAPR_3(Float.parseFloat(dados[14]));
                no.setREP_3(Float.parseFloat(dados[15]));
                no.setABA_3(Float.parseFloat(dados[16]));
                AVL.inserir(no);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void inserirBST(String arquivo, ArvoreBST BST) {
        String linha;
        try (BufferedReader buffer = new BufferedReader(new FileReader(arquivo))) {
            buffer.readLine(); // Pula a primeira linha com títulos
            while ((linha = buffer.readLine()) != null) {
                String[] dados = linha.split(";");
                No no = new No();
                no.setAno(Integer.parseInt(dados[0]));
                no.setCodDiretoria(Integer.parseInt(dados[1]));
                no.setNomeDiretoria(dados[2]);
                no.setMunicipio(dados[3]);
                no.setCodRede(Integer.parseInt(dados[4]));
                no.setCodEscola(Integer.parseInt(dados[5]));
                no.setNomeEscola(dados[6]);
                no.setCodTipoEscola(Integer.parseInt(dados[7]));
                no.setAPR_1(Float.parseFloat(dados[8]));
                no.setREP_1(Float.parseFloat(dados[9]));
                no.setABA_1(Float.parseFloat(dados[10]));
                no.setAPR_2(Float.parseFloat(dados[11]));
                no.setREP_2(Float.parseFloat(dados[12]));
                no.setABA_2(Float.parseFloat(dados[13]));
                no.setAPR_3(Float.parseFloat(dados[14]));
                BST.inserir(no);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
            e.printStackTrace();
        }
    }
}