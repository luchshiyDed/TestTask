package com.example.demo.image;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
    public Page<ImageEntity> findAllByCreator(UserDetails creator, Pageable pageable);
}
