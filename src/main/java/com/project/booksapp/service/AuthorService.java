package com.project.booksapp.service;

import com.project.booksapp.entity.Author;
import com.project.booksapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public Optional<Author> updateAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
    public List<Author> saveAuthors(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }
}
