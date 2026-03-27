package com.proyecto.cebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/about", "/contact", "/index", "/login", "/css/**", "/js/**",
                                                "/img/**", "/uploads/**")
                                .permitAll()

                                .requestMatchers("/admin/index", "/admin/changepfp")
                                .hasAnyAuthority("Director", "Docente")

                                .requestMatchers(
                                                "/admin/addpers", "/admin/apoderado", "/admin/apoderado-pdf",
                                                "/admin/curso", "/admin/editpers", "/admin/estudiantedetail",
                                                "/admin/ficha-estudiante-pdf", "/admin/matricula", "/admin/mensajes",
                                                "/admin/perslist", "/admin/seccion", "/admin/studentlist")
                                .hasAuthority("Director")

                                .requestMatchers(
                                                "/admin/mis-estudiantes-pdf", "/admin/notas-pdf", "/admin/notas",
                                                "/admin/notasedit", "/admin/studentasigned", "/admin/misestudiantes")
                                .hasAuthority("Docente")

                                .anyRequest().authenticated())

                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .usernameParameter("username")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/admin/index", true)
                                                .failureUrl("/login?error=true")
                                                .permitAll())

                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login/logout?=true")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
