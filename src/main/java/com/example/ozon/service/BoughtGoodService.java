package com.example.ozon.service;

import com.example.ozon.domain.BucketGood;
import com.example.ozon.domain.Good;
import com.example.ozon.domain.Receipt;
import com.example.ozon.repository.BoughtGoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BoughtGoodService {
    private BoughtGoodRepository boughtGoodRepository;
    private BucketGoodService bucketGoodService;
    private UserService userService;
    private GoodService goodService;
    private ReceiptService receiptService;

    public BoughtGoodService(BoughtGoodRepository boughtGoodRepository,
                             BucketGoodService bucketGoodService,
                             UserService userService, GoodService goodService,
                             ReceiptService receiptService) {
        this.boughtGoodRepository = boughtGoodRepository;
        this.bucketGoodService = bucketGoodService;
        this.userService = userService;
        this.goodService = goodService;
        this.receiptService = receiptService;
    }

    @Transactional
    public void buyGoodsFromBucket(String email) {
        List<BucketGood> listOfBucketGoods = bucketGoodService.findAllByUserEmail(email);
        Map<Good, Integer> mapOfGoods = listOfBucketGoods
                .stream()
                .collect(Collectors.toMap(BucketGood::getGood, BucketGood::getQuantity));

        goodService.buyAllGoods(mapOfGoods);
//        boughtGoodRepository.saveAll(listOfBucketGoods);
        //bucketGoodService.deleteAll(listOfBucketGoods);
        receiptService.createReceipte(mapOfGoods);

    }
}
