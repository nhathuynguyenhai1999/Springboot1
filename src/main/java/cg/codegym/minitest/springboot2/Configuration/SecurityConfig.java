package cg.codegym.minitest.springboot2.Configuration;

import cg.codegym.minitest.springboot2.Service.iml.ComputerService1;
import cg.codegym.minitest.springboot2.Configuration.CustomAccessDeniedHandler;
import cg.codegym.minitest.springboot2.Configuration.JwtAuthenticationTokenFilter;
import cg.codegym.minitest.springboot2.Configuration.RestAuthenticationEntryPoint;
import cg.codegym.minitest.springboot2.Service.iml.ComputerService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private ComputerService2 computerService1;
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(computerService1);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .requestMatchers("/api/auth/login/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/customers**").authenticated()
//                        .requestMatchers("/api/customers**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/customers**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/customers**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/customers**").hasAnyAuthority("ROLE_ADMIN")
                )
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(customAccessDeniedHandler()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
