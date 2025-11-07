package com.example.libraryManagement.controller;

import com.example.libraryManagement.entity.IssueRecord;
import com.example.libraryManagement.service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issuerecord")
public class IssueRecordController {

    @Autowired
    IssueRecordService issueRecordService ;

    @PostMapping("/issueTheBook/{bookid}")
    public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long id){
        return  ResponseEntity.ok(issueRecordService.issueTheBook(id)) ;
    }

    @PostMapping("/returnTheBook/{recordid}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long id){
        return ResponseEntity.ok(issueRecordService.returnTheBook(id)) ;
    }
}
