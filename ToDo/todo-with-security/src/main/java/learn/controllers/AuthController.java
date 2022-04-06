package learn.controllers;

import learn.domain.UserService;
import learn.security.JwtConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/security")
public class AuthController {

    AuthenticationManager authenticationManager;
    JwtConverter converter;
    UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.userService = userService;
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Map<String, String> credentials){
        throw new UnsupportedOperationException();
    }
}
