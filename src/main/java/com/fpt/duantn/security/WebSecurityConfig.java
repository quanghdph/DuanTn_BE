package com.fpt.duantn.security;

import com.fpt.duantn.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableConfigurationProperties({AppProperties.class})
public class WebSecurityConfig {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.OPTIONS, "/api/auth/**").permitAll()
                                    .requestMatchers("/product/**").permitAll()
                                    .requestMatchers("/product-detail/**").permitAll()
                                    .requestMatchers("/DashBoard").permitAll()// Cho phép
                                    .requestMatchers("/selloff/**").permitAll()
                                    .requestMatchers("/api/send-mail").permitAll()
                                    .requestMatchers("/cart-detail").permitAll()


                )
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .logout((logout) -> logout.permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
        ;

//        http.csrf(csrf -> csrf.disable())
//                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/api/auth/**").permitAll()
//                                .requestMatchers("/api/test/**").permitAll()
//                                .anyRequest().authenticated()
//                );

//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home")
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll());

//        return http.build();

        // Anyone can access unprotected endponts declared in application.yml
        List<AppProperties.UnprotectedEndpoint> unprotectedEndpoints = appProperties.getSecurity().getUnprotectedEndpoints();
        unprotectedEndpoints.forEach(endpoint -> {
            try {
                String urlPattern = endpoint.getUrlPattern();
                String httpMethod = endpoint.getMethod();
                if (Objects.isNull(httpMethod)) {
                    http.authorizeHttpRequests(auth -> auth
                            .requestMatchers(urlPattern)
                            .permitAll()
                    );
                }

                if (Objects.nonNull(httpMethod)) {
                    http.authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.valueOf(httpMethod), urlPattern)
                            .permitAll()
                    );
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // authorize endpoint declared in application.yml
        List<AppProperties.Authorization> endpointAuthorizations =
                appProperties.getSecurity().getEndpointAuthorizations();
        for (AppProperties.Authorization authorization : endpointAuthorizations) {
            String urlPattern = authorization.getUrlPattern();
            String httpMethod = authorization.getMethod();
            String[] roles = authorization.getRoles().toArray(String[]::new);
            if (Objects.isNull(httpMethod)) {
                http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(urlPattern)
                        .hasAnyRole(roles)
                );
            }

            if (Objects.nonNull(httpMethod)) {
                http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.valueOf(httpMethod), urlPattern)
                        .hasAnyRole(roles)
                );
            }
        }

        return http.build();
    }



}
