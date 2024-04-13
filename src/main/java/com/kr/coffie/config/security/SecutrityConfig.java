package com.kr.coffie.config.security;

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

import com.kr.coffie.config.security.handler.LoginFaileHandler;
import com.kr.coffie.config.security.handler.LoginSuccessHandler;
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
		.antMatchers("/images/**", "/JS/**", "/font", "/webfonts/**", "/main/**", "/swagger-ui/**", "/v2/**")
		.antMatchers("/v3/api-docs/**", "/swagger-ui/**");
	}

	private static final String[] PERMIT_URL_ARRAY = {
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.cors().disable()
		.authorizeRequests()
		.antMatchers("/api/admin/selectdelete",
					 "/api/admin/autocompetekeyword",
					 "/api/notice/**").hasRole("ADMIN")
		
		.antMatchers("/page/mypage/**").hasRole("USER")
		.antMatchers("/api/board/**","/api/place/**","/api/mypage/**","/page/mypage/**").hasAnyRole("USER","ADMIN")
		.antMatchers(PERMIT_URL_ARRAY).permitAll()
		
		.antMatchers(	
			"/page/main/mainpage",
			"/page/place/list",
			"/page/login/loginpage",
			"/page/login/memberjoin",
			"/api/board/list",
			"/api/notice/list",
			"/istatic/images/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/page/login/loginpage")
		.loginProcessingUrl("/loginProc").permitAll()
		.failureHandler(new LoginFaileHandler())
		.successHandler(new LoginSuccessHandler())
		.and()
		.logout().permitAll()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/page/main/mainpage")
		.invalidateHttpSession(true)
		.deleteCookies("JESSIONID")
		.clearAuthentication(true)
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.maxSessionsPreventsLogin(false);
	}
	
}
