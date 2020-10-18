package com.zup.zupbank.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws  Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //Permite acesso a rota sem autenticacao
                .antMatchers("/conta/nova", "/conta", "/auth/csrf").permitAll()
                //bloqueia as rotas em diante
                .anyRequest().authenticated();
    }
}
