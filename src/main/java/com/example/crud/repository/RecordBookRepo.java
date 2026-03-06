package com.example.crud.repository;


import com.example.crud.entity.RecordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordBookRepo extends JpaRepository<RecordBook, Long> {
}
