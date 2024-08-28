package com.project.booksapp.service;


import com.project.booksapp.entity.Author;
import com.project.booksapp.entity.Book;
import com.project.booksapp.entity.Category;
import com.project.booksapp.repository.AuthorRepository;
import com.project.booksapp.repository.BookRepository;
import com.project.booksapp.repository.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    public Book assignAuthorToBookByIsbn(String isbn, String authorName) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        Optional<Author> optionalAuthor = authorRepository.findByName(authorName);

        if (optionalBook.isPresent() && optionalAuthor.isPresent()) {
            Book book = optionalBook.get();
            Author author = optionalAuthor.get();
            book.setAuthor(author);
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    public Book assignCategoryToBookByName(Long bookId, String categoryName) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryName);

        if (optionalBook.isPresent() && optionalCategory.isPresent()) {
            Book book = optionalBook.get();
            Category category = optionalCategory.get();
            book.setCategory(category);
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
    public List<Book> searchBooks(String title, String authorName, String categoryName) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContaining(title);
        } else if (authorName != null && !authorName.isEmpty()) {
            return bookRepository.findByAuthorNameContaining(authorName);
        } else if (categoryName != null && !categoryName.isEmpty()) {
            return bookRepository.findByCategoryNameContaining(categoryName);
        } else {
            return bookRepository.findAll();
        }
    }

}