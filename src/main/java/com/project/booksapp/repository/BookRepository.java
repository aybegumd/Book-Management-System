package com.project.booksapp.repository;

import com.project.booksapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findByTitleContaining(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE LOWER(b.author.name) LIKE LOWER(CONCAT('%', :authorName, '%'))")
    List<Book> findByAuthorNameContaining(@Param("authorName") String authorName);

    @Query("SELECT b FROM Book b WHERE LOWER(b.category.name) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    List<Book> findByCategoryNameContaining(@Param("categoryName") String categoryName);

    Optional<Book> findByIsbn(String isbn);



}