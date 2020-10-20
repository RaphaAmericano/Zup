package com.zup.zupbank.models;


import java.util.Date;

public class Conta {
    Pessoa pessoa;
    Endereco endereco;
    byte[] foto_cpf;

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
