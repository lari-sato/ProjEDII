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
    private float APR_2;
    private float REP_2;
    private float ABA_2;
    private float APR_3;
    private float REP_3;
    private float ABA_3;

    public No(){
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.ano = 0;
        this.codDiretoria = 0;
        this.nomeDiretoria = "";
        this.municipio = "";
        this.codRede = 0;
        this.codEscola = 0;
        this.nomeEscola = "";
        this.codTipoEscola = 0;
        this.APR_1 = 0;
        this.REP_1 = 0;
        this.ABA_1 = 0;
        this.APR_2 = 0;
        this.REP_2 = 0;
        this.ABA_2 = 0;
        this.APR_3 = 0;
        this.REP_3 = 0;
        this.ABA_3 = 0;
    }

    public No getEsquerda(){
        return esquerda;
    }

    public No getDireita(){
        return direita;
    }

    public No getPai(){
        return pai;
    }

    public int getAno(){
        return ano;
    }

    public int getCodDiretoria(){
        return codDiretoria;
    }

    public String getNomeDiretoria(){
        return nomeDiretoria;
    }

    public String getMunicipio(){
        return municipio;
    }

    public int getCodRede(){
        return codRede;
    }

    public int getCodEscola(){
        return codEscola;
    }

    public String getNomeEscola(){
        return nomeEscola;
    }

    public int getCodTipoEscola(){
        return codTipoEscola;
    }

    public float getAPR_1(){
        return APR_1;
    }

    public float getREP_1(){
        return REP_1;
    }

    public float getABA_1(){
        return ABA_1;
    }

    public float getAPR_2(){
        return APR_2;
    }

    public float getREP_2(){
        return REP_2;
    }

    public float getABA_2(){
        return ABA_2;
    }

    public float getAPR_3(){
        return APR_3;
    }

    public float getREP_3(){
        return REP_3;
    }

    public float getABA_3(){
        return ABA_3;
    }

    public void setEsquerda(No esquerda){
        this.esquerda = esquerda;
    }

    public void setDireita(No direita){
        this.direita = direita;
    }

    public void setPai(No pai){
        this.pai = pai;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public void setCodDiretoria(int codDiretoria){
        this.codDiretoria = codDiretoria;
    }

    public void setNomeDiretoria(String nomeDiretoria){
        this.nomeDiretoria = nomeDiretoria;
    }

    public void setMunicipio(String municipio){
        this.municipio = municipio;
    }

    public void setCodRede(int codRede){
        this.codRede = codRede;
    }

    public void setCodEscola(int codEscola){
        this.codEscola = codEscola;
    }

    public void setNomeEscola(String nomeEscola){
        this.nomeEscola = nomeEscola;
    }

    public void setCodTipoEscola(int codTipoEscola){
        this.codTipoEscola = codTipoEscola;
    }

    public void setAPR_1(float APR_1){
        this.APR_1 = APR_1;
    }

    public void setREP_1(float REP_1){
        this.REP_1 = REP_1;
    }

    public void setABA_1(float ABA_1){
        this.ABA_1 = ABA_1;
    }

    public void setAPR_2(float APR_2){
        this.APR_2 = APR_2;
    }

    public void setREP_2(float REP_2){
        this.REP_2 = REP_2;
    }

    public void setABA_2(float ABA_2){
        this.ABA_2 = ABA_2;
    }

    public void setAPR_3(float APR_3){
        this.APR_3 = APR_3;
    }

    public void setREP_3(float REP_3){
        this.REP_3 = REP_3;
    }

    public void setABA_3(float ABA_3){
        this.ABA_3 = ABA_3;
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
        this.APR_2 = no.APR_2;
        this.REP_2 = no.REP_2;
        this.ABA_2 = no.ABA_2;
        this.APR_3 = no.APR_3;
        this.REP_3 = no.REP_3;
        this.ABA_3 = no.ABA_3;
    }
}
