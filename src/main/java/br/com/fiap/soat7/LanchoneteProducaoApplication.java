package br.com.fiap.soat7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class LanchoneteProducaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanchoneteProducaoApplication.class, args);
	}

	@Bean
	public MessageSource messageSource () {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
