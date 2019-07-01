package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@RestController
//@EnableDiscoveryClient
public class DemoApplication {


    @Value("${spring.cloud.client.hostname}")
    private String hostname;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void setup() {
        System.out.println(hostname);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) Integer time, @RequestParam(required = false) Integer size) {
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
    public String getbody(@RequestBody Map <Object, Object> data) {
        System.out.println("request body" + data);
        return "request body data is :" + data;
    }

    @RequestMapping("/api/error")
    public String error() {
        throw new RuntimeException("异常测试情况");
    }


}

