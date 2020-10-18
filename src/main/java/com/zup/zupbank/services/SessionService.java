package com.zup.zupbank.services;

import com.zup.zupbank.models.Conta;
import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Service
@SessionAttributes({"nome", "sobrenome", "email", "dataNascimento", "cpf", "cep", "rua", "bairro", "complemento", "cidade", "estado"})
public class SessionService {

    public static void setSessionAttributePasso1(HttpSession session, Pessoa pessoa ){
        session.setAttribute("nome", pessoa.getNome());
        session.setAttribute("sobrenome", pessoa.getSobrenome());
        session.setAttribute("email", pessoa.getEmail());
        session.setAttribute("dataNascimento", pessoa.getDataNascimento());
        session.setAttribute("cpf", pessoa.getCpf());
    }

    public static void setSessionAttributePasso2(HttpSession session, Endereco endereco ){
        session.setAttribute("cep", endereco.getCep());
        session.setAttribute("rua", endereco.getRua());
        session.setAttribute("bairro", endereco.getBairro());
        session.setAttribute("complemento", endereco.getComplemento());
        session.setAttribute("cidade", endereco.getCidade());
        session.setAttribute("estado", endereco.getEstado());
    }

    public static void setStatusComplete(SessionStatus status){
        status.setComplete();
    }

}
