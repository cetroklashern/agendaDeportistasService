package com.agendadeportistas.agendaservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.agendadeportistas.agendaservices.security.AppProperies;

@SpringBootApplication
@EnableJpaAuditing
public class AgendaservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaservicesApplication.class, args);
		System.out.println("Aplicación de servicios de agenda de deportistas iniciada");
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperies getAppProperies() {
		return new AppProperies();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}
}
