package com.example.ozon.repository;

import com.example.ozon.domain.Image;
import com.example.ozon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAll();
    Image save(Image image);

}
