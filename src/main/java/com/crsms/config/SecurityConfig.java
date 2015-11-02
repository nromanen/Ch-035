package com.crsms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * @author Roman Romaniuk
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	CustomAuthenticationHandler customHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		 auth.userDetailsService(userDetailsService);
		 auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
		  .authorizeRequests()
						  	.antMatchers("/login").permitAll()
						  	.antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
						  	.antMatchers("/manager/**").access("hasAnyRole ('ROLE_ADMIN', 'ROLE_MANAGER')")
						  	.antMatchers("/teacher/**").access("hasAnyRole ('ROLE_ADMIN', 'ROLE_TEACHER')")
						  	.antMatchers("/student/**").access("hasAnyRole ('ROLE_ADMIN', 'ROLE_STUDENT')")
						  	.and();
	  http
	  		.formLogin().loginPage("/login")
						  	.usernameParameter("email")
						  	.passwordParameter("password")
						  	.successHandler(customHandler)	// redirects user accordingly to his role
						  	.and()
						  .logout().logoutSuccessUrl("/login?logout")
						  .and().csrf()
						  .and().exceptionHandling().accessDeniedPage("/403");
	}
	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService);
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}