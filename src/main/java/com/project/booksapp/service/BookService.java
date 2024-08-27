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
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book assignAuthorToBook(Long bookId, Long authorId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if (optionalBook.isPresent() && optionalAuthor.isPresent()) {
            Book book = optionalBook.get();
            Author author = optionalAuthor.get();
            book.setAuthor(author);
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    public List<com.project.booksapp.entity.Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


}