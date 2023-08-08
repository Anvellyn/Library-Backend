package com.example.Library.books;

import com.example.Library.authors.Author;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="Books")
public class Book {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @ManyToOne
    private Author author;
    private String genre;
    private LocalDate publicationDate;
    private boolean isBorrowed;
}
