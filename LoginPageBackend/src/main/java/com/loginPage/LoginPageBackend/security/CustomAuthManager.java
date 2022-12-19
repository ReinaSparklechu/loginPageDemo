package com.loginPage.LoginPageBackend.security;

import com.loginPage.LoginPageBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthManager implements AuthenticationManager {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomUserDetailService userDetailService;
    @Autowired
    @Lazy
    PasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("printing in auth manager");
        var user = userDetailService.loadUserByUsername(authentication.getName());
        if(user== null) {
            System.out.println("User not found");
            throw new AuthenticationException("User not found") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
        if(encoder.matches((String)authentication.getCredentials(),user.getPassword())){
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(), user.getAuthorities());
        } else {
            System.out.println("PASSWORD MISMATCH");
            throw new AuthenticationCredentialsNotFoundException("password mismatch");
        }

    }

}
