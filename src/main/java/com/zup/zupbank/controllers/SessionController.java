package com.zup.zupbank.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("session")
public class SessionController {

    // Metodo para ajudar no desenvolvimento
    @GetMapping("/ping")
    public String completa(SessionStatus status, HttpSession session, HttpServletRequest request){
        System.out.println(request.getSession());
        System.out.println(status);
        System.out.println(session.getAttribute("nome"));
        System.out.println(session.getAttribute("sobrenome"));
        System.out.println(session.getAttribute("cpf"));
        System.out.println(session.getAttribute("email"));
        System.out.println(session.getAttribute("cep"));
        System.out.println(session.getAttribute("rua"));
        System.out.println(session.getAttribute("bairro"));
        System.out.println(session.getAttribute("complemento"));
        System.out.println(session.getAttribute("cidade"));
        System.out.println(session.getAttribute("estado"));
        return "Ping";
    }

    @GetMapping("/invalidate")
    public String destroi(HttpSession session ){
        session.invalidate();
        return "Sessão destruída";
    }

}
