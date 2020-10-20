package com.zup.zupbank.models;

public class Proposta extends Conta{
    private boolean valida;

    public Proposta(){
        super();
    }

    public Proposta(Conta conta){
        this.valida = false;
        this.pessoa = conta.getPessoa();
        this.endereco = conta.getEndereco();
        this.foto_cpf = conta.getFoto_cpf();
    }

    public Proposta(boolean valida) {
        this.valida = valida;
    }
}
