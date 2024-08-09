package cg.codegym.minitest.springboot2.Service.iml;

import cg.codegym.minitest.springboot2.Jwt.ComputerPrinciple;
import cg.codegym.minitest.springboot2.Model.Computer;
import cg.codegym.minitest.springboot2.Repository.IComputerRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ComputerService1 implements UserDetailsService {

    @Autowired
    private IComputerRepository1 computerRepository;

    public Computer findByName(String name){
        return computerRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ComputerPrinciple.build(computerRepository.findByName(username));
    }
}
