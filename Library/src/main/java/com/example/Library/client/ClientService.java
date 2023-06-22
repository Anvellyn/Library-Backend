package com.example.Library.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getClients (){
        return clientRepository.findAll();
    }

    public Client getClientById(long id) {
        Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        return client;
    }

    public void removeClientById(long id) {
        Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        clientRepository.delete(client);

    }
}