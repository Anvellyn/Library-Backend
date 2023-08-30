package com.example.Library.clients;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/client")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/all")
    public List<Client> getClient() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeClientById(@PathVariable long id) {
        clientService.removeClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Uzytkownik zostal prawidlowo usuniety");
    }

    @PostMapping("/register")
    public Client registerUser(@RequestBody Client client){
        return clientService.addClient(client);
    }
}
