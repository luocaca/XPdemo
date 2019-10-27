package com.sinovatech.unicom.push;

public class testmain {
    public static void main(String... args) {


        syso(AesUtil.aesEncrypt("1234"));

        String string    ="09ca17c763df557192d86b32ce47d72a";
        syso(AesUtil.aesDecrypt(string));



    }


    public static void syso(String s) {
        System.out.println(s);
    }

}
