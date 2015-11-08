
package com.crsms.config;

import javax.sql.DataSource;

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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
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
	private DataSource dataSource;

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
						  	.antMatchers("/signUp","/signin", "/**").permitAll()
						  	.antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
						  	.antMatchers("/manager/**").access("hasAnyRole ('ROLE_ADMIN', 'ROLE_MANAGER')")
						  	.antMatchers("/teacher/**").access("hasAnyRole ('ROLE_ADMIN', 'ROLE_TEACHER')")
						  	.antMatchers("/student/**").access("hasAnyRole ('ROLE_ADMIN', 'ROLE_STUDENT')")
						  	.and();
	  http
	  		.formLogin().loginPage("/login")
						  	.usernameParameter("email")
						  	.passwordParameter("password")
						  	.successHandler(customHandler)
						  	.and()
						  	.rememberMe().rememberMeParameter("remember-me")
						  	.tokenRepository(persistentTokenRepository())
						  	.tokenValiditySeconds(86400)
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
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

}