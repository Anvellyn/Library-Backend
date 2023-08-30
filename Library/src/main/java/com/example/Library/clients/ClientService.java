package com.example.Library.clients;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    public Client getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        return client;
    }

    public void removeClientById(long id) {
        Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        clientRepository.delete(client);

    }

    public Client getLoggedUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (Client) authentication.getPrincipal();
        }
        throw new RuntimeException("Auth: user must be logged in");
    }

}
