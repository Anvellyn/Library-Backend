package com.example.Library.books;


import com.example.Library.authors.Author;
import com.example.Library.authors.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    public Book addBook(Book book) {
        Author author = book.getAuthor();
        Author existingAuthor = authorService.getOrCreateAuthor(author);

        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), existingAuthor);

        if (existingBook.isPresent()) {
            Book storedBook = existingBook.get();
            storedBook.setAmount(storedBook.getAmount() + book.getAmount());
            return bookRepository.save(storedBook);
        } else {
            book.setAuthor(existingAuthor);
            return bookRepository.save(book);
        }
    }

    public List<Book> getBooks (){
        return bookRepository.findAll();
    }
    public List<Book> getAvailableBooks (){
        return bookRepository.findByAmountGreaterThan(0);
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void removeBookById(long id) {
        Book client = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.delete(client);
    }
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(RuntimeException::new);
    }

    public Book getBookByAuthor(Author author) {
        return bookRepository.findByAuthor(author).orElseThrow(RuntimeException::new);
    }

}
