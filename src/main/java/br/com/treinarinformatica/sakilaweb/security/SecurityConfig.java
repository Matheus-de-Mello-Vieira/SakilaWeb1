/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinarinformatica.sakilaweb.security;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

/**
 *
 * @author ADM
 */
@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private SakilaAuthenticationProvider sakilaAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuider) {
        authBuider.authenticationProvider(sakilaAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/faces/login.xhml")
                .permitAll()
                .antMatchers("/faces/**").hasRole("USER")
                .antMatchers("/ws/**").hasRole("USER")
                .antMatchers("/**")
                .permitAll().anyRequest()
                .authenticated()
                .and()
                .logout().logoutSuccessUrl("/faces/login.xhtml")
                .logoutUrl("/logout")
                .and()
                .formLogin()
                .and()
                .formLogin().loginPage("/faces/login.xhtml")
                .loginProcessingUrl("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/faces/films.xhtml")
                .permitAll()
                .failureUrl("/faces/login.xhtml?erro=1")
                .permitAll();

    }
}
