package com.yibu.dex.rpc.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FeignConfig {

    @Bean
    public ResponseEntityDecoder feignDecoder() {
        HttpMessageConverter fastJsonConverter = createFastJsonConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(fastJsonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    @Bean
    public SpringEncoder feignEncoder(){
        HttpMessageConverter fastJsonConverter = createFastJsonConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(fastJsonConverter);
        return new SpringEncoder(objectFactory);
    }

    @Bean
    HttpMessageConverter createFastJsonConverter() {

        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //升级最新版本需加=============================================================
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的NULL转化为""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的NULL转化为0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的NULL转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的NULL转化为false
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.PrettyFormat,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return fastConverter;
    }
}