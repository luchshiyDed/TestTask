package com.example.demo.security.user;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    public UserEntity getById(Long id){
        Optional<UserEntity> u=repository.findById(id);
        return u.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user=repository.findByName(username);
        if(user.isEmpty())
          throw new UsernameNotFoundException(username);
        return user.get();
    }
}
