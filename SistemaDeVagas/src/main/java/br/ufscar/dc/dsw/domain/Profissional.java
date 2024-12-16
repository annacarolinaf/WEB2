package br.ufscar.dc.dsw.domain;

public class Profissional {

    private String cpf;
    private String data_nasc;
    private String sexo;
    private String telefone;
    private Usuario usuario;

    public Profissional(String cpf){
        this.cpf = cpf;
    }

    public Profissional(String cpf, String data_nasc, String sexo, String telefone, Usuario usuario)
    {
        this(cpf);
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getData_nasc(){
        return data_nasc;
    }

    public void setData_nasc(String data_nasc){
        this.data_nasc = data_nasc;
    }

    public String getSexo(){
        return sexo;
    }

    public void setSexo(String sexo){
        this.sexo = sexo;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}
