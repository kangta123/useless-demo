package com.example.demo;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.ConfigBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

    @Value("${spring.cloud.client.hostname}")
    private String hostname;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @GetMapping("/sleep")
    public String hello(@RequestParam Integer time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);

        return "ok";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) Integer time, @RequestParam(required = false) Integer size) {
        log.info("中文乱码");
        System.out.println("中文乱码");
        Random random = new Random();
        int timeout = random.nextInt(time);
        if (Objects.nonNull(size)) {
            byte[] bytes = new byte[size];
        }
        int a = 0;
        for (int i = 0; i < timeout; i++) {
            a += i;
        }
        return "service processing time:" + a;
    }

    @PostMapping("/hello")
    public String getbody(@RequestBody Map<Object, Object> data) {
        System.out.println("request body" + data);
        return "request body data is :" + data;
    }

    @RequestMapping("/api/error")
    public String error() {
        throw new RuntimeException("异常测试情况");
    }


}

