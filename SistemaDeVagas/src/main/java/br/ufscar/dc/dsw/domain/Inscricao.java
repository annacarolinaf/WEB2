package br.ufscar.dc.dsw.domain;

public class Inscricao {
    private Long id_inscricao;
    private Profissional profissional;
    private Vaga vaga;
    private Integer resultado;
    private String qualificacao;

    public Inscricao(Long id_inscricao) {
        this.id_inscricao = id_inscricao;
    }

    public Inscricao(Profissional profissional, Vaga vaga, Integer resultado, String qualificacao) {
        this.profissional = profissional;
        this.vaga = vaga;
        this.resultado = resultado;
        this.qualificacao = qualificacao;
    }

    public Inscricao( Long id_inscricao, Profissional profissional,  Vaga vaga, Integer resultado, String qualificacao) {
        this(profissional, vaga, resultado, qualificacao);
        this.id_inscricao = id_inscricao;
    }

    public Long getId_inscricao() {
        return id_inscricao;
    }

    public void setId_inscricao(Long id_inscricao ) {
        this.id_inscricao = id_inscricao ;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }
}
