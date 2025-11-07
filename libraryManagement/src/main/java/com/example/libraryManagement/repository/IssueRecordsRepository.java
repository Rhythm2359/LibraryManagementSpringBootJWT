package com.example.libraryManagement.repository;

import com.example.libraryManagement.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordsRepository extends JpaRepository<IssueRecord , Long> {
}
