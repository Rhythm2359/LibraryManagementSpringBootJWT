package com.example.libraryManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String title ;
    private String author ;
    private String isbn ;
    private Integer quantity ;
    private boolean isAvailable ;
}
