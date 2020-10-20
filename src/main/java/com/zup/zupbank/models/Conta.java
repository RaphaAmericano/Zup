package com.zup.zupbank.models;


import java.util.Date;

public class Conta {
    protected Pessoa pessoa;
    protected Endereco endereco;
    protected byte[] foto_cpf;

    private int agencia;
    private long numeroConta;
    private int codigoBanco;
    private double saldo;

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

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        if( agencia > 9999){
            throw new IllegalArgumentException("Agência deve ser menor que 4 dígitos");
        }
        this.agencia = agencia;
    }

    public long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(long numeroConta) {
        if( numeroConta > 99999999L){
            throw new IllegalArgumentException("Agência deve ser menor que 8 dígitos");
        }
        this.numeroConta = numeroConta;
    }

    public int getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(int codigoBanco) {
        if( codigoBanco > 999){
            throw new IllegalArgumentException("Agência deve ser menor que 4 dígitos");
        }
        this.codigoBanco = codigoBanco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
