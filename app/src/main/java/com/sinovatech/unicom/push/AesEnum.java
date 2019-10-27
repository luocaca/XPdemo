package com.sinovatech.unicom.push;

public enum  AesEnum {
    AES0("CfAaVIBblv+0ZpR4tL96fw=="), AES1("ng3qqXjlHhjV7AyQ07Wd6Q=="), AES2(
			"1JHdO8EfZz9H6lHir+klAQ=="), AES3("fha8y/FXu9WA3XJRTiiHkA=="), AES4(
			"aGYYxBVCoiz6J/PlmKAjIQ=="), AES5("n5sniU+su4J0s4OqorWhkQ=="), AES6(
			"8CtdIe4aN53MJaWVwSPZBg=="), AES7("u+D1kao8M8dnxmo6lVgnIg=="), AES8(
			"EXrTBaCCXBqN0/WMpLvPkA=="), AES9("paAX4WcC5cK7n2Z14C70aw=="), AESA(
			"wrong number!");

    private final String aes;

    private AesEnum(String aes) {
        this.aes = aes;
    }

    public static AesEnum getAesEnum(int i) {
        AesEnum aes = null;
        switch (i) {
            case 0:
                aes = AesEnum.AES0;
                break;
            case 1:
                aes = AesEnum.AES1;
                break;
            case 2:
                aes = AesEnum.AES2;
                break;
            case 3:
                aes = AesEnum.AES3;
                break;
            case 4:
                aes = AesEnum.AES4;
                break;
            case 5:
                aes = AesEnum.AES5;
                break;
            case 6:
                aes = AesEnum.AES6;
                break;
            case 7:
                aes = AesEnum.AES7;
                break;
            case 8:
                aes = AesEnum.AES8;
                break;
            case 9:
                aes = AesEnum.AES9;
                break;
            default:
                aes = AesEnum.AESA;
        }
        return aes;
    }

    public String getAes() {
        return aes;
    }
}
