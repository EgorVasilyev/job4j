/*
package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.job4j.entity.user.User;
import ru.job4j.service.user.UserService;

import java.util.ArrayList;
import java.util.List;
@Service("provider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Autowired
    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println();
        System.out.println();
        System.out.println("------CustomAuthenticationProvider-----authenticate()---------");
        System.out.println("-----login - " + login);
        System.out.println("-----password - " + password);
        System.out.println();
        System.out.println();

        final User user = userService.isCredential(login, password);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

            System.out.println();
            System.out.println();
            System.out.println("------CustomAuthenticationProvider-----authenticate()---------grantedAuthorities - " + grantedAuthorities);
            System.out.println("-----login - " + login);
            System.out.println("-----password - " + password);
            System.out.println();
            System.out.println();

            return new UsernamePasswordAuthenticationToken(login, password, grantedAuthorities);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
*/
