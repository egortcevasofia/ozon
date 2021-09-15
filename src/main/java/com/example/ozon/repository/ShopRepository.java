package com.example.ozon.repository;

import com.example.ozon.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAll();
    Optional<Shop> findShopById(Long id);
    Optional<Shop> findShopByName(String name);
    Boolean existsByName(String email);
    Shop save(Shop shop);
}
