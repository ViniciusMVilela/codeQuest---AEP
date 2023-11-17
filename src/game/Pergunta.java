package game;

import application.UI;

import java.util.concurrent.TimeUnit;

public class Pergunta {
    private Integer id;
    private String descricao;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String correta;
    private String feedback;
    private String dica;

    public Pergunta(String descricao, String alternativaA, String alternativaB,
                    String alternativaC, String correta, String feedback, Integer id, String dica) {
        this.descricao = descricao;
        this.alternativaA = alternativaA;
        this.alternativaB = alternativaB;
        this.alternativaC = alternativaC;
        this.correta = correta;
        this.feedback = feedback;
        this.id = id;
        this.dica = dica;
    }

    public String getDescricao() {
        return descricao;
    }


    public String getAlternativaA() {
        return alternativaA;
    }


    public String getAlternativaB() {
        return alternativaB;
    }


    public String getAlternativaC() {
        return alternativaC;
    }


    public String getCorreta() {
        return correta;
    }


    public String getFeedback() {
        return feedback;
    }


    public String getDica() {
        return dica;
    }


    @Override
    public String toString() {
        return getDescricao() + "\n" + getAlternativaA() + "\n" + getAlternativaB() + "\n" + getAlternativaC();
    }
}
