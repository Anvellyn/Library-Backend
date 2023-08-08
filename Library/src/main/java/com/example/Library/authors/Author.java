package com.example.Library.authors;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="Authors")
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
