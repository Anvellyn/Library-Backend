package com.example.Library.client;

import com.example.Library.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Optional<Client> findByEmail(String email);
    public Optional<Client> findById(Long id);
}