package com.loginPage.LoginPageBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    CustomAuthManager authManager;
    @Autowired
    CustomUserDetailService userDetailsService;

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/signUp").permitAll().antMatchers(HttpMethod.POST, "/login").permitAll();
        http.csrf().disable();
        http.authenticationManager(authManager);
        http.userDetailsService(userDetailsService);
        http.logout(logout -> logout.invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("https://loginpagedemo-25d91.as.r.appspot.com/logoutSuccess").addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        response.setHeader("Access-Control-Allow-Origin", "https://phenomenal-pony-11b2ff.netlify.app");
                        response.setHeader("Access-Control-Allow-Credentials", "true");
                        response.setHeader("location", "https://loginpagedemo-25d91.as.r.appspot.com/logoutSuccess");
                        response.setHeader("set-cookie", "JSESSIONID=; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:10 GMT; Path=/; Secure; SameSite=None");
                    }
                }));

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        serializer.setUseSecureCookie(true);
        serializer.setUseHttpOnlyCookie(false);
        serializer.setSameSite("None");
        return serializer;
    }
}







