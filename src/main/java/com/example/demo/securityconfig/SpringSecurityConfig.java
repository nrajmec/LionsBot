package com.example.demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER")
				.and()
				.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}

	// Authorization : mention which role can access which URL
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/customers").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/customers").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/customers/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/customers/changepassword/**").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/orders/customer/**").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/orders/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/orders").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/orders/update/**").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.DELETE, "/orders/delete/**").hasAnyRole("ADMIN")

				.and().formLogin().permitAll()
				.and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/403")
				.and().csrf().disable().headers()
				.frameOptions().disable();
	}

}
