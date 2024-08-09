package cg.codegym.minitest.springboot2.Controller.Jwt;

import cg.codegym.minitest.springboot2.Jwt.JwtResponse;
import cg.codegym.minitest.springboot2.Jwt.JwtService;
import cg.codegym.minitest.springboot2.Model.Computer;
import cg.codegym.minitest.springboot2.Service.iml.ComputerService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ComputerService1 computerService1;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Computer user) {
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getCode()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Computer currentUser = computerService1.findByName(user.getName());
        return ResponseEntity.ok(new JwtResponse(userDetails.getAuthorities(), currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getUsername()));
    }
}
