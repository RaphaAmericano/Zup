package com.zup.zupbank.utils;

import com.zup.zupbank.daos.ContaDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {


    private ContaDAO contaDAO = new ContaDAO();

    public static Boolean checkEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public Boolean checkEmailExists(String email){
        String checkemail = this.contaDAO.getEmail(email);
        return checkemail.isEmpty();
    }

    public static Boolean checkCPF(long cpf){
        long max = 1000000000000L;
        return cpf < max && cpf > 0;
    }

    public Boolean checkCPFExists(long cpf){
        String cpfStr = Format.FormatCPFLongToString(cpf);
        String checkcpf = this.contaDAO.getCPF(cpfStr);
        return checkcpf.isEmpty();
    }

    public static Boolean checkMaioridade(LocalDate data){
        LocalDate hoje = LocalDate.now();
//        LocalDate aniversario = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period maioridade = Period.between(data, hoje);
        return maioridade.getYears() >= 18;
    }

}
