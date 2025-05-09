package com.innova;
// oe
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "Models")
public class InnovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovaApplication.class, args);
	}

}
