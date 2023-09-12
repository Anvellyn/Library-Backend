package com.example.Library;

import com.example.Library.authors.Author;
import com.example.Library.books.Book;
import com.example.Library.borrowings.Borrowing;
import com.example.Library.clients.Client;
import com.example.Library.clients.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowingTest {
    private Borrowing borrowing;
    private Book book;
    private Client client;
    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author(5, "Edward Elric");
        book = new Book(1000, "Alchemy", author, "fantasy", 5);
        client = new Client(1000, "ClientName", "client@example.com", "password", Role.USER);
        borrowing = new Borrowing(book, client);
    }

    @Test
    public void testBorrowingConstructor() {
        assertNotNull(borrowing.getId());
        assertEquals(book, borrowing.getBook());
        assertEquals(client, borrowing.getClient());
        assertNotNull(borrowing.getDateOfStart());
        assertNotNull(borrowing.getDateOfEnd());
        assertFalse(borrowing.isReturned());
    }


    @Test
    public void testDueDate() {
        LocalDate expectedDueDate = LocalDate.now().plus(2, ChronoUnit.WEEKS);
        assertEquals(expectedDueDate, borrowing.getDateOfEnd());
    }
}
