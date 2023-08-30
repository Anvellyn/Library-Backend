package com.example.Library;

import com.example.Library.clients.Client;
import com.example.Library.clients.ClientController;
import com.example.Library.clients.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientControllerTest {
    private ClientService clientService;
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        clientService = mock(ClientService.class);
        clientController = new ClientController(clientService);
    }

    @Test
    void testGetClient() {
        List<Client> clients = List.of(new Client(), new Client());
        when(clientService.getClients()).thenReturn(clients);

        List<Client> result = clientController.getClient();

        assertEquals(clients, result);
        verify(clientService).getClients();
    }

    @Test
    void testGetClientById() {
        long clientId = 1L;
        Client client = new Client();
        when(clientService.getClientById(clientId)).thenReturn(client);

        Client result = clientController.getClientById(clientId);

        assertEquals(client, result);
        verify(clientService).getClientById(clientId);
    }

    @Test
    void testRemoveClientById() {
        long clientId = 1L;
        ResponseEntity response = clientController.removeClientById(clientId);
        verify(clientService).removeClientById(clientId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Uzytkownik zostal prawidlowo usuniety", response.getBody());
    }

    @Test
    void testRegisterUser() {
        Client client = new Client();
        when(clientService.addClient(client)).thenReturn(client);
        Client result = clientController.registerUser(client);
        assertEquals(client, result);
        verify(clientService).addClient(client);
    }

}
