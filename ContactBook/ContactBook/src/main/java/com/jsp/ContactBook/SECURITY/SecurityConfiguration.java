package com.jsp.ContactBook.SECURITY;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("password")
		.roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/contact").authenticated()
		.anyRequest()
		.permitAll()
		.and().httpBasic();
	}
	
	@Bean
	public PasswordEncoder paswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
}











