package com.gestionreseau;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@ServletComponentScan
@SpringBootApplication
public class PortailcomtransApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException  {
		SpringApplication.run(PortailcomtransApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PortailcomtransApplication.class);
    } 
	

	
	 @Bean
	 public SpringSecurityDialect securityDialect() {
	   return new SpringSecurityDialect();
	 }
	
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}


}

