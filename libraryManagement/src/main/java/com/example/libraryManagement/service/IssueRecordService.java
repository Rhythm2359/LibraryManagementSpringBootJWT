package com.example.libraryManagement.service;

import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.entity.IssueRecord;
import com.example.libraryManagement.entity.User;
import com.example.libraryManagement.repository.BookRepository;
import com.example.libraryManagement.repository.IssueRecordsRepository;
import com.example.libraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private BookRepository bookRepository ;

    @Autowired
    private IssueRecordsRepository issueRecordsRepository ;

    public IssueRecord issueTheBook(Long bookId){

        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("book not found")) ;

        if(book.getQuantity() <= 0 || !book.isAvailable()){
            throw new RuntimeException("Book not avaliable") ;
        }

        String userName = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new RuntimeException("username not found")) ;

        IssueRecord issueRecord = new IssueRecord() ;
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity() == 0) book.setAvailable(false);

        bookRepository.save(book) ;
        return issueRecordsRepository.save(issueRecord) ;
    }

    public IssueRecord returnTheBook(Long id) {
        IssueRecord issueRecord = issueRecordsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("record not found")) ;

        if(issueRecord.getIsReturned()){
            throw new RuntimeException("book is returned") ;
        }

        Book book = issueRecord.getBook();

        book.setQuantity(book.getQuantity()+1);
        book.setAvailable(true);

        bookRepository.save(book) ;

        return  issueRecordsRepository.save(issueRecord) ;
    }
}
