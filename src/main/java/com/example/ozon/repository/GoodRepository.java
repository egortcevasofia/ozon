package com.example.ozon.repository;

import com.example.ozon.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAll();

    Optional<Good> findGoodById(Long id);

    Optional<Good> findGoodByName(String name);

    Boolean existsByName(String name);

    Good save(Good good);

    @Modifying
    @Query("UPDATE Good g set g.quantity = :quantity where g.id = :goodId")
    void setNewQuantity(@Param("goodId") Long goodId, @Param("quantity") int quantity);

    @Override
    <S extends Good> List<S> saveAll(Iterable<S> iterable);
}

