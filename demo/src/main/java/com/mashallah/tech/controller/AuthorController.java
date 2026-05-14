// AuthorController.java
package com.mashallah.tech.controller;

import com.mashallah.tech.entite.Author;
import com.mashallah.tech.input.AuthorInput;
import com.mashallah.tech.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @QueryMapping
    public Author authorById(@Argument Long id) {
        return authorService.getAuthorById(id);
    }

    @QueryMapping
    public List<Author> allAuthors() {
        return authorService.getAllAuthors();
    }

    @MutationMapping
    public Author addAuthor(@Argument AuthorInput authorInput) {
        return authorService.createAuthor(authorInput);
    }

    @MutationMapping
    public Author updateAuthor(@Argument Long id, @Argument AuthorInput authorInput) {
        return authorService.updateAuthor(id, authorInput);
    }

    @MutationMapping
    public boolean deleteAuthor(@Argument Long id) {
        return authorService.deleteAuthor(id);
    }
}