package com.xwing.springbootproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug=false)
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
    
	@Bean
	UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user")
				.password(bCryptPasswordEncoder.encode("userPass"))
				.roles("USER")
				.build());
		manager.createUser(User.withUsername("admin")
				.password(bCryptPasswordEncoder.encode("adminPass"))
				.roles("USER", "ADMIN")
				.build());
		return manager;
	}
	
	
	@Bean
	@Order(1)
	SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher(new AntPathRequestMatcher("/api/**"))
				.authorizeHttpRequests(auth -> {
					auth.anyRequest().permitAll();//.authenticated();
					//auth.anyRequest().authenticated();
				})
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.build();
	}

	
//  @Bean
//  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//      UserDetails user = User.withUsername("user")
//          .password(passwordEncoder.encode("userPass"))
//          .roles("USER")
//          .build();
//      return new InMemoryUserDetailsManager(user);
//  }
	
//	@Bean
//	@Order(2)
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.authorizeHttpRequests(auth -> {
//					auth.requestMatchers("/search").authenticated();
//					auth.anyRequest().authenticated();
//				})
//				.formLogin(Customizer.withDefaults())
//				.build();
//	}

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//        		.authorizeHttpRequests(request -> 
//            request.requestMatchers(new AntPathRequestMatcher("/api/spaceships/**"))
//            .permitAll())
//        		.authorizeHttpRequests(request -> 
//        	request.requestMatchers(new AntPathRequestMatcher("/search/**"))
//        	.authenticated())
//                //.hasRole("ADMIN"))
//            .httpBasic(Customizer.withDefaults())
//            .build();
//    }
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(AbstractHttpConfigurer::disable)
//			.authorizeHttpRequests(request ->
//			request.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//			.requestMatchers("/admin/**").hasAnyRole("ADMIN")
//			.requestMatchers("/api/spaceships/**").permitAll()
//			.requestMatchers("/login/**").permitAll()
//			.requestMatchers(HttpMethod.GET, "/search/**").hasRole("USER")
//			.anyRequest().authenticated())
//		.httpBasic(Customizer.withDefaults())
//		.sessionManagement(httpSecuritySessionManagementConfigurer -> 
//		httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//		return http.build();
//	
//	}

}
