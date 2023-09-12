package com.example.Library;

import com.example.Library.borrowings.Borrowing;
import com.example.Library.borrowings.BorrowingController;
import com.example.Library.borrowings.BorrowingService;
import com.example.Library.clients.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BorrowingControllerTest {
    private BorrowingController borrowingController;
    private BorrowingService borrowingService;

    @BeforeEach
    public void setUp() {
        borrowingService = Mockito.mock(BorrowingService.class);
        borrowingController = new BorrowingController(borrowingService);
    }

    @Test
    public void testGetBorrowings() {
        List<Borrowing> borrowings = new ArrayList<>();
        when(borrowingService.getBorrowings()).thenReturn(borrowings);

        List<Borrowing> result = borrowingController.getBorrowings();

        assertEquals(borrowings, result);
    }

    @Test
    public void testGetBorrowingById() {
        long id = 1L;
        Borrowing expectedBorrowing = new Borrowing();
        when(borrowingService.getBorrowingById(id)).thenReturn(expectedBorrowing);

        Borrowing result = borrowingController.getBorrowingById(id);

        assertEquals(expectedBorrowing, result);
    }

    @Test
    public void testRemoveBorrowingById() {
        long id = 1L;
        ResponseEntity expectedResponse = ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Wypozyczenie zostalo prawidlowo usuniete");
        borrowingController.removeBookById(id);
        Mockito.verify(borrowingService).removeBorrowingById(id);
    }

    @Test
    public void testAddBorrowing() {
        long bookId = 2L;
        Borrowing expectedBorrowing = new Borrowing();

        when(borrowingService.addBorrow(bookId)).thenReturn(expectedBorrowing);

        Borrowing result = borrowingController.addBorrowing(bookId);

        assertEquals(expectedBorrowing, result);
    }

    @Test
    public void testGetBorrowingByClient() {
        // Symulacja klienta
        Client client = new Client();
        Borrowing expectedBorrowing = new Borrowing();

        when(borrowingService.getBorrowingByClient(client)).thenReturn(expectedBorrowing);

        Borrowing result = borrowingController.getBorrowingByClient(client);

        assertEquals(expectedBorrowing, result);
    }
}
