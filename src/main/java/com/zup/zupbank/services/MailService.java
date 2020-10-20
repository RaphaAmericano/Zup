package com.zup.zupbank.services;

import com.zup.zupbank.models.Conta;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class MailService {

    private JavaMailSender configMail(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("933b3de814a370");
        mailSender.setPassword("cba98b468d3239");

        return mailSender;
    }

    public void sendEmail(String to, String assunto, String conteudo){
        String from = "noreply@zupbank.com";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(assunto);
        email.setText(conteudo);
        JavaMailSender sender = this.configMail();
        sender.send(email);
    }

    public static Map<String, String> mailContent(Object contaProposta ){
        Map<String, String> retorno = new HashMap<String, String>();
        if(contaProposta instanceof Conta){
            retorno.put("destinatario", ((Conta) contaProposta).getProposta().getPessoa().getEmail());
            retorno.put("assunto", "Bem vindo ao Zup Bank!");
            String texto = "Olá "+ ( ((Conta) contaProposta).getProposta().getPessoa().getNome() ) + "! Tudo bem?\r\n"
                    + "Seja bem vindo ao Zup Bank\r\n"
                    + "Seus dados cadastrais:\r\n"
                    + "Agencia:" + (((Conta) contaProposta).getAgencia()) +"\r\n"
                    + "Conta:" + (((Conta) contaProposta).getNumeroConta()) +"\r\n"
                    + "Código do banco:" + (((Conta) contaProposta).getCodigoBanco()) +"\r\n";
            retorno.put("conteudo", texto );
        } else if(contaProposta instanceof Conta){

        }
        retorno.put("mensagem", "Erro ao gerar o email");
        return retorno;
    }



}
