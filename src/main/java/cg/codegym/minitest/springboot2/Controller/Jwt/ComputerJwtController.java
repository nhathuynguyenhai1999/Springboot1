package cg.codegym.minitest.springboot2.Controller.Jwt;

import cg.codegym.minitest.springboot2.Model.Computer;
import cg.codegym.minitest.springboot2.Service.iml.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/jwt/computers")
public class ComputerJwtController {
    @Autowired
    private IComputerService computerService;

    @GetMapping("")
    public ResponseEntity<Iterable<Computer>> findAll() {
        return new ResponseEntity<>(computerService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Computer> finOne(@PathVariable Long id) {
        Optional<Computer> c = computerService.findById(id);
        return new ResponseEntity<>(computerService.findByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Computer computer) {
        computerService.save(computer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        computerService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
