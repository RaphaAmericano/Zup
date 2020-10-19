package com.zup.zupbank.controllers;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.services.ContaService;
import com.zup.zupbank.services.SessionService;
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

        URI location = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8080).path("/passo2").build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, location.toString());
        headers.setLocation(location);
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
        URI location = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8080).path("/passo3").build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, location.toString());
        headers.setLocation(location);
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
        URI location = UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8080).path("/passo4").build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, location.toString());
        headers.setLocation(location);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

}
