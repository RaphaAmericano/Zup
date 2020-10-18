package com.zup.zupbank.controllers;


import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.services.ContaService;
import com.zup.zupbank.services.SessionService;
import com.zup.zupbank.utils.Validation;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("conta")
public class ContaController {

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

}
