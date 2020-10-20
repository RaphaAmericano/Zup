package com.zup.zupbank.services;


import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.utils.Validation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContaService {

    public static Map<Boolean, String> checkPasso1(Pessoa pessoa){

        Validation validador = new Validation();

        Map<Boolean, String> retorno = new HashMap<Boolean, String>();

        if(pessoa.getNome().trim().equals("")){
            retorno.put(false, "Nome inválido");
            return retorno;
        }
        if(pessoa.getSobrenome().trim().equals("")){
            retorno.put(false, "Sobrenome inválido");
            return retorno;
        }
        if(!Validation.checkEmail(pessoa.getEmail()) ) {
            retorno.put(false, "Email inválido");
            return retorno;
        }
        if(validador.checkEmailExists(pessoa.getEmail())){
            retorno.put(false, "Email já cadastrado");
            return retorno;
        }
        if(!Validation.checkCPF(pessoa.getCpf()) ) {
            retorno.put(false, "CPF inválido");
            return retorno;
        }
        if(validador.checkCPFExists(pessoa.getCpf())){
            retorno.put(false, "CPF já cadastrado");
            return retorno;
        }
        if(!Validation.checkMaioridade(pessoa.getDataNascimento())){
            retorno.put(false, "Cadastro inválido. É preciso ser maior de idade.");
            return retorno;
        }
        retorno.put(true, "Dados validados");
        return retorno;
    }

    public static Map<Boolean, String> checkPasso2(Endereco endereco){

        Validation validador = new Validation();
        Map<Boolean, String> retorno = new HashMap<Boolean, String>();

        if(endereco.getCep() <= 0L){
            retorno.put(false, "CEP inválido");
            return retorno;
        }
        if(endereco.getRua().trim().equals("")){
            retorno.put(false, "Rua inválida");
            return retorno;
        }
        if(endereco.getBairro().trim().equals("")){
            retorno.put(false, "Bairro inválido");
            return retorno;
        }
        if(endereco.getComplemento().trim().equals("")){
            retorno.put(false, "Complemento inválido");
            return retorno;
        }
        retorno.put(true, "Dados validados");
        return retorno;
    }

    public static Map<Boolean, String> checkPasso3(byte[] arquivo ){
        Map<Boolean,String> retorno = new HashMap<Boolean, String>();
        if(arquivo != null && arquivo.length > 0){
            retorno.put(true, "Dados validados");
            return retorno;
        }
        retorno.put(false, "Arquivo inválido");
        return retorno;
    }

}
