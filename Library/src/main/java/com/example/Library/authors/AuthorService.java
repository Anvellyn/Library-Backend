package com.example.Library.authors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;

    public Author getOrCreateAuthor(Author author) {
        Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
        return existingAuthor.orElseGet(() -> authorRepository.save(author));
    }

}
