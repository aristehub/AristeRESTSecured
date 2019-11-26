package com.aristaREST.aristahub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private UserPrincipalDetailsService userPS;
	
	/*
	 * -------------------------------------------------------	
	 * 					CONSTRUCTOR
	 * -------------------------------------------------------
	 */
	@Autowired
	public SecurityConfig(UserPrincipalDetailsService ups)
	{
		this.userPS = ups;
	}
	
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
	//configure Authentication user type
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authProvider());
		
	}
	//protect resources that Ariste Hub exposes, options of every type of role and authorizations
	@Override
	protected void configure(HttpSecurity https) throws Exception
	{
		https
			.authorizeRequests()
			.antMatchers("/index.html").permitAll()
			.antMatchers("/profile/**").authenticated()
			.antMatchers("/admin/**").hasRole("Admin")
			.antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
			.antMatchers("/api/products").hasAuthority("ACCESS_TEST1")
			.antMatchers("/api//product/{product_id}").hasAuthority("ACCESS_TEST2")
			.antMatchers("/api/public/users").hasRole("ADMIN")
			.and()
			.formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll().usernameParameter("txtUsername").passwordParameter("txtPassword")
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.and()
			.rememberMe().tokenValiditySeconds(2592000).key("cs697@FALL!").rememberMeParameter("checkRememberMe");
	}
	//get bean for authentication provider of the DataBase, password encoder of the query
	@Bean
	DaoAuthenticationProvider authProvider() 
	{
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(passwordEncoder());
		daoAuth.setUserDetailsService(this.userPS);
		
		return daoAuth;
	}
	//Password encoder...
	@Bean
	PasswordEncoder passwordEncoder()
	{
		//type of encoder...
		return new BCryptPasswordEncoder();
	}
}
