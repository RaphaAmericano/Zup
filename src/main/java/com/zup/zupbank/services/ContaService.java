package com.zup.zupbank.services;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.utils.Validation;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    public static Boolean checkPasso1(Conta conta){

        Validation validador = new Validation();

        if(conta.getNome().trim().equals("")){
            return false;
        }
        if(conta.getSobrenome().trim().equals("")){
            return false;
        }
        if(!Validation.checkEmail(conta.getEmail()) ) {
            return false;
        }
        if(validador.checkEmailExists(conta.getEmail())){
            return false;
        }
        if(!Validation.checkCPF(conta.getCpf()) ) {
            return false;
        }
        if(validador.checkCPFExists(conta.getCpf())){
            return false;
        }
        if(!Validation.checkMaioridade(conta.getDataNascimento())){
            return false;
        }
        return true;
    }

}
