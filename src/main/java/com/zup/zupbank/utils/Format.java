package com.zup.zupbank.utils;

public class Format {

    public static String FormatCPFLongToString(long cpf){
        String cpfStr = Long.toString(cpf);
        String parte1 = cpfStr.substring(0, 3);
        String parte2 = cpfStr.substring(3, 6);
        String parte3 = cpfStr.substring(6, 9);
        String parte4 = cpfStr.substring(9, 11);
        String cpftoString = parte1+"."+parte2+"."+parte3+"-"+parte4;

        return cpftoString;
    }

    public static Long FormatCPFStringToLong(String cpf ){
        String[] cpfSplitBar = cpf.split("-");
        String[] cpfSplitDot = cpfSplitBar[0].split("\\.");
        String joinSplitDot = String.join("", cpfSplitDot);
        String joinAll = joinSplitDot+cpfSplitBar[1];
        long cpfStringToLong = Long.parseLong(joinAll);
        return cpfStringToLong;
    }

    public static String FormatCEPLongToString(long cep){
        String cepStr = Long.toString(cep);
        String parte1 = cepStr.substring(0, 5);
        String parte2 = cepStr.substring(5, 8);
        String cepToString = parte1+"-"+parte2;
        return cepToString;
    }
}
