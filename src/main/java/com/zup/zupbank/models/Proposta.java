package com.zup.zupbank.models;

public class Proposta {

    private boolean valida;

    protected Pessoa pessoa;
    protected Endereco endereco;
    protected byte[] foto_cpf;

    public Proposta(){
        this.valida = false;
    }

    public Proposta(boolean valida) {
        this.valida = valida;
    }


    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public byte[] getFoto_cpf() {
        return foto_cpf;
    }

    public void setFoto_cpf(byte[] foto_cpf) {
        this.foto_cpf = foto_cpf;
    }

}
