package com.example.demo.security.authentication;


import com.example.demo.security.JWTService;
import com.example.demo.security.user.UserEntity;
import com.example.demo.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String jwt=jwtService.generateToken(new HashMap<>(),user);
        AuthenticationResponse response=new AuthenticationResponse();
        response.setToken(jwt);
        return response;
    }
    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public void edit(UserEntity user,Long id){

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword()));
        var user=userRepository.findByName(request.getLogin()).orElseThrow();
        String jwt=jwtService.generateToken(new HashMap<>(),user);

        AuthenticationResponse response=new AuthenticationResponse();
        response.setToken(jwt);
        return response;
    }
}
