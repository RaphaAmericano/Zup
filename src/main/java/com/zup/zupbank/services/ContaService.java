package com.zup.zupbank.services;


import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.utils.Validation;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    public static Boolean checkPasso1(Pessoa pessoa){

        Validation validador = new Validation();

        if(pessoa.getNome().trim().equals("")){
            return false;
        }
        if(pessoa.getSobrenome().trim().equals("")){
            return false;
        }
        if(!Validation.checkEmail(pessoa.getEmail()) ) {
            return false;
        }
        if(validador.checkEmailExists(pessoa.getEmail())){
            return false;
        }
        if(!Validation.checkCPF(pessoa.getCpf()) ) {
            return false;
        }
        if(validador.checkCPFExists(pessoa.getCpf())){
            return false;
        }
        if(!Validation.checkMaioridade(pessoa.getDataNascimento())){
            return false;
        }
        return true;
    }

    public static Boolean checkPasso2(Endereco endereco){

        Validation validador = new Validation();

//        if(pessoa.getNome().trim().equals("")){
//            return false;
//        }
//        if(pessoa.getSobrenome().trim().equals("")){
//            return false;
//        }
//        if(!Validation.checkEmail(pessoa.getEmail()) ) {
//            return false;
//        }
//        if(validador.checkEmailExists(pessoa.getEmail())){
//            return false;
//        }
//        if(!Validation.checkCPF(pessoa.getCpf()) ) {
//            return false;
//        }
//        if(validador.checkCPFExists(pessoa.getCpf())){
//            return false;
//        }
//        if(!Validation.checkMaioridade(pessoa.getDataNascimento())){
//            return false;
//        }
        return true;
    }

}
