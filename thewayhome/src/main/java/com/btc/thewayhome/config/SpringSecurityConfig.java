package com.btc.thewayhome.config;

import jakarta.servlet.DispatcherType;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.formLogin().disable();


		return http.build();
	}

	/*@Bean
	@Order(1)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("filterChain");

		http.csrf().disable()
				.cors().disable()
				.authorizeHttpRequests(request -> request
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
						.requestMatchers("/css/**", "/error/**", "/img/**", "/js/**", "", "/",
								"/user/member/create_account_form", "/user/member/create_account_confirm").permitAll()   // Security 제외
						.anyRequest().authenticated()
				)
				.formLogin(login -> login                           // 로그인 시 폼(form)을 이용
						.loginPage("/user/member/member_login_form")            // 로그인시 폼 주소 설정
						.loginProcessingUrl("/member_login_confirm")
						.usernameParameter("u_m_id")
						.passwordParameter("u_m_pw")
						.defaultSuccessUrl("/", true)
						.permitAll())
				.logout()
				.logoutUrl("/user/member/member_logout_confirm")
				.logoutSuccessUrl("/");
		return http.build();
	}
	@Bean
	@Order(2)
	public SecurityFilterChain filterChainAdmin(HttpSecurity http) throws Exception {
		log.info("filterChain");

		http.csrf().disable()
				.cors().disable()
				.authorizeHttpRequests(request -> request
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
						.requestMatchers("/css/**", "/error/**", "/img/**", "/js/**", "", "/",
								"/admin/member/create_account_form", "/admin/member/create_account_confirm").permitAll()   // Security 제외
						.anyRequest().authenticated()
				)
				.formLogin(login -> login                           // 로그인 시 폼(form)을 이용
						.loginPage("/admin/member/member_login_form")            // 로그인시 폼 주소 설정
						.loginProcessingUrl("/member_login_confirm")
						.usernameParameter("a_m_id")
						.passwordParameter("a_m_pw")
						.defaultSuccessUrl("/", true)
						.permitAll())
				.logout()
				.logoutUrl("/admin/member/member_logout_confirm")
				.logoutSuccessUrl("/");
		return http.build();
	}
*/

}
