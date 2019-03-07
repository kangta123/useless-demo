package com.example.demo;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
//@EnableDiscoveryClient
@Slf4j
public class DemoApplication {
    Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) Integer time) {
        log.info("Call api hello");
        if (Objects.isNull(time)) {
            time = 1000;
        }
        int timeout = random.nextInt(time);
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (timeout > 900) {
            throw new RuntimeException("测试错误情况, timeout=" + timeout);
        }

        return "service processing time:" + timeout;
    }

    @RequestMapping("/api/error")
    public String error() {
        throw new RuntimeException("异常测试情况");
    }

    @ControllerAdvice
    class AppExceptionHandlerController extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity <Object> handleException(HttpServletRequest request, Exception e) {
            return createResponseEntity(500, request.getRequestURI(), e.getMessage());
        }

        private ResponseEntity <Object> createResponseEntity(int httpStatus, String requestUri, String message) {
            HashMap <Object, Object> errorMap = Maps.newHashMap();
            errorMap.put("code", httpStatus);
            errorMap.put("message", message);
            errorMap.put("uri", requestUri);
            errorMap.put("error", true);
            String json = JsonUtils.object2Json(errorMap);

            return ResponseEntity.status(HttpStatus.valueOf(httpStatus)).body(json);
        }

    }


}

