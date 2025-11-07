package com.example.libraryManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="issuerecord")
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDate issueDate ;
    private LocalDate dueDate ;
    private LocalDate returnDate ;

    private boolean IsReturned ;

    @ManyToOne
    @JoinColumn(name="user_id" , referencedColumnName = "id")
    private User user ;

    @ManyToOne
    @JoinColumn(name="book_id" , referencedColumnName="id")
    private Book book ;

    public boolean getIsReturned() {
        return IsReturned ;
    }
}
