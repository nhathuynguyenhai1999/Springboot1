package cg.codegym.minitest.springboot2.Repository;

import cg.codegym.minitest.springboot2.Model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComputerRepository1 extends JpaRepository<Computer, Long> {
    Computer findByName(String name);
}
