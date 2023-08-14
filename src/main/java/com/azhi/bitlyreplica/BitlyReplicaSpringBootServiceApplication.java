package com.azhi.bitlyreplica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableRetry
@EnableAsync
@EnableCaching
@SpringBootApplication
public class BitlyReplicaSpringBootServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitlyReplicaSpringBootServiceApplication.class, args);
    }

}
