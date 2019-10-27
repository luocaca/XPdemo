package com.sinovatech.unicom.push;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptos {

    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_IVSIZE = 16;
    private static SecureRandom random = new SecureRandom();

    /**
     * 使用AES加密原始字符�?
     *
     * @param input
     *            原始输入字符数组
     * @param key
     *            符合AES要求的密�?
     */
    public static byte[] aesEncrypt(byte[] input, byte[] key) {
        return aes(input, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES加密原始字符�?
     *
     * @param input
     *            原始输入字符数组
     * @param key
     *            符合AES要求的密�?
     * @param iv
     *            初始向量
     */
    public static byte[] aesEncrypt(byte[] input, byte[] key, byte[] iv) {
        return aes(input, key, iv, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES解密字符�? 返回原始字符�?
     *
     * @param input
     *            Hex编码的加密字符串
     * @param key
     *            符合AES要求的密�?
     */
    public static String aesDecrypt(byte[] input, byte[] key) {
        byte[] decryptResult = aes(input, key, Cipher.DECRYPT_MODE);
        return new String(decryptResult);
    }

    /**
     * 使用AES解密字符�? 返回原始字符�?
     *
     * @param input
     *            Hex编码的加密字符串
     * @param key
     *            符合AES要求的密�?
     * @param iv
     *            初始向量
     */
    public static String aesDecrypt(byte[] input, byte[] key, byte[] iv) {
        byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
        return new String(decryptResult);
    }

    /**
     * 使用AES加密或解密无编码的原始字节数�? 返回无编码的字节数组结果.
     *
     * @param input
     *            原始字节数组
     * @param key
     *            符合AES要求的密�?
     * @param mode
     *            Cipher.ENCRYPT_MODE �?Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            // throw Exceptions.unchecked(e);
            return null;
        }
    }

    /**
     * 使用AES加密或解密无编码的原始字节数�? 返回无编码的字节数组结果.
     *
     * @param input
     *            原始字节数组
     * @param key
     *            符合AES要求的密�?
     * @param iv
     *            初始向量
     * @param mode
     *            Cipher.ENCRYPT_MODE �?Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 生成AES密钥,返回字节数组, 默认长度�?28�?16字节).
     */
    public static byte[] generateAesKey() {
        return generateAesKey(DEFAULT_AES_KEYSIZE);
    }

    /**
     * 生成AES密钥,可�?长度�?28,192,256�?
     */
    public static byte[] generateAesKey(int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(keysize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
     */
    public static byte[] generateIV() {
        byte[] bytes = new byte[DEFAULT_IVSIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 随机向量：f282500008143959ca752778ecdf7d53 密钥：6005cf529f96268725afd30b86683c6b
     */
    public static void main(String[] args) {
        System.out.println(EncodeUtils.hexEncode(generateIV()));
        System.out.println(EncodeUtils.hexEncode(generateAesKey()));
    }


}
