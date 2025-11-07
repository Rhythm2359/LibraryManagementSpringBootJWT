package com.example.libraryManagement.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="books")

public class BookDTO {

    private String title ;
    private String author ;
    private String isbn ;
    private Integer quantity ;
    private boolean isAvailable ;

}
