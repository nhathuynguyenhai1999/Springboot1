package cg.codegym.minitest.springboot2.Jwt;

import cg.codegym.minitest.springboot2.Model.Computer;
import cg.codegym.minitest.springboot2.Model.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComputerPrinciple implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public ComputerPrinciple(String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetails build(Computer computer) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Type role : computer.getTypes()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new ComputerPrinciple(computer.getName(),
                computer.getCode(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
