package com.example.Library.books;


import com.example.Library.authors.Author;
import com.example.Library.authors.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    public Book addBook(Book book) {
        Author author = book.getAuthor();
        Author existingAuthor = authorService.getOrCreateAuthor(author);
        book.setAuthor(existingAuthor);
        return bookRepository.save(book);
    }

    public List<Book> getBooks (){
        return bookRepository.findAll();
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void removeBookById(long id) {
        Book client = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.delete(client);
    }
    public Book getBookByName(String title) {
        return bookRepository.findByTitle(title).orElseThrow(RuntimeException::new);
    }

    public Book getBookByAuthor(Author author) {
        return bookRepository.findByAuthor(author).orElseThrow(RuntimeException::new);
    }

}
