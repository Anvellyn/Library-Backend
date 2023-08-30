package com.example.Library;

import com.example.Library.authors.Author;
import com.example.Library.books.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    void testGetterAndSetter() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("warhammer 40k");
        book.setAuthor(new Author());
        book.setGenre("fiction");
        book.setBorrowed(true);

        assertEquals(1L, book.getId());
        assertEquals("warhammer 40k", book.getTitle());
        assertNotNull(book.getAuthor());
        assertEquals("fiction", book.getGenre());
        assertTrue(book.isBorrowed());
    }
}
