package cg.codegym.minitest.springboot2.Jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Setter
@Getter
public class JwtResponse {

    private Long id;
    private String name;
    private String type = "Bearer";
    private String code;
    private final Collection<? extends GrantedAuthority> authorities;
    public JwtResponse(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    public JwtResponse(Collection<? extends GrantedAuthority> authorities, Long id, String name, String type, String code) {
        this.authorities = authorities;
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
    }
}
