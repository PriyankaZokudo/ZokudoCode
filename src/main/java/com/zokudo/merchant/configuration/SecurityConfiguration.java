package com.zokudo.merchant.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @Autowired
	 private DataSource dataSource;
	 
	 private final String USERS_QUERY = "SELECT email, password,'true' as enabled FROM merchant_user WHERE status='ACTIVE' and email=?";
	 private final String ROLES_QUERY = "SELECT mu.email,r.name FROM merchant_user mu INNER JOIN `merchant_user_has_role` mhr ON mhr.merchant_user_id = mu.id INNER JOIN role r ON r.id = mhr.role_id WHERE mu.email=?";
	 
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.jdbcAuthentication()
	   .usersByUsernameQuery(USERS_QUERY)
	   .authoritiesByUsernameQuery(ROLES_QUERY)
	   .dataSource(dataSource)
	   .passwordEncoder(bCryptPasswordEncoder);
	 }
	 
	 protected void configure(HttpSecurity http) throws Exception{
	  http.authorizeRequests()
	   .antMatchers("/","/login","/forgot-password","/signup","/assets/**").permitAll()
	   .antMatchers("/merchant/**").hasAuthority("MERCHANT").anyRequest()
	   .authenticated().and().csrf().disable()
	   .formLogin().loginPage("/login").failureUrl("/login?error=true")
	   .defaultSuccessUrl("/merchant/dashboard")
	   .usernameParameter("email")
	   .passwordParameter("password")
	   .and().logout()
	   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	   .logoutSuccessUrl("/login")
	   .and().rememberMe()
	   .tokenRepository(persistentTokenRepository())
	   .tokenValiditySeconds(60*60)
	   .and().exceptionHandling().accessDeniedPage("/access_denied").and().httpBasic();
	 }
		 
	 @Bean
	 public PersistentTokenRepository persistentTokenRepository() {
	  JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	  db.setDataSource(dataSource);
	  
	  return db;
	}
}
