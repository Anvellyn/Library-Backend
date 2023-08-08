package com.example.Library.books;

import com.example.Library.authors.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findById(Long id);
    public Optional<Book> findByTitle(String title);
    public Optional<Book> findByAuthor(Author author);
    public List<Book> findAll();
}
