package com.project.booksapp.controller;

import com.project.booksapp.entity.Author;
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
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.OK);
    }

//    @PostMapping("/saveAuthors")
//    public ResponseEntity<List<Author>> saveAuthors(@RequestBody List<Author> authors) {
//        List<Author> savedAuthors = authorService.saveAuthors(authors);
//        return new ResponseEntity<>(savedAuthors, HttpStatus.OK);
//    }

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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);

        if (!author.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorService.deleteAuthorById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } }


