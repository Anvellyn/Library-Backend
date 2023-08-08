package com.example.Library.borrowings;

import com.example.Library.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    public Optional<Borrowing> findById(Long id);
    public Optional<Borrowing> findByClient(Client client);
    public List<Borrowing> findAll();
}

