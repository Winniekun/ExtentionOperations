package com.wkk.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/3/8
 */
@Slf4j
public class AESUtil {
    private static final int KEY_LENGTH = 16;

    private AESUtil() {
        throw new IllegalStateException("Utility class");
    }

    // 加密
    public static String encrypt(String sSrc, String sKey, String ivStr) {
        if (sKey == null) {
            log.warn(LogDomain.ENCRYPT, "Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != KEY_LENGTH) {
            log.warn(LogDomain.ENCRYPT, "Key长度不是16位");
            return null;
        }
        try {
            byte[] raw = sKey.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度1234567890123456
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes());

            byte[] encode = Base64.getEncoder().encode(encrypted);
            String str = new BASE64Encoder().encode(encrypted);
            str = str.replaceAll("\r\n", "");
            str = str.replaceAll("\n", "");

            return str;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
                | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            log.error(LogDomain.ENCRYPT, e.getMessage(), e);
        }
        return sSrc;
    }


    // 解密
    public static String decrypt(String sSrc, String sKey, String ivStr) {
        // 判断Key是否正确
        if (sKey == null) {
            log.warn(LogDomain.DECRYPT, "Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != KEY_LENGTH) {
            log.warn(LogDomain.DECRYPT, "Key长度不是16位");
            return null;
        }

        try {
            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            // 先用base64解密
            byte[] encrypted = new BASE64Decoder().decodeBuffer(sSrc);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException
                | IOException | IllegalBlockSizeException | BadPaddingException e) {
            log.error(LogDomain.DECRYPT, e.getMessage(), e);

        }

        return sSrc;
    }
}
