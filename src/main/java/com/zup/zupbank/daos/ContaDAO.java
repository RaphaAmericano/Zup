package com.zup.zupbank.daos;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.models.Proposta;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public class ContaDAO {

    public Conta novaConta(Conta conta){
        // Faz um insert no Banco cadastrando a nova conta
        return conta;
    }

    public Proposta novaProposta(Proposta proposta){
        // Faz um insert no Banco cadastrando a nova conta
        return proposta;
    }

    public Conta getContaByEmail(String email, long cpf){
        Conta conta = new Conta();
        // Para cenario de desenvolvimento
        Proposta proposta = new Proposta();
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);
        pessoa.setCpf(cpf);
        proposta.setPessoa(pessoa);
        conta.setProposta(proposta);
        return conta;
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
