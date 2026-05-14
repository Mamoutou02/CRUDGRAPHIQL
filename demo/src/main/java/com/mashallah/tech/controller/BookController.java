// BookController.java
package com.mashallah.tech.controller;

import com.mashallah.tech.entite.Book;
import com.mashallah.tech.input.BookInput;
import com.mashallah.tech.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @QueryMapping
    public List<Book> allBooks() {
        return bookService.getAllBooks();
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput bookInput) {
        return bookService.addBook(bookInput);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id,
                           @Argument BookInput bookInput) {
        return bookService.updateBook(id, bookInput);
    }

    @MutationMapping
    public boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }
}