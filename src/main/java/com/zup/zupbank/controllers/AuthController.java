package com.zup.zupbank.controllers;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.services.ContaService;
import com.zup.zupbank.services.MailService;
import com.zup.zupbank.utils.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private MailService mailService;

    @PostMapping( value = "/primeiro", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity primeiroAcesso(@RequestBody Map<String, Object> acesso ){
        String email = acesso.get("email").toString();
        long cpf = (long) acesso.get("cpf");

        Conta conta = this.contaService.getContaByEmail(email, cpf);

        return new ResponseEntity(HttpStatus.OK);
    }


}
