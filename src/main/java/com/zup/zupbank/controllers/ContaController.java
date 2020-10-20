package com.zup.zupbank.controllers;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.services.ContaService;
import com.zup.zupbank.services.SessionService;
import com.zup.zupbank.utils.RequestResponseFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("conta")
public class ContaController {

    private static String UPLOADED_FOLDER = "/temp/";

    @GetMapping
    public ResponseEntity<String> ping(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");
        headers.add(HttpHeaders.LOCATION, "localhost:8080");
        return new ResponseEntity<String>("Ping", headers, HttpStatus.OK
        );
    }

    @PostMapping(value = "/nova", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity novaConta(@RequestBody Pessoa pessoa, HttpSession session){

        if(ContaService.checkPasso1(pessoa).get(false) != null){
            Map<String, String> retorno = new HashMap<String, String>();
            retorno.put("Mensagem", ContaService.checkPasso1(pessoa).get(false));
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = RequestResponseFormat.setHeaderLocation("localhost", 8080, "/passo2");
        SessionService.setSessionAttributePasso1(session, pessoa);
        return new ResponseEntity( headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/passo2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity novaContaPasso2(@RequestBody Endereco endereco, HttpSession session){
        if(ContaService.checkPasso2(endereco).get(false) != null){
            Map<String, String> retorno = new HashMap<String, String>();
            retorno.put("Mensagem", ContaService.checkPasso2(endereco).get(false));
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }

        Pessoa sessionPessoa;
        try {
            sessionPessoa = SessionService.createSessionPessoa(session);
        }catch (Exception e){
            Map<String, String> retorno = new HashMap<String, String>();
            retorno.put("Mensagem", "Os dados pessoais não foram preenchidos");
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }

        if(ContaService.checkPasso1(sessionPessoa).get(false) != null){
            Map<String, String> retorno = new HashMap<String, String>();
            retorno.put("Mensagem", ContaService.checkPasso1(sessionPessoa).get(false));
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = RequestResponseFormat.setHeaderLocation("localhost", 8080, "/passo3");
        SessionService.setSessionAttributePasso2(session, endereco);
        return new ResponseEntity( headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/passo3", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity novaContaPasso3(@RequestParam("file") MultipartFile file, HttpSession session ){

        // Verifica se o arquivo é vazio
        if(file.isEmpty()){
            Map<String, String> retorno =  new HashMap<String, String>();
            retorno.put("Mensagem", "Arquivo vazio");
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }

        // Verifica a existencia dos dados da pessoa no session
        Pessoa pessoaSession;
        try{
            pessoaSession = SessionService.createSessionPessoa(session);
        }catch(Exception e){
            Map<String, String> retorno =  new HashMap<String, String>();
            retorno.put("Mensagem", "Erro no passo 1");
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }
        // Verifica a existencia dos dados de endereco da pessoa no session
        Endereco enderecoSession;
        try {
            enderecoSession = SessionService.createSessionEndereco(session);
        } catch (Exception e){
            Map<String, String> retorno =  new HashMap<String, String>();
            retorno.put("Mensagem", "Erro no passo 2");
            return new ResponseEntity(retorno, HttpStatus.BAD_REQUEST);
        }

        // Armazena a foto em bytes na session
        try {
            byte[] bytes = file.getBytes();
            SessionService.setSessionAttributePasso3(session, bytes);
        } catch (IOException e){
            e.printStackTrace();
        }

        // Finaliza a verificacao e segue o cadastro
        HttpHeaders headers = RequestResponseFormat.setHeaderLocation("localhost", 8080, "/passo4");

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PostMapping( value = "/passo4", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity novaContaPasso4(@RequestBody Map<String, Boolean> aceite, HttpSession session ){
        Map<String, String> retorno = new HashMap<String, String>();
        Conta conta = new Conta();
        Pessoa pessoa;
        try{
            pessoa = SessionService.createSessionPessoa(session);
            conta.setPessoa(pessoa);
        } catch(Exception e){
            retorno.put("mensagem", "Os dados pessoais não foram preenchidos");
            return new ResponseEntity(retorno, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Endereco endereco;
        try {
            endereco = SessionService.createSessionEndereco(session);
            conta.setEndereco(endereco);
        } catch(Exception e ){
            retorno.put("mensagem", "Os dados de endereço não foram preenchidos");
            return new ResponseEntity(retorno, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        try {
            conta.setFoto_cpf(session.getAttribute("foto_cpf").toString().getBytes());
        } catch(Exception e ){
            retorno.put("mensagem", "O arquivo de foto do CPF não existe.");
            return new ResponseEntity(retorno, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if(ContaService.checkPasso1(conta.getPessoa()).get(false) != null){
            retorno.put("mensagem", ContaService.checkPasso1(conta.getPessoa()).get(false));
            return new ResponseEntity(retorno, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(ContaService.checkPasso2(conta.getEndereco()).get(false) != null){
            retorno.put("mensagem", ContaService.checkPasso2(conta.getEndereco()).get(false));
            return new ResponseEntity(retorno, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(ContaService.checkPasso3(conta.getFoto_cpf()).get(false) != null){

            retorno.put("mensagem", ContaService.checkPasso3(conta.getFoto_cpf()).get(false));
            return new ResponseEntity(retorno, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if(aceite.get("aceite")){
            //TODO: mensagem informando o envio de email
            //criar a conta
            // enviar email
            retorno.put("Mensagem", "Um email será enviado");
            return new ResponseEntity(retorno, HttpStatus.OK);
        }
        //TODO: registrar a proposta no sistema
        //enviar o email implorando o aceitar fazer a conta
        retorno.put("mensagem", "Proposta não aceita");
        return new ResponseEntity(retorno, HttpStatus.OK);
    }

}
