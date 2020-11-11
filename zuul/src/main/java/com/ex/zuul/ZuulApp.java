package com.ex.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class })
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "com.meizan.zuul")
@EnableCreateCacheAnnotation
@EnableHystrix
public class ZuulApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
	}
}
