package com.thymeleaf.learnthymeleaf;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thymeleaf.learnthymeleaf.service.FileStorageService;

@SpringBootApplication
public class LearnThymeleafApplication implements CommandLineRunner{
	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(LearnThymeleafApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
