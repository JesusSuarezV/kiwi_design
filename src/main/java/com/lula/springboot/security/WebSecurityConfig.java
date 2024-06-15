package com.lula.springboot.security;

import com.lula.springboot.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/js/**", "/css/**", "/img/**", "/Iniciar_Sesion").permitAll()
                                .requestMatchers("/Registrarse/**", "/Test", "/Confirmacion/**").anonymous()

                                //.requestMatchers("/personas/nueva").hasAnyAuthority("ADMIN","CREATOR")
                                //.requestMatchers("/personas/editar/*").hasAnyAuthority("ADMIN","EDITOR")
                                //.requestMatchers("/personas/eliminar/*").hasAnyAuthority("ADMIN")
                                .requestMatchers("/Categorias/**", "/Productos/**", "/Usuarios/**").hasAnyAuthority("ADMINISTRADOR", "SUPERADMIN")
                                .requestMatchers("/Productos_Descuento/**", "/Codigos/**").hasAnyAuthority("MARKETING", "SUPERADMIN")

                                .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/Iniciar_Sesion")
                        .permitAll())
                .logout(logout -> logout
                        // Configure logout behavior here
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .clearAuthentication(true)   // Clear SecurityContext on logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/Cerrar_Sesion")) // Custom logout URL
                        .logoutSuccessUrl("/?logout")  // Redirect after successful logout
                );
        return httpSecurity.build();
    }
}