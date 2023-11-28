package br.com.erudio.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtConfigurer {
    // extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
    // https://cursos.alura.com.br/forum/topico-websecurityconfigureradapter-deprecated-222772

	@Autowired
	private JwtTokenProvider tokenProvider;

	public JwtConfigurer(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	/*
	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
	*/
	
    @Bean
    SecurityFilterChain addFilterBefore(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}