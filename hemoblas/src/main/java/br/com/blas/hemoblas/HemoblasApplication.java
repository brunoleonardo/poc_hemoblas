package br.com.blas.hemoblas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class HemoblasApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemoblasApplication.class, args);
	}

	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			// Redireciona para a página principal
			registry.addRedirectViewController("/", "index");
		}

	}
}
