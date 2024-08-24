package com.project.booksapp.repository;

import com.project.booksapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.id = :bookId")
    Optional<Author> findAuthorByBookId(Long bookId);

}