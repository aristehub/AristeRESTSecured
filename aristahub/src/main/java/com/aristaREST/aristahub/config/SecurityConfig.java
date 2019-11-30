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
import com.aristaREST.aristahub.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	@Autowired
	private CustomAuthenticationSuccessHandler userPS;
	
	// add a reference to our security data source
    @Autowired
    private UserService userService;
	
	/*
	 * -------------------------------------------------------	
	 * 					CONSTRUCTOR
	 * -------------------------------------------------------
	 */
	
	public SecurityConfig(CustomAuthenticationSuccessHandler ups)
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
		https.authorizeRequests()
		.antMatchers("/index.html").permitAll()
		.antMatchers("/profile/**").authenticated()
		.antMatchers("/admin/**").hasRole("Admin")
		.antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
		.antMatchers("/api/products").permitAll()
		.antMatchers("/api//product/{product_id}").hasRole("USER")
		.and()
		.formLogin().loginProcessingUrl("/signin").loginPage("/login").successHandler(userPS).permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
		.and()
		.rememberMe().tokenValiditySeconds(2592000).key("cs697@FALL!").rememberMeParameter("checkRememberMe")
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
	//get bean for authentication provider of the DataBase, password encoder of the query
	@Bean
	public DaoAuthenticationProvider authProvider() 
	{
		//get authentication provider object
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(passwordEncoder());  //set the password encoder - bcrypt
		daoAuth.setUserDetailsService(userService); //service set..
		return daoAuth;
	}
	//Password encoder...
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		//type of encoder...
		return new BCryptPasswordEncoder();
	}
}
