package com.loginPage.LoginPageBackend;

import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableReactiveFirestoreRepositories
public class LoginPageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginPageBackendApplication.class, args);
	}

}
