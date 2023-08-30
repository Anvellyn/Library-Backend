package com.example.Library;

import com.example.Library.authors.Author;
import com.example.Library.authors.AuthorService;
import com.example.Library.books.Book;
import com.example.Library.books.BookRepository;
import com.example.Library.books.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    private BookRepository bookRepository;
    private AuthorService authorService;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        authorService = mock(AuthorService.class);
        bookService = new BookService(bookRepository, authorService);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        Author author = new Author();
        book.setAuthor(author);

        when(authorService.getOrCreateAuthor(author)).thenReturn(author);
        when(bookRepository.save(book)).thenReturn(book);

        Book addedBook = bookService.addBook(book);

        assertEquals(author, addedBook.getAuthor());
        verify(authorService).getOrCreateAuthor(author);
        verify(bookRepository).save(book);
    }

    @Test
    void testGetBooks() {
        List<Book> books = List.of(new Book(), new Book());
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getBooks();

        assertEquals(books, result);
        verify(bookRepository).findAll();
    }

    @Test
    void testGetAvailableBooks() {
        List<Book> availableBooks = List.of(new Book());
        when(bookRepository.findByIsBorrowedFalse()).thenReturn(availableBooks);

        List<Book> result = bookService.getAvailableBooks();

        assertEquals(availableBooks, result);
        verify(bookRepository).findByIsBorrowedFalse();
    }

    @Test
    void testGetBookById() {
        long bookId = 1L;
        Book book = new Book();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book retrievedBook = bookService.getBookById(bookId);

        assertEquals(book, retrievedBook);
        verify(bookRepository).findById(bookId);
    }

    @Test
    void testRemoveBookById() {
        long bookIdToRemove = 1L;
        Book bookToRemove = new Book();
        bookToRemove.setId(bookIdToRemove);

        when(bookRepository.findById(bookIdToRemove)).thenReturn(Optional.of(bookToRemove));

        bookService.removeBookById(bookIdToRemove);

        verify(bookRepository, times(1)).findById(bookIdToRemove);
        verify(bookRepository, times(1)).delete(bookToRemove);
    }

    @Test
    void testGetBookByTitle() {
        String title = "Sample Book";
        Book book = new Book();
        when(bookRepository.findByTitle(title)).thenReturn(Optional.of(book));

        Book retrievedBook = bookService.getBookByTitle(title);

        assertEquals(book, retrievedBook);
        verify(bookRepository).findByTitle(title);
    }

    @Test
    void testGetBookByAuthor() {
        Author author = new Author();
        Book book = new Book();
        when(bookRepository.findByAuthor(author)).thenReturn(Optional.of(book));

        Book retrievedBook = bookService.getBookByAuthor(author);

        assertEquals(book, retrievedBook);
        verify(bookRepository).findByAuthor(author);
    }
}
