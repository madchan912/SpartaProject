package com.sparta.ecommerce.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.sparta.ecommerce.orderservice", "com.sparta.ecommerce.userservice", "com.sparta.ecommerce.productservice"})
@EnableJpaRepositories(basePackages = {"com.sparta.ecommerce.orderservice", "com.sparta.ecommerce.userservice", "com.sparta.ecommerce.productservice"})
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
