//package com.mobileshop.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableMethodSecurity
//public class UserAuthentication {
//
//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        return new UserDetailsServiceImpl();
//    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return passwordEncoder;
//    }
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/").hasAnyAuthority("Admin","Store Manager","Customer","Sales Associate")
//                .requestMatchers("/new/**").hasAnyAuthority("Admin","Store Manager")
//                .requestMatchers("/delete/**").hasAnyAuthority("Admin","Store Manager")
//                .requestMatchers("/update/**").hasAnyAuthority("Sales Associate")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//        ;
//        return http.build();
//    }
//
////    protected void configure(HttpSecurity http) throws Exception{
////        http.authorizeHttpRequests()
////                .requestMatchers("/").hasAnyAuthority("Admin","Store Manager","Customer","Sales Associate")
////                .requestMatchers("/new/**").hasAnyAuthority("Admin","Store Manager")
////                .requestMatchers("/delete/**").hasAnyAuthority("Admin","Store Manager")
////                .requestMatchers("/update/**").hasAnyAuthority("Sales Associate")
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().permitAll()
////                .and()
////                .exceptionHandling().accessDeniedPage("/403")
////        ;
////    }
//
//
//}
