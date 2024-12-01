package br.com.fiap.soat7;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@OpenAPIDefinition(
		info = @Info(
				title = "Produção API",
				description = "Api de produção de pedidos"
		)
)
@EnableFeignClients
@SpringBootApplication
public class ProducaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducaoApplication.class, args);
	}

	@Bean
	public MessageSource messageSource () {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
