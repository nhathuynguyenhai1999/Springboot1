package cg.codegym.minitest.springboot2.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
public class ComputerUserJwt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Type> typeSet;

    public ComputerUserJwt(Long id, String username, String password, Set<Type> typeSet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeSet = typeSet;
    }
    public ComputerUserJwt() {}
}
