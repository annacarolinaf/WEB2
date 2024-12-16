package br.ufscar.dc.dsw.domain;

public class Vaga {

    private Long id_vaga;
    private float salario;
    private String descricao;
    private String data_limite;
    private Empresa empresa;
    private String status_vaga;

    public Vaga(Long id_vaga) {
        this.id_vaga = id_vaga;
    }


    public Vaga( float salario, String descricao, String data_limite, Empresa empresa, String status_vaga) {
        this.salario = salario;
        this.descricao = descricao;
        this.data_limite = data_limite;
        this.empresa = empresa;
        this.status_vaga = status_vaga;
    }
    
    public Vaga( Long id_vaga,  float salario, String descricao, String data_limite, Empresa empresa, String status_vaga) {
        this(salario, descricao, data_limite, empresa, status_vaga);
        this.id_vaga = id_vaga;
    }
    //Por que faz diferença colocar o nome do get igual da variável?
    public Long getId_vaga() {
        return id_vaga;
    }

    public void setId_vaga(Long id_vaga) {
        this.id_vaga = id_vaga;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_limite() {
        return data_limite;
    }

    public void setData_limite(String data_limite) {
        this.data_limite = data_limite;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getStatus_vaga() {
        return status_vaga;
    }

    public void setStatus_vaga(String status_vaga) {
        this.status_vaga = status_vaga;
    }
}
