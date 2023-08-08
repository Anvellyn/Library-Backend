package com.example.Library.borrowings;

import com.example.Library.books.Book;
import com.example.Library.clients.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingService {
    private BorrowingRepository borrowingRepository;

    private boolean checkIfBorrowed(Book book) {
        return book.isBorrowed();
    }
    public Borrowing addBorrow(Borrowing borrowing, Book book) {
        if (checkIfBorrowed(book)) {
            return borrowingRepository.save(borrowing);
        }
        return null;
    }

    public List<Borrowing> getBorrowings (){
        return borrowingRepository.findAll();
    }

    public Borrowing getBorrowingById(long id) {
        return borrowingRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Borrowing getBorrowingByClient(Client client) {
        return borrowingRepository.findByClient(client).orElseThrow(RuntimeException::new);
    }

    public void removeBorrowingById(long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElseThrow(RuntimeException::new);
        borrowingRepository.delete(borrowing);
    }
}
