package com.mashallah.tech.controller;

import com.mashallah.tech.entite.Author;
import com.mashallah.tech.input.AuthorInput;
import com.mashallah.tech.repository.AuthorRipository;
import com.mashallah.tech.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRipository authorRipository;
    private final BookRepository bookRepository;

    @QueryMapping
    public Author authorById(@Argument Long id) {
        return authorRipository.findById(id).orElse(null);


    }

    @QueryMapping
    public List<Author> allAuthors() {
        return authorRipository.findAll();
    }

    @MutationMapping
    public Author createAuthor(@Argument AuthorInput authorInput) {
        var author = new Author();
        author.setFirstName(authorInput.getFirstName());
        author.setLastName(authorInput.getLastName());
        return authorRipository.save(author);
    }
}
