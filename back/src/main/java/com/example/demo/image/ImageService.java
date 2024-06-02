package com.example.demo.image;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {
    @Autowired
    private static ImageRepository repository;
    public Page<ImageEntity> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Page<ImageEntity> getByUser(UserDetails user, Pageable pageable){
        return repository.findAllByCreator(user,pageable);
    }
}
