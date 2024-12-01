package br.com.fiap.soat7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProducaoTest {


	@Autowired
	private ApplicationContext context;

	@Autowired
	private MessageSource messageSource;

	@Test
	void contextLoads() {
		assertThat(context).isNotNull();
	}

	@Test
	void messageSourceBeanShouldBeLoaded() {
		assertThat(messageSource).isNotNull();
		assertThat(context.containsBean("messageSource")).isTrue();
	}

}
