package net.javagudies.todo.security;


import lombok.AllArgsConstructor;
import net.javagudies.todo.Entity.TodoUser;
import net.javagudies.todo.Repositary.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
private UserRepo ur;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        TodoUser usr= (TodoUser) ur.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).
                orElseThrow(()->new UsernameNotFoundException("User Not Found and even "));

        Set<GrantedAuthority> authorities = usr.getRoles().stream()
                .map((yo) -> {
                    return new SimpleGrantedAuthority(yo.getName());
                })
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail, usr.getPassword(),authorities

        );
    }
}








