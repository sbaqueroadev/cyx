package co.com.sbaqueroadev.cyxtera;

import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CyxteraTestApplication {

	private static final Logger logger = LoggerFactory.getLogger(CyxteraTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CyxteraTestApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Scope("session")
	public CalculationData getCalculationData(){
		logger.info("Calculation Temporal data bean created.");
		return new CalculationData();
	}

	@Bean
	@Scope("session")
	public SessionCalculationData getSessionCalculationData(){
		logger.info("SessionCalculation data bean created.");
		return new SessionCalculationData();
	}

}
