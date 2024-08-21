package com.project.booksapp.controller;

import com.project.booksapp.entity.Book;
import com.project.booksapp.repository.BookRepository;
import com.project.booksapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    private BookRepository bookRepository;

    @GetMapping("getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList::add);

        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveBook")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }

    @PutMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            Book updatedBookData = bookData.get();
            updatedBookData.setTitle(book.getTitle());
            updatedBookData.setAuthor(book.getAuthor());

            Book savedBook = bookRepository.save(updatedBookData);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        if (!bookService.getBookById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
