package com.example.Library.borrowings;

import com.example.Library.books.Book;
import com.example.Library.books.BookRepository;
import com.example.Library.books.BookService;
import com.example.Library.clients.Client;
import com.example.Library.clients.ClientRepository;
import com.example.Library.clients.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingService {
    private BorrowingRepository borrowingRepository;
    private BookService bookService;
    private ClientService clientService;


    public Borrowing addBorrow(String title) {
        Book book = bookService.getBookByTitle(title);
        Client client = clientService.getLoggedUserPrincipal();
        if(!book.isBorrowed()) {
            book.setBorrowed(true);
            return borrowingRepository.save(new Borrowing(book, client));
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
