package com.binarybeasts.voyalsuper.security.config;

import com.binarybeasts.voyalsuper.security.jwt.JwtAuthEntryPoint;
import com.binarybeasts.voyalsuper.security.jwt.JwtRequestFilter;
import com.binarybeasts.voyalsuper.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService userDetailsService;

    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    public WebSecurityConfig(JwtUserDetailsService userDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).
                and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
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


}
