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
        return 10L;
    }

}
