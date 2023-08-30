package com.example.Library.books;

import com.example.Library.authors.Author;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public List<Book> getBook() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeBookById(@PathVariable long id) {
        bookService.removeBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ksiazka zostala prawidlowo usunieta");
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/getByAuthor")
    public Book getBookByAuthor(Author author) {
        return bookService.getBookByAuthor(author);
    }
}
