package com.zup.zupbank.controllers;


import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.services.ContaService;
import com.zup.zupbank.services.SessionService;
import com.zup.zupbank.utils.Validation;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        if(!ContaService.checkPasso1(pessoa)){
            return new ResponseEntity(pessoa, HttpStatus.BAD_REQUEST);
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
        if(!ContaService.checkPasso2(endereco)){
            return new ResponseEntity(endereco, HttpStatus.BAD_REQUEST);
        }

        Pessoa sessionPessoa;
        try {
            sessionPessoa = SessionService.createSessionPessoa(session);
        }catch (Exception e){
            return new ResponseEntity(endereco, HttpStatus.BAD_REQUEST);
        }

        if(!ContaService.checkPasso1(sessionPessoa)){
            Conta conta = new Conta();
            conta.setPessoa(sessionPessoa);
            conta.setEndereco(endereco);

            return new ResponseEntity(conta, HttpStatus.BAD_REQUEST);
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

        if(file.isEmpty()){
            return new ResponseEntity("Vazio", HttpStatus.BAD_REQUEST);
        }

        try {
            byte[] bytes = file.getBytes();
            SessionService.setSessionAttributePasso3(session, bytes);
        } catch (IOException e){
            e.printStackTrace();
        }


        return new ResponseEntity( HttpStatus.CREATED);
    }

}
