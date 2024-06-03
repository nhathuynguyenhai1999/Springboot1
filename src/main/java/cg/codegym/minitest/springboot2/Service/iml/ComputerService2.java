package cg.codegym.minitest.springboot2.Service.iml;

import cg.codegym.minitest.springboot2.Jwt.ComputerUserPrinciple;
import cg.codegym.minitest.springboot2.Model.ComputerUserJwt;
import cg.codegym.minitest.springboot2.Repository.IComputerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ComputerService2 implements UserDetailsService {
    @Autowired
    private IComputerUserRepository iComputerUserRepository;

    public ComputerUserJwt findByUsername(String name){
        return iComputerUserRepository.findByUsername(name);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ComputerUserPrinciple.build(iComputerUserRepository.findByUsername(username));
    }
}
