package com.example.ozon.repository;

import com.example.ozon.domain.BucketGood;
import com.example.ozon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketGoodRepository extends JpaRepository<BucketGood, Long> {
    List<BucketGood> findAll();
    Optional<BucketGood> findBucketGoodById(Long id);
    List<BucketGood> findBucketGoodsByUserId(Long id);
    BucketGood save(BucketGood bucketGood);

    @Query(value = "SELECT * FROM bucket_goods " +
            "WHERE now() - bucket_goods.date_of_adding >= interval '2 hour' ",
            nativeQuery = true)
    List<BucketGood> findOldGoods();



}
