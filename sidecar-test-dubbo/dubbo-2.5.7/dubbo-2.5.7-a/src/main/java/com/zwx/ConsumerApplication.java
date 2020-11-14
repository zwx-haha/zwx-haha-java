package com.zwx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-22 11:00
 */
@SpringBootApplication
public class ConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("服务调用者------>>启动完毕");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
