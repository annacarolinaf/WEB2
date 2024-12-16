package br.ufscar.dc.dsw.domain;

public class Empresa {

    private String cnpj;
    private String cidade;
    private String descricao;
    private Usuario usuario;

    public Empresa(String cnpj) {
        this.cnpj = cnpj;
    }

    public Empresa(String cnpj, String cidade, String descricao, Usuario usuario) {
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.descricao = descricao;
        this.usuario = usuario;

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}