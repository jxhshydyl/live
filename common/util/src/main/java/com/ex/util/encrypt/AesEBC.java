package com.ex.util.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesEBC {

    /**
     * 已确认
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    public static String sKey = "1234123412ABCDEF";
    public static String ivParameter = "ABCDEF1234123412";
    private static AesEBC instance = null;

    public static AesEBC getInstance() {
        if (instance == null){
            instance = new AesEBC();
        }
        return instance;
    }

    private AesEBC() {
    }

    /**
     * 加密
     */
    public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
            //此处使用BASE64做转码。
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密
     */
    public String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter){
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "id=15&number=&timeStamp=1591357114612";
        System.out.println("加密前的字串是：" + cSrc);
        // 加密
        String enString = AesEBC.getInstance().encrypt(cSrc, "utf-8", sKey, ivParameter);
        System.out.println("加密后的字串是：" + enString);
        System.out.println("yXVUkR45PFz0UfpbDB8/ew==".equals(enString));
        // 解密
        String DeString = AesEBC.getInstance().decrypt(enString, "utf-8", sKey, ivParameter);
        System.out.println("解密后的字串是：" + DeString);
    }

}