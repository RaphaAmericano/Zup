package com.zup.zupbank.services;

import com.zup.zupbank.models.Endereco;
import com.zup.zupbank.models.Pessoa;
import com.zup.zupbank.utils.Format;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static Pessoa createSessionPessoa(HttpSession session){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(session.getAttribute("nome").toString());
        pessoa.setSobrenome(session.getAttribute("sobrenome").toString());
        pessoa.setEmail(session.getAttribute("email").toString());
        pessoa.setCpf(Long.parseLong(session.getAttribute("cpf").toString()));
        LocalDate nascimento = LocalDate.parse(session.getAttribute("dataNascimento").toString());
        pessoa.setDataNascimento(nascimento);
        return pessoa;
    }

}
