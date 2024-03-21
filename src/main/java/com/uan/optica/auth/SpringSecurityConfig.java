package com.uan.optica.auth;


import com.uan.optica.auth.filters.JwtAuthenticationFilter;
import com.uan.optica.auth.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    /**Este metodo genera un componente de spring */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeRequests()
            .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuarios/verificarCodigoRecuperacion").permitAll()
                .requestMatchers(HttpMethod.PUT, "/usuarios/actualizarContrasena").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios/nueva").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/calendario/nueva").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/calendario//modificar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/preguntas/nueva").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/preguntas/modificar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/preguntas/eliminar/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/preguntas/listaPreguntas").permitAll()
                .requestMatchers(HttpMethod.GET, "/calendario/duracioncita/{dia}").hasRole("PACIENTE")
                .requestMatchers(HttpMethod.POST, "/paciente/nueva").permitAll()
                .requestMatchers(HttpMethod.POST, "/cita/nueva").permitAll()






                .anyRequest().authenticated()
            .and()
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))

                .csrf(config -> config.disable())
            .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
