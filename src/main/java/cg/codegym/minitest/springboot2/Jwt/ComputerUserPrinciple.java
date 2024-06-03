package cg.codegym.minitest.springboot2.Jwt;

import java.io.Serial;

import cg.codegym.minitest.springboot2.Model.ComputerUserJwt;
import cg.codegym.minitest.springboot2.Model.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ComputerUserPrinciple implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;

    public ComputerUserPrinciple(String username, String password,
                         Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static ComputerUserPrinciple build(ComputerUserJwt user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Type role : user.getTypeSet()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new ComputerUserPrinciple(user.getUsername(),
                user.getPassword(),authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
