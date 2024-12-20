package com.example.composer.configuration;

import com.example.composer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;

@Configuration
public class SecurityConfig {

    @Value("${composer.client.id}")
    private String clientId;

    @Value("${composer.client.secret}")
    private String clientSecret;

    @Bean
    public ClientRegistration clientRegistration() {
        return ClientRegistration
                .withRegistrationId("bitbucket")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .userNameAttributeName("username")
                .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                .authorizationGrantType(AUTHORIZATION_CODE)
                .userInfoUri("https://api.bitbucket.org/2.0/user")
                .tokenUri("https://bitbucket.org/site/oauth2/access_token")
                .authorizationUri("https://bitbucket.org/site/oauth2/authorize")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .build();
    }

    @Bean
    public AuthService oAuth2userService() {
        return new AuthService();
    }

    @Bean
    @Autowired
    public ClientRegistrationRepository clientRegistrationRepository(List<ClientRegistration> registrations) {
        return new InMemoryClientRegistrationRepository(registrations);
    }

    @Configuration
    @EnableWebSecurity
    public static class AuthConfig {

        private final AuthService authService;

        @Autowired
        public AuthConfig(AuthService authService) {
            this.authService = authService;
        }

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                            .anyRequest().authenticated()
                    )
                    .oauth2Login(oauth2Login -> oauth2Login
                            .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(authService))
                    );
            return http.build();
        }
    }
}
