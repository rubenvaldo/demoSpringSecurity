package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	
	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{ // define datasource for users
		auth.authenticationProvider(authenticationProvider());
		
//			.inMemoryAuthentication()
//			.withUser("ruben")
//			.password(passwordEncoder().encode("password"))
//			.authorities("ACCESS_TEST1", "ACCESS_TEST2", "ROLE_ADMIN") // authorities has precedences - need to put role as authority
//			.and()
//			.withUser("antara")
//			.password(passwordEncoder().encode("password"))
//			.roles("USER")
//			.and()
//			.withUser("junior")
//			.password(passwordEncoder().encode("password"))
//			.authorities("ACCESS_TEST1", "ROLE_EMPLOYEE");
//			//.roles("EMPLOYEE").authorities("ACCESS_TEST1");
			
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{ // authorize requests
		http
			.authorizeRequests()
			.antMatchers("/index.html").permitAll() // view
			.antMatchers("/profile/**").authenticated()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/employee/**").hasAnyRole("ADMIN", "EMPLOYEE")
			.antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
			.antMatchers("/api/public/test2").hasAnyAuthority("ACCESS_TEST2") 
			.antMatchers("/api/public/test3").authenticated()
			.antMatchers("/api/public/users").hasRole("ADMIN")	
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.defaultSuccessUrl("/index").permitAll()
//			.loginPage("/login").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index")
			.and()
			.rememberMe();
		
	}
	
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		return daoAuthenticationProvider;
		
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
