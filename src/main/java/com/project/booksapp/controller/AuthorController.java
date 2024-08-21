package com.project.booksapp.controller;

import com.project.booksapp.entity.Author;
import com.project.booksapp.repository.AuthorRepository;
import com.project.booksapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("getAllAuthors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/getAuthorById/{id}")
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable Long id) {
        Optional<Author> authorData = authorRepository.findById(id);
        return authorData.isPresent() ? new ResponseEntity<>(authorData, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/saveAuthor")
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.OK);
    }

    @PutMapping("/updateAuthorById/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> authorData = authorRepository.findById(id);

        if (authorData.isPresent()) {
            Author updatedAuthorData = authorData.get();
            updatedAuthorData.setName(author.getName());

            Author savedAuthor = authorRepository.save(updatedAuthorData);
            return new ResponseEntity<>(savedAuthor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteAuthorById/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (!authorService.getAuthorById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
