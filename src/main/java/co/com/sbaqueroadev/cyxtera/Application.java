package co.com.sbaqueroadev.cyxtera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages  = "co.com.sbaqueroadev.cyxtera")
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(co.com.sbaqueroadev.cyxtera.Application.class, args);
	}

	/*@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

}
