package ProjEDII.arvores;

public class No{
    private No esquerda;
    private No direita;
    private No pai;
    private int ano;
    private int codDiretoria;
    private String nomeDiretoria;
    private String municipio;
    private int codRede;
    private int codEscola;
    private String nomeEscola;
    private int codTipoEscola;
    private float APR_1;
    private float REP_1;
    private float ABA_1;
    private float APR_1_A;
    private float REP_1_A;
    private float ABA_1_A;
    private float APR_2;
    private float REP_2;
    private float ABA_2;
    private float APR_2_A;
    private float REP_2_A;
    private float ABA_2_A;
    private float APR_3;
    private float REP_3;
    private float ABA_3;

    public No(String nome){
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.nomeEscola = nome;
    }

    public No(int ano, int codDiretoria, String nomeDiretoria, String municipio, int codRede,
              int codEscola, String nomeEscola, int codTipoEscola, float APR_1, float REP_1,
              float ABA_1, float APR_1_A, float REP_1_A, float ABA_1_A, float APR_2, float REP_2,
              float ABA_2, float APR_2_A, float REP_2_A, float ABA_2_A, float APR_3, float REP_3, float ABA_3){
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this. ano = ano;
        this.codDiretoria = codDiretoria;
        this.nomeDiretoria = nomeDiretoria;
        this.municipio = municipio;
        this.codRede = codRede;
        this.codEscola = codEscola;
        this.nomeEscola = nomeEscola;
        this.codTipoEscola = codTipoEscola;
        this.APR_1 = APR_1;
        this.REP_1 = REP_1;
        this.ABA_1 = ABA_1;
        this.APR_1_A = APR_1_A;
        this.REP_1_A = REP_1_A;
        this.ABA_1_A = ABA_1_A;
        this.APR_2 = APR_2;
        this.REP_2 = REP_2;
        this.ABA_2 = ABA_2;
        this.APR_2_A = APR_2_A;
        this.REP_2_A = REP_2_A;
        this.ABA_2_A = ABA_2_A;
        this.APR_3 = APR_3;
        this.REP_3 = REP_3;
        this.ABA_3 = ABA_3;
    }

    public No getEsquerda(){
        return esquerda;
    }

    public void setEsquerda(No esquerda){
        this.esquerda = esquerda;
    }

    public No getDireita(){
        return direita;
    }

    public void setDireita(No direita){
        this.direita = direita;
    }

    public No getPai(){
        return pai;
    }

    public void setPai(No pai){
        this.pai = pai;
    }

    public String getNomeEscola(){
        return nomeEscola;
    }

    public void copiarValores(No no){
        this.ano = no.ano;
        this.codDiretoria = no.codDiretoria;
        this.nomeDiretoria = no.nomeDiretoria;
        this.municipio = no.municipio;
        this.codRede = no.codRede;
        this.codEscola = no.codEscola;
        this.nomeEscola = no.nomeEscola;
        this.codTipoEscola = no.codTipoEscola;
        this.APR_1 = no.APR_1;
        this.REP_1 = no.REP_1;
        this.ABA_1 = no.ABA_1;
        this.APR_1_A = no.APR_1_A;
        this.REP_1_A = no.REP_1_A;
        this.ABA_1_A = no.ABA_1_A;
        this.APR_2 = no.APR_2;
        this.REP_2 = no.REP_2;
        this.ABA_2 = no.ABA_2;
        this.APR_2_A = no.APR_2_A;
        this.REP_2_A = no.REP_2_A;
        this.ABA_2_A = no.ABA_2_A;
        this.APR_3 = no.APR_3;
        this.REP_3 = no.REP_3;
        this.ABA_3 = no.ABA_3;
    }
}
