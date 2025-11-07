package com.example.libraryManagement.repository;


import com.example.libraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
