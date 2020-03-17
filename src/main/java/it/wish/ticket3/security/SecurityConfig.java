package it.wish.ticket3.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder()).usersByUsernameQuery("SELECT username,password, enabled from users where username=?")
//        .authoritiesByUsernameQuery("SELECT username, authority from authorities where username=?");
		auth.jdbcAuthentication()
			.dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
			.usersByUsernameQuery("SELECT username,password, enabled "
					+ "from users "
					+ "where username=?")
	        .authoritiesByUsernameQuery("SELECT username, authority "
	        		+ "from authorities "
	        		+ "where username=?");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/index.html")
		.antMatchers("/tecnico/all")
		.antMatchers("/");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/admin**.html").hasAuthority("ROLE_ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	@Bean
	public PasswordEncoder gePasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//				User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password1")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}