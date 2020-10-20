package com.zup.zupbank.daos;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Proposta;

public class ContaDAO {

    public Conta novaConta(Conta conta){
        // Faz um insert no Banco cadastrando a nova conta
        return conta;
    }

    public Proposta novaProposta(Proposta proposta){
        // Faz um insert no Banco cadastrando a nova conta
        return proposta;
    }

    public String getEmail(String email){
        // Busca o email no banco e retorna
        return email;
    }

    public String getCPF(String cpf){
        // Busca o cpf no banco e retorna
        return cpf;
    }

}
