package com.main.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SampleApiApplication extends SpringBootServletInitializer  {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SampleApiApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(SampleApiApplication.class, args);
	}
}
