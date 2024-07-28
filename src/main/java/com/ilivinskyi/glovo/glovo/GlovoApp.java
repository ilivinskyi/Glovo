package com.ilivinskyi.glovo.glovo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ilivinskyi.glovo.glovo.repository")
@EntityScan(basePackages = "com.ilivinskyi.glovo.glovo.models")
public class GlovoApp {
	public static void main(String[] args) {
		SpringApplication.run(GlovoApp.class, args);
	}
}
