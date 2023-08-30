package com.example.Library;

import com.example.Library.clients.Client;
import com.example.Library.clients.ClientRepository;
import com.example.Library.clients.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    private ClientRepository clientRepository;
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void testAddClient() {
        Client client = new Client();

        when(clientRepository.save(client)).thenReturn(client);

        Client addedClient = clientService.addClient(client);

        verify(clientRepository).save(client);
        assertEquals(client, addedClient);
    }

    @Test
    void testGetClients() {
        List<Client> clients = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getClients();

        assertEquals(clients, result);
        verify(clientRepository).findAll();
    }

    @Test
    void testGetClientById() {
        long clientId = 1L;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Client retrievedClient = clientService.getClientById(clientId);

        assertEquals(client, retrievedClient);
        verify(clientRepository).findById(clientId);
    }

    @Test
    void testGetClientByEmail() {
        String email = "test@example.com";
        Client client = new Client();
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));

        Client retrievedClient = clientService.getClientByEmail(email);

        assertEquals(client, retrievedClient);
        verify(clientRepository).findByEmail(email);
    }

    @Test
    void testRemoveClientById() {
        long clientId = 1L;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.removeClientById(clientId);

        verify(clientRepository).delete(client);
    }

    @Test
    void testGetLoggedUserPrincipal() {
        Client client = new Client();
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(authentication.getPrincipal()).thenReturn(client);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Client result = clientService.getLoggedUserPrincipal();

        assertEquals(client, result);
    }

}
