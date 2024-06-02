package com.example.demo.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final int PAGE_COUNT=10;
    @Autowired
    private final ImageService service;
    @GetMapping("/all/{page}")
    public Page<ImageEntity> getAll(@PathVariable Integer page){
        return service.getAll(PageRequest.of(page,PAGE_COUNT, Sort.by("date").descending()));
    }
    @GetMapping("/user/{page}")
    public Page<ImageEntity> getMy(Authentication auth, @PathVariable Integer page){
        return service.getByUser((UserDetails) auth.getPrincipal(),PageRequest.of(page,PAGE_COUNT, Sort.by("date").descending()));
    }
}
