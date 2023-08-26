package com.example.Library.borrowings;

import com.example.Library.books.Book;
import com.example.Library.clients.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    LocalDate dateOfStart;
    LocalDate dateOfEnd;
}
