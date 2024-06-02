package com.example.demo.image;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Entity
@Table
@Data
public class ImageEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private UserDetails creator;
    private Object image;
    private Date date=new Date();
}
