package com.kr.coffie.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kr.coffie.config.handler.LoginFaileHandler;
import com.kr.coffie.config.handler.LoginSuccessHandler;
import com.kr.coffie.login.service.UserDetailService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecutrityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserDetailService service;
	
	//암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationProvider authProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/webjars/**", "/dist/**", "/plugins/**", "/css/**", "/user/**","/swagger-resources/**")
		.antMatchers("/images/**", "/JS/**", "/font", "/webfonts/**", "/main/**", "/swagger-ui/**", "/v2/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.cors().disable()
		.authorizeRequests()
		.antMatchers("/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/page/login/loginpage")
		.loginProcessingUrl("/loginProc").permitAll()
		.successHandler(new LoginSuccessHandler())
		.failureHandler(new LoginFaileHandler())
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.maxSessionsPreventsLogin(true);
	}
	
	
}
