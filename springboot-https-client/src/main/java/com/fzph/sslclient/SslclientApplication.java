package com.fzph.sslclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SslclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslclientApplication.class, args);

        while (true){
            System.out.println(HttpClientUtils.sendGet("https://10.200.159.27:8088/server/ssl",""));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
