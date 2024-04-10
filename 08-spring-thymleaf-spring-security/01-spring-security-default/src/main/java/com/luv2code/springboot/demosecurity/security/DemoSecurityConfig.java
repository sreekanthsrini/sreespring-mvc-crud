package com.luv2code.springboot.demosecurity.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig { 
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // it is without custome concept
        // return new JdbcUserDetailsManager(dataSource);
        // for custome concept to get the role from db
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        // define query to reterive the user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        // define query to reterive the authorize roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return jdbcUserDetailsManager;
    }
   
        
  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure -> configure
        .requestMatchers("/").hasRole("EMPLOYEE")
        .requestMatchers("/leaders/**").hasRole("MANAGER")
        // .requestMatchers("/systems/**").hasRole("ADMIN")
        .requestMatchers("/systems/**").hasRole("ADMIN")
        .requestMatchers("/empl/**").hasRole("EMPLOYEE")
        .requestMatchers("/char/**").hasRole("CHAIRMAN")
        .anyRequest().authenticated()
        )
        .formLogin(form->
        form
        .loginPage("/showMyLoginPage")
        .loginProcessingUrl("/authenticateTheUser")
        .permitAll()
        )
        .logout(logout ->logout.permitAll()
        )
        .exceptionHandling(configure ->
        configure.accessDeniedPage(("/access-denied")));
        return http.build();
    }


    
}
 // @Bean
    // public InMemoryUserDetailsManager userDetailsManager()
    // {

    //     UserDetails john = User.builder()
    //           .username("john")
    //           .password("{noop}john123")
    //           .roles("EMPLOYEE")
    //           .build();


    //       UserDetails marry =User.builder()
    //              .username("marry")
    //              .password("{noop}marry123")
    //              .roles("EMPLOYEE","ADMIN")    
    //              .build();

    //         UserDetails susan =User.builder()
    //         .username("susan")
    //         .password("{noop}susan123")
    //         .roles("EMPLOYEE","ADMIN","MANAGER")
    //         .build();
    //         UserDetails sree =User.builder()
    //         .username("sree")
    //         .password("{noop}sree123")
    //         .roles("EMPLOYEE","ADMIN","MANAGER","OWNER")
    //         .build();
            
    //         UserDetails srini=User.builder()
    //         .username("srini")
    //         .password("{noop}srini123")
    //         .roles("EMPLOYEE","ADMIN","MANAGER","CHAIRMAN")
    //         .build();
    //         return new InMemoryUserDetailsManager(john,marry,susan,sree,srini);
            
    // }
