package com.example.secure_library_management_api.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class CustomUser implements UserDetails {

    private final User user;

    public User getUser() {
        return user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
       //return Collections.emptyList();
        return List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
    }


    public String getPassword()
    {
        return user.getPassword();
    }


    public String getUsername()
    {
        return user.getUsername();
    }


}
