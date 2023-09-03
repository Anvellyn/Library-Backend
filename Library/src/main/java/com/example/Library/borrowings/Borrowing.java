package com.example.Library.borrowings;

import com.example.Library.books.Book;
import com.example.Library.clients.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="Borrowing")
public class Borrowing {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Client client;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;
    private boolean isReturned;
    public Borrowing(Book book, Client client) {
        this.book = book;
        this.client = client;
        this.dateOfStart = LocalDate.now();
        this.dateOfEnd = dateOfStart.plus(2, ChronoUnit.WEEKS);
        this.isReturned = false;
    }
}
