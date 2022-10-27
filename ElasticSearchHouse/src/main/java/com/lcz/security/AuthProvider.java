package com.lcz.security;

import com.lcz.entity.User;
import com.lcz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义认证实现
 *
 */
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserService userService;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        if (this.passwordEncoder.matches(inputPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } else {
            throw new BadCredentialsException("authError");
        }
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
