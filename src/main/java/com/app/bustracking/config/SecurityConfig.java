package com.app.bustracking.config;

import com.app.bustracking.filter.JwtRequestFilter;
import com.app.bustracking.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity   // enables @PreAuthorize on controllers
public class SecurityConfig {

    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService,
                          JwtRequestFilter jwtRequestFilter,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    // ─────────────────────────────────────────────────────────────
    //  Authentication provider (DaoAuthenticationProvider)
    //  Uses UserService (UserDetailsService) + BCrypt encoder.
    // ─────────────────────────────────────────────────────────────
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        // Uncomment if you ever add an "enabled" flag on UserModel:
        // provider.setHideUserNotFoundExceptions(true);
        return provider;
    }

    // ─────────────────────────────────────────────────────────────
    //  AuthenticationManager (exposed so AuthController can inject it)
    // ─────────────────────────────────────────────────────────────
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // ─────────────────────────────────────────────────────────────
    //  Main security filter chain
    // ─────────────────────────────────────────────────────────────
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Stateless REST API — no CSRF, no sessions
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                // ✅ Explicitly register our DaoAuthenticationProvider so that
                //    AuthenticationManager.authenticate(...) uses it.
                .authenticationProvider(authenticationProvider())

                // ─── URL authorization rules ─────────────────────────
                .authorizeHttpRequests(auth -> auth

                        // Public auth endpoints
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register",
                                "/api/auth/logout"
                        ).permitAll()

                        // CORS preflight must always be allowed
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Static assets — whitelisted in case Spring ever serves them
                        // (normally nginx does, but this stops 401→application/json
                        //  MIME errors if anything slips through)
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/error",
                                "/assets/**",
                                "/static/**",
                                "/favicon.ico",
                                "/vite.svg",
                                "/*.css",
                                "/*.js",
                                "/*.mjs",
                                "/*.png",
                                "/*.jpg",
                                "/*.jpeg",
                                "/*.gif",
                                "/*.svg",
                                "/*.ico",
                                "/*.webp",
                                "/*.avif",
                                "/*.woff",
                                "/*.woff2",
                                "/*.ttf",
                                "/*.eot",
                                "/*.map"
                        ).permitAll()

                        // Everything else requires a valid JWT
                        .anyRequest().authenticated()
                )

                // Stateless — no HTTP session, no JSESSIONID
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // JSON 401 / 403 responses instead of Spring's default HTML
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) -> {
                            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            res.setContentType("application/json");
                            res.getWriter().write(
                                    "{\"error\":\"Unauthorized - missing or invalid token\"}"
                            );
                        })
                        .accessDeniedHandler((req, res, e) -> {
                            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            res.setContentType("application/json");
                            res.getWriter().write(
                                    "{\"error\":\"Access denied - insufficient role\"}"
                            );
                        })
                )

                // Plug the JWT filter in before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}