package com.ex.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ex.message.service.SendMsService;
import com.ex.message.spring.config.MessageProperties;
import com.ex.message.util.SignUtils;
import com.ex.model.entity.message.Message;
import com.ex.util.http.HttpClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuweipeng
 * @Date: 5/6/2020 下午 9:59
 * @Version: 1.0
 * @Description:
 */
@Service
public class SendMsServiceImpl implements SendMsService {

    @Autowired
    MessageProperties messageProperties;

    @Value("${spring.profiles.active}")
    private String active;

    private Logger logger = LoggerFactory.getLogger(SendMsServiceImpl.class);

    @Override
    public boolean sendMs(Message record) {
        // 开发送短信验证码
//        return doMeiLian(record);
//        return doSaiYou(record);
        return false;
    }

    private boolean doMeiLian(Message message) {
        try {
            logger.info("美联准备发送短信 国家代码:" + message.getCountryCode() + " 手机:" + message.getReceiveAddress());
            String url = String.format("%s?username=%s&password_md5=%s&apikey=%s&mobile=%s&content=%s&encode=%s",
                    messageProperties.getMl().getSmsApiUrl(), messageProperties.getMl().getSmsApiUser(), DigestUtils.md5Hex(messageProperties.getMl().getSmsApiPassword()), messageProperties.getMl().getSmsApiKey(),
                    message.getCountryCode() + message.getReceiveAddress(), URLEncoder.encode(message.getContext()),
                    "UTF-8");
            String s = HttpClient.GET(url);
            logger.info("发送结果: " + s);
            if (s.startsWith("success:")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("短信发送出错: " + e.getMessage());
        }
        return false;
    }

    private boolean doSaiYou(Message message){
        try {
            logger.info("赛邮准备发送短信 国家代码:" + message.getCountryCode() + " 手机:" + message.getReceiveAddress());
            Map<String,String> map = new HashMap<>();
            //赛邮短信平台的一个规则:
            // 您必须为每条短信模板提交一个短信签名，且该签名必须使用全角大括号”【“和”】“包括起来，
            // 请将短信签名的字数控制在2至10字符以内（括号不计算字符数）
            map.put("content","【OTC】" + message.getContext());
            map.put("to",message.getReceiveAddress());

//            map.put("appid","51072");
//            map.put("signature","3e3d3c9ee65d74d02062b7ce0a9d5333");
            map.put("appid",messageProperties.getSy().getSmsSyAppId());
            map.put("signature",messageProperties.getSy().getSmsSySignature());

            SignUtils.doSign(map);
            String url = messageProperties.getSy().getSmsSyApiUrl();
//            String url = "https://api.mysubmail.com/message/send";
            String resultMsg = HttpClient.POST(url,map);
            logger.info("赛邮发送短信结果为：" + resultMsg);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",resultMsg);
            JSONObject jsonData =  jsonObject.getJSONObject("result");
            String status = jsonData.getString("status");
            if(status.startsWith("success")){
                logger.info("赛邮发送短信成功!");
                return true;
            }
        }catch (Exception e){
            logger.error("赛邮发送短信失败原因:" + e.getMessage());
        }
        return false;
    }
}
