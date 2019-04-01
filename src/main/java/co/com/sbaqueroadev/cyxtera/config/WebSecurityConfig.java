package co.com.sbaqueroadev.cyxtera.config;

import co.com.sbaqueroadev.cyxtera.security.CustomAccessDeniedHandler;
import co.com.sbaqueroadev.cyxtera.security.CustomAuthenticationFailureHandler;
import co.com.sbaqueroadev.cyxtera.security.CustomLoginSuccessHandler;
import co.com.sbaqueroadev.cyxtera.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	private UserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private CustomAuthenticationFailureHandler authenticationFailureHandler =
			new CustomAuthenticationFailureHandler("/");
	private CustomLoginSuccessHandler authenticationSuccessHandler = 
			new CustomLoginSuccessHandler("/");

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Configuration of web security: ");
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll().and().anonymous();
		/*http.cors().and().csrf().disable().authorizeRequests()//.anyRequest().authenticated()
		/*.antMatchers(HttpMethod.GET,"/error"
				,"/resources/**","/webjars/**","/users/access").permitAll()
		.antMatchers(HttpMethod.POST,"/users/sign-up").permitAll()
		/*.antMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority(
				Privileges.API.getValue().getName(), Privileges.READ.getValue().getName())
		.antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority(
				Privileges.API.getValue().getName(),Privileges.WRITE.getValue().getName())
		/*.and()
		.anonymous().and()
        .exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler)
       /* .authenticationEntryPoint(new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

			}
		})*///new org.springframework.boot.security.Http401AuthenticationEntryPoint("headerValue"))
		/*.and()
		.formLogin()
		.loginPage("/users/access")
		.permitAll()
		.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
		.successHandler(authenticationSuccessHandler)
		.permitAll()
		.and()
		.logout()
		.clearAuthentication(true)
		.deleteCookies(SecurityConstants.HEADER_STRING)
		.invalidateHttpSession(true)
		.logoutUrl("/logout")
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))//,UsernamePasswordAuthenticationFilter.class)
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		// this disables session creation on Spring Security
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);*/
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}