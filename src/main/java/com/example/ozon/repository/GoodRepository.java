package com.example.ozon.repository;

import com.example.ozon.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAll();
    Optional<Good> findGoodById(Long id);
    Optional<Good> findGoodByName(String name);
    Boolean existsByName(String name);
    Good save(Good good);
}
