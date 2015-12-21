
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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
	private static final Integer VALIDITY_TIME = 28800;	// 8 Hours

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationHandler customHandler;
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		 auth.userDetailsService(userDetailsService);
		 auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/resources/**", "/webjars/**");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
			 .authorizeRequests()
				 .antMatchers("/", "/signUp", "/signin", "/courses/**").permitAll()
				 .antMatchers("/private/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
				 .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
				 .anyRequest().authenticated()
				 .and()
			 .formLogin()
				 .loginPage("/signin")
				 .usernameParameter("email")
				 .passwordParameter("password")
				 .successHandler(customHandler)			  	
				 .and().csrf()
				 .and()
			 .logout()
				 .logoutSuccessUrl("/signin?signout")
				 .invalidateHttpSession(true)
				 .and()
			 .exceptionHandling()
				 .accessDeniedPage("/403")
				 .and()
			 .rememberMe()
				 .rememberMeParameter("remember-me")
				 .tokenRepository(persistentTokenRepository())
				 .tokenValiditySeconds(VALIDITY_TIME)
				 .and()
			 .sessionManagement()
				 .invalidSessionUrl("/signin")
				 .maximumSessions(1);
				 
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