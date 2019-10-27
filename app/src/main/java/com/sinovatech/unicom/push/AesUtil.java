package com.sinovatech.unicom.push;

public class AesUtil {
    private static final String iv = "00e5d201c2c2acbff8154861242ba0c4";// 向量
    private static final String key = "6206c34e2186e752c74e6df32ab8fa5b";// 密钥

    /**
     * @功能:加密
     * @param str
     * @return
     */
    public static String aesEncrypt(String str) {
        return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(),
                EncodeUtils.hexDecode(key), EncodeUtils.hexDecode(iv)));
    }

    /**
     * @功能:解密
     * @param str
     * @return
     */
    public static String aesDecrypt(String str) {
        return Cryptos.aesDecrypt(EncodeUtils.hexDecode(str),
                EncodeUtils.hexDecode(key), EncodeUtils.hexDecode(iv));
    }

    public static void main(String[] args) {
        // 密钥
        System.out.println(Cryptos.generateAesKey());
        // 向量
        System.out.println(Cryptos.generateAesKey());
    }

}
