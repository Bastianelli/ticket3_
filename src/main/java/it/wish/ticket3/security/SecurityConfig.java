package it.wish.ticket3.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder()).usersByUsernameQuery("SELECT username,password, enabled from users where username=?")
		//        .authoritiesByUsernameQuery("SELECT username, authority from authorities where username=?");
		//predisposto per schemapersonalizzato
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
		.antMatchers("/error")
		.antMatchers("/");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.successHandler(myAuthenticationSuccessHandler())
		.permitAll()
		.and()
		.logout()
		.permitAll();


		//		.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		//		.antMatchers("/admin**.html").hasAuthority("ROLE_ADMIN")
		//        .antMatchers("/user/**").hasAuthority("ROLE_USER")
		//        .antMatchers("/user**.html").hasAuthority("ROLE_USER")

		//http.exceptionHandling().accessDeniedPage("/errore");
	}
	//	@Bean
	//	public PasswordEncoder gePasswordEncoder() {
	//		return NoOpPasswordEncoder.getInstance();
	//	}
	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		return new MySimpleUrlAuthenticationSuccessHandler();
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