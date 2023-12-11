package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


import tacos.User;
import tacos.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null)
				return user;
			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.antMatchers(HttpMethod.POST, "/api/ingredients")
				.hasAuthority("SCOPE_writeIngredients").antMatchers(HttpMethod.DELETE, "/api/ingredients")
				.hasAuthority("SCOPE_deleteIngredients").anyRequest().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt());
	}

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//				.oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/taco-admin-client"))
//				.oauth2Client(withDefaults());
//		return http.build();
//	}

	// 1- Spring Security Configuration

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http.authorizeHttpRequests().antMatchers("/design", "/orders").hasRole("USER").antMatchers("/", "/**")
//				.permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/design", true).and().csrf()
//				.disable().logout().and().build();
//	}

	// OAth2 Resource Server:

}
