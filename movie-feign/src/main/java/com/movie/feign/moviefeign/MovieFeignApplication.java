package com.movie.feign.moviefeign;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class MovieFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieFeignApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
					.setConnectTimeout(Duration.ofMillis(3000))
					.setReadTimeout(Duration.ofMillis(3000))
					.build();
	}
}
