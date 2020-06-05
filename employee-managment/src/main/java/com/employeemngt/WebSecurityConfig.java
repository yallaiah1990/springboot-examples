package com.employeemngt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employeemngt.jwt.JwtAuthenticationEntryPoint;
import com.employeemngt.jwt.JwtRequestFilter;
import com.employeemngt.jwt.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(name = "auth.flag", matchIfMissing = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/*
	 * configure AuthenticationManager so that it knows from where to load user for
	 * matching credentials Use BCryptPasswordEncoder
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(jwtUserDetailsService);
		} catch (Exception e) {
			logger.info("Credentials Are Not Matched", e);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/generateToken", "/authenticate", "/", "/rabbitmq/**").permitAll()
				// all other requests need to be authenticated
				.antMatchers(HttpMethod.GET, "/css/**", "/js/**", "/fonts/**", "/**/favicon.ico", "/about").permitAll()
				.anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
