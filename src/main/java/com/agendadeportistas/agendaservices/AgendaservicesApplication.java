package com.agendadeportistas.agendaservices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.agendadeportistas.agendaservices.security.AppProperies;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class AgendaservicesApplication {
	/*
	 * @Autowired
	 * private ApplicationContext applicationContext;
	 */

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

	/*
	 * @PostConstruct
	 * public void listBeans() {
	 * System.out.println("Beans:");
	 * String[] beans = applicationContext.getBeanDefinitionNames();
	 * for (String beanName : beans) {
	 * System.out.println(beanName);
	 * }
	 * }
	 */
}
