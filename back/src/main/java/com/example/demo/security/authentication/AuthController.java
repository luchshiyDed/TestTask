package com.example.demo.security.authentication;


import com.example.demo.security.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserEntity request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        authenticationService.deleteUser(id);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
