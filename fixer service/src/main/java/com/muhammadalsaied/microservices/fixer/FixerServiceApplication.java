package com.muhammadalsaied.microservices.fixer;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class FixerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixerServiceApplication.class, args);
	}
        
        @Bean
        public Sampler defaultSampler(){
            return Sampler.ALWAYS_SAMPLE;
        }
}
