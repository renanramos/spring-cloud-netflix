package br.com.renanrramossi.payment.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.com.renanrramossi.payment")
@EnableJpaRepositories("br.com.renanrramossi.payment")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
