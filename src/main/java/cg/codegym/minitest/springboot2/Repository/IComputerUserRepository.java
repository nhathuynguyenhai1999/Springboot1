package cg.codegym.minitest.springboot2.Repository;

import cg.codegym.minitest.springboot2.Model.ComputerUserJwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComputerUserRepository extends JpaRepository<ComputerUserJwt, Long> {
    ComputerUserJwt findByUsername(String username);
}
