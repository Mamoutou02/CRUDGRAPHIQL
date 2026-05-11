package com.mashallah.tech.controller;

import com.mashallah.tech.entite.Book;
import com.mashallah.tech.input.BookInput;
import com.mashallah.tech.repository.AuthorRipository;
import com.mashallah.tech.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRipository authorRipository;


    @QueryMapping
    public List<Book> allBooks(){
        return bookRepository.findAll();

    }

    @QueryMapping
    public Book BookById(@Argument Long id){
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput bookInput) {

        var book = new Book();

        book.setTitle(bookInput.getTitle());
        book.setPageCount(bookInput.getPageCount());

        if (Objects.nonNull(bookInput.getAuthorId())) {
            authorRipository.findById(bookInput.getAuthorId())
                    .ifPresent(book::setAuthor);
        }

        return bookRepository.save(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Long id,
                           @Argument BookInput bookInput) {

        return bookRepository.findById(id)
                .map(existingBook -> {

                    existingBook.setTitle(bookInput.getTitle());
                    existingBook.setPageCount(bookInput.getPageCount());

                    if (Objects.nonNull(bookInput.getAuthorId())) {
                        authorRipository.findById(bookInput.getAuthorId())
                                .ifPresent(existingBook::setAuthor);
                    }

                    return bookRepository.save(existingBook);
                })
                .orElse(null);
    }


    @MutationMapping
    public boolean deleteBook(@Argument Long id){
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }


    }





}
