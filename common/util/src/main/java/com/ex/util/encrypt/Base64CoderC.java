package com.ex.util.encrypt;


import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64CoderC {

    private static Base64 base64 = new Base64();

    public static String encode(String str) {
        try {
            str = base64.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            //just ignore
        }
        return str;
    }

    public static String decode(String str) {
        return new String(Base64.decodeBase64(str));
    }

//    public static void main(String[] args) {
//    	String str = "!@#$%$%^&%$&EFDSFADSAFDSdsfadsafd898234983092849320AFDR@#$#@$#@$#@%$#@";
//    	String ret = encode(str);
//		System.out.println(ret);
//		System.out.println(decode(ret));
//	}
}