// AuthorService.java
package com.mashallah.tech.service;

import com.mashallah.tech.entite.Author;
import com.mashallah.tech.entite.Book;
import com.mashallah.tech.input.AuthorInput;
import com.mashallah.tech.input.BookInput;
import com.mashallah.tech.repository.AuthorRipository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRipository authorRepository;

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author createAuthor(AuthorInput authorInput) {
        Author author = new Author();
        author.setFirstName(authorInput.getFirstName());
        author.setLastName(authorInput.getLastName());
        return authorRepository.save(author);
    }

    @Transactional
    public Author updateAuthor(Long id, AuthorInput authorInput) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    // Mise à jour des champs de l'auteur
                    if (authorInput.getFirstName() != null) {
                        existingAuthor.setFirstName(authorInput.getFirstName());
                    }

                    if (authorInput.getLastName() != null) {
                        existingAuthor.setLastName(authorInput.getLastName());
                    }

                    // Note: Un auteur n'a pas de "bookId" dans AuthorInput
                    // La relation est gérée du côté de Book

                    return authorRepository.save(existingAuthor);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteAuthor(Long id) {
        try {
            authorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}