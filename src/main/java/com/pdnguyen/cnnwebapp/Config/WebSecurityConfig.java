package com.pdnguyen.cnnwebapp.Config;

import com.pdnguyen.cnnwebapp.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Config SpringSecurity for Webapp
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /***
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * @param authentication
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http.csrf().disable();

     //Disable Security Filter, no authorization required
     //Note: Permitting public access to /api to debug with Postman
     http.authorizeRequests()
             .antMatchers("/api", "/login", "/logout").permitAll()
             //Both roles can access
             .antMatchers( "/welcomePage", "/","/user-list", "/change-password").authenticated()
             //Only Professors can access
             .antMatchers( "/add-user", "/edit-user").access("hasRole('ROLE_PROF')")
             .and().exceptionHandling().accessDeniedPage("/403")
             //Config log in form
             .and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                //logout
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");;
    }
}
