package com.example.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SuppressWarnings("deprecation")
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.example")
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
@EnableEurekaClient

@EnableWebMvc
public class MoviecatalogueprojectApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory= new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectionRequestTimeout(3000);
		return new RestTemplate(clientHttpRequestFactory);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MoviecatalogueprojectApplication.class, args);
	}

}
