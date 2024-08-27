package com.project.booksapp.service;

import com.project.booksapp.entity.Author;
import com.project.booksapp.entity.Book;
import com.project.booksapp.repository.AuthorRepository;
import com.project.booksapp.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author addBookToAuthor(Long authorId, Book book) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            book.setAuthor(author);
            bookRepository.save(book);
            author.getBooks().add(book);
            return authorRepository.save(author);
        } else {
            throw new RuntimeException("Author not found with id: " + authorId);
        }
    }

    public Author getAuthorByBookId(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            return optionalBook.get().getAuthor();
        } else {
            throw new RuntimeException("Book not found with id: " + bookId);
        }
    }
}