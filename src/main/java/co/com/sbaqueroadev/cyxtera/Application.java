package co.com.sbaqueroadev.cyxtera;

import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication//(scanBasePackages  = "co.com.sbaqueroadev.cyxtera")
@EnableScheduling
@ComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(co.com.sbaqueroadev.cyxtera.Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Scope("session")
	public CalculationData getCalculationData(){
		return new CalculationData();
	}

	@Bean
	@Scope("session")
	public SessionCalculationData getSessionCalculationData(){
		return new SessionCalculationData();
	}

}
