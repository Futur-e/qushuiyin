package com.example.huafeng_serve;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class ServeApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ServeApplication.class, args);

        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("项目启动成功...");
        log.info("web端:http//:"+ip+":8083\t");
        log.info("app端:http//:"+ip+":8083\t");
    }

}
