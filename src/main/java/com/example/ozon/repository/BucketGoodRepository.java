package com.example.ozon.repository;

import com.example.ozon.domain.BucketGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketGoodRepository extends JpaRepository<BucketGood, Long> {
    List<BucketGood> findAll();
    Optional<BucketGood> findBucketGoodById(Long id);
    List<BucketGood> findBucketGoodsByUserId(Long id);
    BucketGood save(BucketGood bucketGood);
}
