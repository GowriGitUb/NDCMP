package com.ceainfotech.ndcmp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Samy
 * 
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	private CustomLoginHandler customLoginHandler;
	
	@Autowired
	private FailureHandler failureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/forgotPassword")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/mobile/restApi/**")
				.permitAll()
				.antMatchers(HttpMethod.POST, "/mobile/restApi/**")
				.permitAll()
				.antMatchers("/mobile/restApi/**")
				.permitAll()
				.antMatchers("/api/**")
				.access("hasRole('SUPER_ADMIN') or hasRole('PROJECT_ADMIN') or hasRole('PROJECT_PLANNER') or hasRole('STATUS_REVIEWER') or hasRole('STATUS_UPDATER') or hasRole('STATUS_APPROVER')")
				.and().formLogin().loginPage("/login").successHandler(customLoginHandler).usernameParameter("username").passwordParameter("password")
				.failureHandler(failureHandler).usernameParameter("username")
				.and().csrf().disable().exceptionHandling().accessDeniedPage("/access_denied");
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
}
