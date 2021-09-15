package com.example.ozon.repository;

import com.example.ozon.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findAll();
    Optional<Receipt> findReceiptById(Long id);
    List<Receipt> findReceiptByUserId(Long id);
    Receipt save(Receipt receipt);
}
