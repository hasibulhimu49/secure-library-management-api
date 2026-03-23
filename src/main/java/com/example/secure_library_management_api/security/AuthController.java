package com.example.secure_library_management_api.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return user;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        log.info(loginRequest.username());
        log.info(loginRequest.password());
        User user= repository.findByUsername(loginRequest.username()).orElseThrow(()->new RuntimeException("Not found"));
        log.info(user.getUsername());

        if(!passwordEncoder.matches(loginRequest.password(), user.getPassword()))
        {
            return ResponseEntity.status(401).body("Invalid Username or password");
        }


        String token=jwtService.generateToken(new CustomUser(user));

        return ResponseEntity.ok(new HashMap<>(){{
            put("message","Login Success");
            put("token",token);
        }});
    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout()
    {
        return ResponseEntity.ok("Logout Successfully");
    }


}
