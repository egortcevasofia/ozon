package com.example.ozon.repository;

import com.example.ozon.domain.BoughtGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoughtGoodRepository extends JpaRepository<BoughtGood, Long> {
    List<BoughtGood> findAll();

    Optional<BoughtGood> findBoughtGoodById(Long id);

    List<BoughtGood> findBoughtGoodsByReceiptId(Long id);

    BoughtGood save(BoughtGood boughtGood);
}
