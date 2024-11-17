//Giovana Simões Franco     RA: 10417646
//Julia Santos Oliveira     RA: 10417672
//Larissa Yuri Sato         RA: 10418318

package ProjEDII.perguntas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ProjEDII.arvores.ArvoreAVL;
import ProjEDII.arvores.No;

public class Perguntas{
    public Perguntas(){

    }

    public float media(ArvoreAVL arvore, String tipoDado){
        float[] soma = {0}; // Correctly declare soma as a float array
        media(arvore.getRaiz(), soma, tipoDado);
        return soma[0] / arvore.getDados();
    }

    private void media(No no, float[] soma, String tipoDado){
        if (no == null) return;
        media(no.getEsquerda(), soma, tipoDado);
        media(no.getDireita(), soma, tipoDado);
        switch (tipoDado){
            case "APR_1":
                soma[0] += no.getAPR_1();
                break;
            case "APR_2":
                soma[0] += no.getAPR_2();
                break;
            case "APR_3":
                soma[0] += no.getAPR_3();
                break;
            case "REP_1":
                soma[0] += no.getREP_1();
                break;
            case "REP_2":
                soma[0] += no.getREP_2();
                break;
            case "REP_3":
                soma[0] += no.getREP_3();
                break;
            case "ABA_1":
                soma[0] += no.getABA_1();
                break;
            case "ABA_2":
                soma[0] += no.getABA_2();
                break;
            case "ABA_3":
                soma[0] += no.getABA_3();
                break;
            default:
                System.out.println("Tipo de dado inválido!");
        }
    }


    // Como as taxas de abandono e repetência se comportam ao longo dos anos letivos?
    // Como a relação entre aprovação e repetência varia ao longo das séries (do 1º ao 3º ano)?

    public void taxas(ArvoreAVL arvore){
        System.out.println("\nMédia de taxa de aprovação nos anos iniciais no ensino fundamental de 9 anos: " + media(arvore, "APR_1"));
        System.out.println("Média de taxa de reprovação nos anos iniciais no ensino fundamental de 9 anos: " + media(arvore, "REP_1"));
        System.out.println("Média de taxa de abandono nos anos iniciais no ensino fundamental de 9 anos: " + media(arvore, "ABA_1"));
        System.out.println("Média de taxa de aprovação nos anos finais no ensino fundamental de 9 anos: " + media(arvore, "APR_2"));
        System.out.println("Média de taxa de reprovação nos anos finais no ensino fundamental de 9 anos: " + media(arvore, "REP_2"));
        System.out.println("Média de taxa de abandono nos anos finais no ensino fundamental de 9 anos: " + media(arvore, "ABA_2"));
        System.out.println("Média de taxa de aprovação no ensino médio: " + media(arvore, "APR_3"));
        System.out.println("Média de taxa de reprovação no ensino médio: " + media(arvore, "REP_3"));
        System.out.println("Média de taxa de abandono no ensino médio: " + media(arvore, "ABA_3"));
    }

    // Quais diretorias de ensino têm as maiores taxas de repetência e como isso se relaciona com outros fatores (como infraestrutura)?
    // Existe uma diferença significativa entre as taxas de abandono escolar nas diferentes regiões administrativas?

    public void diretoria(ArvoreAVL arvore){
        HashMap<String, List<Float>> diretorias = new HashMap<>();
        diretoria(arvore.getRaiz(), diretorias);
        for (String diretoria : diretorias.keySet()){
            List<Float> values = diretorias.get(diretoria);
            System.out.println("\nDiretoria: " + diretoria +
                               "\nMédia de taxa de reprovação: " + values.get(1)/values.get(0) +
                               "\nMédia de taxa de abandono: " + values.get(2)/values.get(0));
        }
    }  

    private void diretoria(No no, HashMap<String, List<Float>> diretorias){
        if (no == null) return;
    
        diretoria(no.getEsquerda(), diretorias);
        diretoria(no.getDireita(), diretorias);
    
        String diretoria = no.getNomeDiretoria();
        float reprovacao = (no.getREP_1() + no.getREP_2() + no.getREP_3()) / 3.0f;
        float abandono = (no.getABA_1() + no.getABA_2() + no.getABA_3()) / 3.0f;
    
        if (diretorias.containsKey(diretoria)){
            List<Float> values = diretorias.get(diretoria);
            values.set(0, values.get(0) + 1);
            values.set(1, values.get(1) + reprovacao);
            values.set(2, values.get(2) + abandono);
        }else{
            List<Float> values = new ArrayList<>();
            values.add(1.0f);
            values.add(reprovacao);
            values.add(abandono);
            diretorias.put(diretoria, values);
        }
    }

    // Qual o impacto do número de escolas em cada município nas taxas de abandono e aprovação?

    public void escola(ArvoreAVL arvore){
        HashMap<String, List<Float>> escolas = new HashMap<>();
        boolean encontrado = true;
        escola(arvore.getRaiz(), escolas);
        for (String escola : escolas.keySet()){
            List<Float> values = escolas.get(escola);
            System.out.println("\nMunicípio: " + escola + "\nQuantidade de escolas: " + values.get(0) +
            "\nMédia de taxa de aprovação: " + values.get(1)/values.get(0) +
            "\nMédia de taxa de abandono: " + values.get(2)/values.get(0));
        }
    }  

    private void escola(No no, HashMap<String, List<Float>> escolas){
        if (no == null) return;
    
        escola(no.getEsquerda(), escolas);
        escola(no.getDireita(), escolas);
    
        String escola = no.getMunicipio();
        float aprovacao = (no.getAPR_1() + no.getAPR_2() + no.getAPR_3()) / 3.0f;
        float abandono = (no.getABA_1() + no.getABA_2() + no.getABA_3()) / 3.0f;
    
        if (escolas.containsKey(escola)){
            List<Float> values = escolas.get(escola);
            values.set(0, values.get(0) + 1);
            values.set(1, values.get(1) + aprovacao);
            values.set(2, values.get(2) + abandono);
        }else{
            List<Float> values = new ArrayList<>();
            values.add(1.0f);
            values.add(aprovacao);
            values.add(abandono);
            escolas.put(escola, values);
        }
    }
}
