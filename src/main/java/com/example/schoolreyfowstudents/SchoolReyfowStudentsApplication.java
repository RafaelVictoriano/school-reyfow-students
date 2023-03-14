package com.example.schoolreyfowstudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SchoolReyfowStudentsApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
		SpringApplication.run(SchoolReyfowStudentsApplication.class, args);
	}

}
