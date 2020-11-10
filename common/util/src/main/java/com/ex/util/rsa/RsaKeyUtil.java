package com.ex.util.rsa;

import com.ex.util.encrypt.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class RsaKeyUtil {

    final static int rsaTime = 10 * 60 * 60;

    static Logger logger = LoggerFactory.getLogger(RsaKeyUtil.class);

    public final static String rsaSessionPre = "rsa_key_";

    public static RsaKey getRsaKey(String ip) {
        RsaKey rsaKey = null;
        if (StringUtils.isNotEmpty(ip)) {
            ip = EncryptUtil.MD5(ip);
            rsaKey = addRsaKey(ip);
        }
        return rsaKey;
    }


    private static RsaKey addRsaKey(String ip) {
        Map<String, Object> keyMap = null;
        try {
            keyMap = RSACoder.initKey();

            String publicKey = RSACoder.getPublicKey(keyMap);
            String privateKey = RSACoder.getPrivateKey(keyMap);

            RsaKey rsaUser = new RsaKey();
            rsaUser.setPubKey(publicKey.replace(" ", "").replace("\n", "").replace("\r", ""));
            rsaUser.setPriKey(privateKey.replace(" ", "").replace("\n", "").replace("\r", ""));
            rsaUser.setStimes(System.currentTimeMillis());
            return rsaUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RsaKey getRasKey() {
        Map<String, Object> keyMap = null;
        try {
            keyMap = RSACoder.initKey();
            String publicKey = RSACoder.getPublicKey(keyMap);
            String privateKey = RSACoder.getPrivateKey(keyMap);

//			System.err.println("公钥: \n\r" + publicKey);
//			System.err.println("私钥： \n\r" + privateKey);

            RsaKey rsaUser = new RsaKey();
            rsaUser.setPubKey(publicKey.replace(" ", "").replace("\n", "").replace("\r", ""));
            rsaUser.setPriKey(privateKey.replace(" ", "").replace("\n", "").replace("\r", ""));
            rsaUser.setStimes(System.currentTimeMillis());
            return rsaUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

//        Map<String, Object> stringObjectMap = RSACoder.initKey();
        /*String publicKey = RSACoder.getPublicKey(stringObjectMap);
        String privateKey = RSACoder.getPrivateKey(stringObjectMap);*/

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDn11Kff52MMWWOG+W/fylta72gBOcw5sS4f29+\n" +
                "JLEqlC66e4kTcpUUmumQ3G/yftDb/VQ0WKfx1aZweckDNsV0Xq9L8+HlCl83GVUaxkxIGmbIIm06\n" +
                "EacqOD9YZFk5kWfgxghQRoX9XeunE0DCJqqV2ndlkBeuF0c0E2gqAB5XOwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOfXUp9/nYwxZY4b5b9/KW1rvaAE\n" +
                "5zDmxLh/b34ksSqULrp7iRNylRSa6ZDcb/J+0Nv9VDRYp/HVpnB5yQM2xXRer0vz4eUKXzcZVRrG\n" +
                "TEgaZsgibToRpyo4P1hkWTmRZ+DGCFBGhf1d66cTQMImqpXad2WQF64XRzQTaCoAHlc7AgMBAAEC\n" +
                "gYEAwzaUdIbREu249FrbLk6VhHQW/DmErbFybWOobo/YueCCJDXG6yAtFWeHSZnDjW1o0gPSNi18\n" +
                "ZG4gsk5pipJA2Swn/xRDmhMSSKjh/vK3gcSc10+N9Kf9roNIWPjJLGdDeCXG7Yl9bgw+CKrru9tZ\n" +
                "VFc+YPRC24Qrv0OsTBTeLAECQQD8QAiAWEWzZczZ+P5Qc8x72F4XSidaijTy2+EaUFojNSQ8EWSI\n" +
                "TXpDJBrVvulUl2Gm3fwVFMZXs3uGoVz/8TE7AkEA60me4xAyYLqJrAvlqOy9WIC/bdskift3TQLg\n" +
                "5w3O0zckSD3uJhhf4HE6csWIWHgbIkATZhu/rPMlNudu9LUSAQJAcD9iSRJ+uazkMl7NdCmDZdzL\n" +
                "SdvSlUSI+UUE7zuwUJh2LlCjBtBLRpNLabARjnXqm2GOqibbd+1DW450nuPKPwJAbjXb8OwjTN/T\n" +
                "oP0ppEjQIGaeRG0D3oYlybLxegkjuhf4LMaliL6eGlmvURdp2FbaDt8ItymETRTPd7pMZN90AQJB\n" +
                "AIvZehhw6H5ddrbieM0Qo3t5No3J7PDj+DrEnRfH9c3KlKd2IP6xU3hKanMczL99DiVap437X1+Q\n" +
                "b3NZa7zkoH0=";
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

/*
        MultiDomainConfig multiDomainConfig2=new MultiDomainConfig();
        multiDomainConfig2.setDomainName("12222daaaaaaaaadfasd");
        multiDomainConfig2.setStandbyDomainName("1564164351dasdf");
        multiDomainConfigList.add(multiDomainConfig2);


        MultiDomainConfig multiDomainConfig3=new MultiDomainConfig();
        multiDomainConfig3.setDomainName("12222daaaaaaaaadfasd");
        multiDomainConfig3.setStandbyDomainName("1564164351dasdf");
        multiDomainConfigList.add(multiDomainConfig3);

        MultiDomainConfig multiDomainConfig4=new MultiDomainConfig();
        multiDomainConfig4.setDomainName("12222daaaaaaaaadfasd");
        multiDomainConfig4.setStandbyDomainName("1564164351dasdf");
        multiDomainConfigList.add(multiDomainConfig4);

        MultiDomainConfig multiDomainConfig5=new MultiDomainConfig();
        multiDomainConfig5.setDomainName("12222daaaaaaaaadfasd");
        multiDomainConfig5.setStandbyDomainName("1564164351dasdf");
        multiDomainConfigList.add(multiDomainConfig5);*/

        HashMap<String, String> hashMap = new HashMap<>();

//        byte[] bytes1 = RSACoder.encryptSubsectionByPublicKey(JSON.toJSONString(multiDomainConfigList).getBytes("utf-8"), publicKey);
        byte[] bytes1 = RSACoder.encryptSubsectionByPrivateKey("123456".getBytes("utf-8"), privateKey);
        String s = RSACoder.encryptBASE64(bytes1);
        System.out.println("加密密文" + s);

        System.out.println("=======================解密======================");
        privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKwyb6uwb+TCcgJccfG06tSydpr/bD4H2qOwQqpXcvyusaco/ctr5NkPFOCae19cS5xbydxfp7Qq1zQUYg6gFiBlYUGTSVqbUpuanYk7ow2SftLhMDyjlUlhR229lWZKkuXmtQP4wJffpEMJ8qszWTf/k/k3s8mRM4J+xUIkFPZfAgMBAAECgYEAqxiaOL/hDi3eVlZAqWeYBaslijDM/n2TUx2pv9OQX129C1kBhAQ6qSoBFoKon0H3pu7VDfDFk5EVr5upKtsJ35iSZR1iRqJSS2ruDF99Ep4XTR45Wq7ZQl/GCaVPP5DsRtob90lRv6rNKHY+x4AZwaCMGc1wjdwNiRBuTGX9I3ECQQDfXknnYhib2qreVfsi/ta2DbUdHsgoDyptDb9GjyoPH1qMhEH1sm28qv0Qa9KddF8b0NCv/cN+8WUdP0Ny6JlFAkEAxVpmv33Z3tnAtKGZ0j/U2T35DJdmSCJEsM8xKxdbUAdM1bmmIkAQoMXawQ1kh4QdNwVgTTwv1dgTHs2w7eoBUwJBAKiOb3iS9v0NeumCupvC8OW63FRYOTS5lQtsV034lQWKZHsrBtdQySyZX5R3uRymh2rnHcPg3Bi8MtBqQAzDZ30CQHy3jIcnOTAj3CxKk7LSs+g1ydWzN+gN/+rchO3+bK4+bsgB8oNiiR+Q6ZVmqUkarHvA4gLtLi2txyeIRW+UmhECQQCxz6pHc4RiQOsaYF+v8JPsAQMKwjtqpz3s+6YaXMPf3V79/OS1uBw1ck3ZLQIaBpi3SRAM6syD+wpeDMh3zhs8";
        s = "GGAYT3FLgsULzNmJK2jqpfZ+h1vzOSZV/i7hqZVr8O7zXbMtBJKr7I9IklgO6q3r4I/knYEQdLfG\\nSGeJwA55Qt4KKglF/IGSVAL5RmtXuuXbCev8Zbt8SXVCjIuyDIsZYXVSId6VBf68H/sVpa+9FwN2\\nbxsuskPcGcDPdLw0P09M4AakX2avwtGHkVv757mTSTM/PoYHjbuy+0NDCbhTjAj/0nGZtHrpVqAb\\nqJJsWTiuoaMRURODp+40El5w0uDdwytkYnT7n/LyxdBWQ5LS7/s4sRlmDn61jMj3oWFWVhS8P9t0\\nDJ66bX0J9deld9y35A0Co+ARefl5COIIZfDJNg==";
        byte[] bytes = RSACoder.decryptSubsectionByPrivateKey(RSACoder.decryptBASE64(s), privateKey);

//        byte[] bytes = RSACoder.decryptSubsectionByPublicKey(RSACoder.decryptBASE64(s), publicKey);

//        String s1 = RSACoder.encryptBASE64(bytes);
        String s1 = new String(bytes, "utf-8");
        System.out.println(s1);
    }
}
