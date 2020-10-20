package com.zup.zupbank.models;

public class Conta {

    private Proposta proposta;

    private int agencia;
    private long numeroConta;
    private int codigoBanco;
    private double saldo;

    public Conta(Proposta proposta){
        this.proposta = proposta;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
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
