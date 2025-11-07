package com.example.libraryManagement.service;

import com.example.libraryManagement.dto.BookDTO;
import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository ;

    public List<Book> getAllBooks(){
        return bookRepository.findAll() ;
    }

    public Book getBookById(Long id){
        Book book = bookRepository.findById(id).
                orElseThrow(()-> new RuntimeException("id not found "));

        return book ;
    }

    public Book addBook(BookDTO bookDTO){
        Book book = new Book() ;

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
        book.setAvailable(bookDTO.isAvailable());
        
        return bookRepository.save(book) ; 
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book oldbook = bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("book not found")) ;

        oldbook.setTitle(bookDTO.getTitle());
        oldbook.setAvailable(bookDTO.isAvailable());
        oldbook.setAuthor(bookDTO.getAuthor());
        oldbook.setIsbn(bookDTO.getIsbn());
        oldbook.setQuantity(bookDTO.getQuantity());

        return bookRepository.save(oldbook) ;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
