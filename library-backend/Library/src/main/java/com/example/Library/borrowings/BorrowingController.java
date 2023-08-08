package com.example.Library.borrowings;

import com.example.Library.books.Book;
import com.example.Library.clients.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/borrow")
public class BorrowingController {
    private final BorrowingService borrowingService;

    @GetMapping("/all")
    public List<Borrowing> getBorrowings() {
        return borrowingService.getBorrowings();
    }

    @GetMapping("/{id}")
    public Borrowing getBorrowingById(@PathVariable long id) {
        return borrowingService.getBorrowingById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeBookById(@PathVariable long id) {
        borrowingService.removeBorrowingById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Wypozyczenie zostalo prawidlowo usuniete");
    }

    @GetMapping("/{client}")
    public Borrowing getBorrowingByClient(@PathVariable Client client) {
        return borrowingService.getBorrowingByClient(client);
    }

    @PostMapping("/add")
    private Borrowing addBorrowing(@RequestBody Borrowing borrowing, @RequestBody Book book){
        return borrowingService.addBorrow(borrowing, book);
    }
}
