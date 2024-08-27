package com.project.booksapp.controller;

import com.project.booksapp.entity.Author;
import com.project.booksapp.entity.Book;
import com.project.booksapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authorList = authorService.getAllAuthors();
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author.get(), HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Author> getAuthorByBookId(@PathVariable Long bookId) {
        Optional<Author> author = Optional.ofNullable(authorService.getAuthorByBookId(bookId));
        if (author.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> authorData = authorService.getAuthorById(id);

        if (authorData.isPresent()) {
            Author updatedAuthorData = authorData.get();
            updatedAuthorData.setName(author.getName());

            Author savedAuthor = authorService.saveAuthor(updatedAuthorData);
            return new ResponseEntity<>(savedAuthor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{authorId}/books")
    public ResponseEntity<Author> addBookToAuthor(@PathVariable Long authorId, @RequestBody Book book) {
        Author updatedAuthor = authorService.addBookToAuthor(authorId, book);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);

        if (author.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
