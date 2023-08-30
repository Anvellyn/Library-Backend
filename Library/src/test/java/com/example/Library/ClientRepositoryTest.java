package com.example.Library;

import com.example.Library.clients.Client;
import com.example.Library.clients.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ClientRepositoryTest {
    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        when(clientRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(new Client()));

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(new Client()));
    }

    @Test
    void testFindByEmail() {
        Optional<Client> client = clientRepository.findByEmail("test@example.com");

        assertTrue(client.isPresent());
    }

    @Test
    void testFindById() {
        Optional<Client> client = clientRepository.findById(1L);

        assertTrue(client.isPresent());
    }
}
