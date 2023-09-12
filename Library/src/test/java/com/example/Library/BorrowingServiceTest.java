package com.example.Library;

import com.example.Library.authors.Author;
import com.example.Library.books.Book;
import com.example.Library.books.BookService;
import com.example.Library.borrowings.Borrowing;
import com.example.Library.borrowings.BorrowingRepository;
import com.example.Library.borrowings.BorrowingService;
import com.example.Library.clients.Client;
import com.example.Library.clients.ClientService;
import com.example.Library.clients.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BorrowingServiceTest {
    @InjectMocks
    private BorrowingService borrowingService;

    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private BookService bookService;

    @Mock
    private ClientService clientService;

    private Book book;
    private Client client;
    private Borrowing borrowing;
    private Author author;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        author = new Author(5, "Edward Elric");
        book = new Book(1000, "Alchemy", author, "fantasy", 5);
        client = new Client(1000, "ClientName", "client@example.com", "password", Role.USER);
        borrowing = new Borrowing(book, client);

        when(bookService.getBookById(anyLong())).thenReturn(book);
        when(clientService.getLoggedUserPrincipal()).thenReturn(client);
    }

    @Test
    public void testAddBorrowValid() {
        when(borrowingRepository.save(any(Borrowing.class))).thenReturn(borrowing);
        when(bookService.getBookById(anyLong())).thenReturn(book);
        when(clientService.getLoggedUserPrincipal()).thenReturn(client);

        Borrowing result = borrowingService.addBorrow(1L);

        assertNotNull(result);
        assertTrue(result.getBook().equals(book));
        assertTrue(result.getClient().equals(client));
    }

    @Test
    public void testGetBorrowings() {
        List<Borrowing> borrowingList = new ArrayList<>();
        borrowingList.add(borrowing);

        when(borrowingRepository.findAll()).thenReturn(borrowingList);

        List<Borrowing> result = borrowingService.getBorrowings();

        assertFalse(result.isEmpty());
        assertEquals(borrowingList.size(), result.size());
    }

    @Test
    public void testGetBorrowingById() {
        when(borrowingRepository.findById(anyLong())).thenReturn(Optional.of(borrowing));

        Borrowing result = borrowingService.getBorrowingById(1L);

        assertNotNull(result);
        assertEquals(borrowing, result);
    }

    @Test
    public void testGetBorrowingByClient() {
        when(borrowingRepository.findByClient(any(Client.class))).thenReturn(Optional.of(borrowing));

        Borrowing result = borrowingService.getBorrowingByClient(client);

        assertNotNull(result);
        assertEquals(borrowing, result);
    }

    @Test
    public void testRemoveBorrowingById() {
        when(borrowingRepository.findById(anyLong())).thenReturn(Optional.of(borrowing));

        borrowingService.removeBorrowingById(1L);

        verify(borrowingRepository, times(1)).delete(any(Borrowing.class));
    }
}
