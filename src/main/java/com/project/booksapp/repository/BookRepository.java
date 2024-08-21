package com.project.booksapp.repository;


import com.project.booksapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Long> {
}

