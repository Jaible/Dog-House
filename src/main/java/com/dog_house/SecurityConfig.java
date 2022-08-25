/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dog_house;

import com.dog_house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserService getUserService(){
        return new UserService();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(getUserService());
        return daoAuthenticationProvider;
    }
    
    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler(){
        return new AppAuthenticationSuccessHandler();
    }
    
    public SecurityConfig(UserService userPrincipalDetailService){
        this.userDetailsService = userPrincipalDetailService;
    }
    
    @Override
    protected void configure (AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests((requests) -> requests.antMatchers("/UserProfile").permitAll()
                                                     .antMatchers("/reservacion").authenticated()
                                                     .antMatchers("/").permitAll()
                                                     .antMatchers("/contacto").permitAll()
                                                     .antMatchers(HttpMethod.GET, "/testimonios/**").hasAnyRole("ADMIN","USER")
                                                     .antMatchers(HttpMethod.POST, "/testimonios/**").hasAnyRole("ADMIN","USER")
                                                     .antMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                                                     .antMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN"))
                                                     .cors().and().csrf().disable();
        http.formLogin();
//        http.authorizeRequests()
//                .antMatchers("/admin/habitaciones", "/assets/**")
//                .hasRole("ADMIN")
//                .antMatchers("/login","/assets")
//                .hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                //.formLogin().successHandler(appAuthenticationSuccessHandler());
//                .formLogin().defaultSuccessUrl("/UserProfile",true);
    }
    
    
}
