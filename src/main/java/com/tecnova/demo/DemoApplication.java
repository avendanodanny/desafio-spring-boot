package com.tecnova.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@OpenAPIDefinition(info = @Info(title = "Api Task",
		version = "1.0.3",
		description = "APIs Swagger Task Microservice",
		license = @License(name = "Apache 2.0"),
		contact = @Contact(url = "", name = "Danny Avendaño", email = "dannyfuv@gmail.com")
		),
		security = {@SecurityRequirement(name = "JWT") },
		servers = {
				@Server(description = "local environment", url = "http://localhost:8080/")
		}
)
@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
