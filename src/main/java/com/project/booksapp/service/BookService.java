package com.project.booksapp.service;


import com.project.booksapp.entity.Book;
import com.project.booksapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<com.project.booksapp.entity.Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<com.project.booksapp.entity.Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


}
