package com.zup.zupbank.services;

import com.zup.zupbank.models.Conta;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Service
@SessionAttributes({"nome", "sobrenome", "email", "dataNascimento", "cpf"})
public class SessionService {

    public static void setSessionAttributePasso1(HttpSession session, Conta conta ){
        session.setAttribute("nome", conta.getNome());
        session.setAttribute("sobrenome", conta.getSobrenome());
        session.setAttribute("email", conta.getEmail());
        session.setAttribute("dataNascimento", conta.getDataNascimento());
        session.setAttribute("cpf", conta.getCpf());
    }

    public static void setStatusComplete(SessionStatus status){
        status.setComplete();
    }

}
