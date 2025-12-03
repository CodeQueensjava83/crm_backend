package com.generation.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CrmBackendApplication {

	public static void main(String[] args) {
		

		try {
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		
			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		} catch (Exception e) {
			System.out.println("Running without .env file - using environment variables");
}
		
		SpringApplication.run(CrmBackendApplication.class, args);
	}

}
