package com.ex.message.util;


import org.omg.DynamicAny.NameValuePair;

import java.util.*;

public class SignUtils {

    public static String doSign(Map<String,String> params){

        NameValuePair[] param = new NameValuePair[1];
        param[0] = new NameValuePair();

        String paramString = mapToString(params);
        return paramString;
    }

    public static String mapToString(Map<String,String> params) {
        Set<String> keysEnum = params.keySet();
        Iterator<String> iterator = keysEnum.iterator();

        List<String> keyList = new ArrayList<String>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            keyList.add(key);
        }

        Collections.sort(keyList);
        StringBuffer signSb = new StringBuffer();
        for (String key : keyList) {
            if (!signSb.toString().isEmpty()) {
                signSb.append("&");
            }

            signSb.append(key + "=" + params.get(key));
        }

        return signSb.toString();
    }

}
