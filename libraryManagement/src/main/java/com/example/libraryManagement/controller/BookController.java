package com.example.libraryManagement.controller;

import com.example.libraryManagement.dto.BookDTO;
import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService ;

    @PostMapping("/getallbooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks()) ;
    }

    @PostMapping("/getbookbyid/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id)) ;
    }

    @PostMapping("/addbook")
    @PreAuthorize("hasrole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.addBook(bookDTO)) ;
    }

    @PutMapping("updatebook/{id}")
    @PreAuthorize("hasrole('ADMIN')")
    public  ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.updateBook(id , bookDTO)) ;
    }

    @DeleteMapping("deletebook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id) ;
        return ResponseEntity.ok().build() ;
    }

}
