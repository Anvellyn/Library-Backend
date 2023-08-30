package com.example.Library;

import com.example.Library.clients.Client;
import com.example.Library.clients.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("johndoe@example.com");
        client.setPassword("password");
        client.setRole(Role.USER);
    }

    @Test
    void testGetAuthorities() {
        assertEquals(1, client.getAuthorities().size());
        assertTrue(client.getAuthorities().contains(new SimpleGrantedAuthority(Role.USER.name())));
    }

    @Test
    void testGetPassword() {
        assertEquals("password", client.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals("johndoe@example.com", client.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(client.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(client.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(client.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(client.isEnabled());
    }

}
