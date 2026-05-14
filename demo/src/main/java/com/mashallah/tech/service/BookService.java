// BookService.java
package com.mashallah.tech.service;

import com.mashallah.tech.entite.Book;
import com.mashallah.tech.input.BookInput;
import com.mashallah.tech.repository.BookRepository;
import com.mashallah.tech.repository.AuthorRipository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRipository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book addBook(BookInput bookInput) {
        Book book = new Book();
        book.setTitle(bookInput.getTitle());
        book.setPageCount(bookInput.getPageCount());

        if (Objects.nonNull(bookInput.getAuthorId())) {
            authorRepository.findById(bookInput.getAuthorId())
                    .ifPresent(book::setAuthor);
        }

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, BookInput bookInput) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookInput.getTitle());
                    existingBook.setPageCount(bookInput.getPageCount());

                    if (Objects.nonNull(bookInput.getAuthorId())) {
                        authorRepository.findById(bookInput.getAuthorId())
                                .ifPresent(existingBook::setAuthor);
                    }

                    return bookRepository.save(existingBook);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}