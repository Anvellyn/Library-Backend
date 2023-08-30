package com.example.Library;

import com.example.Library.authors.Author;
import com.example.Library.books.Book;
import com.example.Library.books.BookController;
import com.example.Library.books.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerTest {
    private BookService bookService;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookService = mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Test
    void testGetBook() {
        List<Book> books = List.of(new Book(), new Book());
        when(bookService.getAvailableBooks()).thenReturn(books);

        List<Book> result = bookController.getBook();

        assertEquals(books, result);
        verify(bookService).getAvailableBooks();
    }

    @Test
    void testGetAvailableBooks() {
        List<Book> availableBooks = List.of(new Book());
        when(bookService.getAvailableBooks()).thenReturn(availableBooks);

        List<Book> result = bookController.getAvailableBooks();

        assertEquals(availableBooks, result);
        verify(bookService).getAvailableBooks();
    }

    @Test
    void testGetBookById() {
        long bookId = 1L;
        Book book = new Book();
        when(bookService.getBookById(bookId)).thenReturn(book);

        Book result = bookController.getBookById(bookId);

        assertEquals(book, result);
        verify(bookService).getBookById(bookId);
    }

    @Test
    void testRemoveBookById() {
        long bookId = 1L;

        ResponseEntity response = bookController.removeBookById(bookId);

        verify(bookService).removeBookById(bookId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Ksiazka zostala prawidlowo usunieta", response.getBody());
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        when(bookService.addBook(book)).thenReturn(book);

        Book result = bookController.addBook(book);

        assertEquals(book, result);
        verify(bookService).addBook(book);
    }

    @Test
    void testGetBookByAuthor() {
        Author author = new Author();
        Book book = new Book();
        when(bookService.getBookByAuthor(author)).thenReturn(book);

        Book result = bookController.getBookByAuthor(author);

        assertEquals(book, result);
        verify(bookService).getBookByAuthor(author);
    }
}
