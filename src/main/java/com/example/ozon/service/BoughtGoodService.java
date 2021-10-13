package com.example.ozon.service;

import com.example.ozon.domain.BoughtGood;
import com.example.ozon.domain.BucketGood;
import com.example.ozon.domain.Receipt;
import com.example.ozon.domain.User;
import com.example.ozon.mapper.UserMapper;
import com.example.ozon.exception.EmptyBucketException;
import com.example.ozon.repository.BoughtGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoughtGoodService {
    private BoughtGoodRepository boughtGoodRepository;
    private BucketGoodService bucketGoodService;
    private UserService userService;
    private GoodService goodService;
    private ReceiptService receiptService;
    private UserMapper userMapper;

    @Autowired
    public BoughtGoodService(BoughtGoodRepository boughtGoodRepository,
                             BucketGoodService bucketGoodService,
                             UserService userService, GoodService goodService,
                             ReceiptService receiptService,
                             UserMapper userMapper) {
        this.boughtGoodRepository = boughtGoodRepository;
        this.bucketGoodService = bucketGoodService;
        this.userService = userService;
        this.goodService = goodService;
        this.receiptService = receiptService;
        this.userMapper = userMapper;
    }

    @Transactional
    public List<BoughtGood> buyGoodsFromBucket(String email) {
        User user = userMapper.UserDtoToUser(userService.findUserByEmail(email));
        List<BucketGood> listOfBucketGoods = bucketGoodService.findAllByUserId(user.getId());
        if (listOfBucketGoods.isEmpty()) {
            throw new EmptyBucketException();
        }

        goodService.buyAllGoods(listOfBucketGoods);// уменьшает кол во на складе
        List<BoughtGood> listOfBoughtGoods = bucketGoodsToBoughtGoods(listOfBucketGoods);// маппит товары из корзины в купленные товары
        Receipt receipt = receiptService.createReceipt(listOfBoughtGoods, user);// создает чек
        listOfBoughtGoods.forEach(boughtGood -> boughtGood.setReceipt(receipt));// сетит квитанцию в каждый купленый товар
        bucketGoodService.deleteAll(listOfBucketGoods);//опустошает корзину
        return boughtGoodRepository.saveAll(listOfBoughtGoods);//все сохраняет в базу купленных товаров

    }

    private List<BoughtGood> bucketGoodsToBoughtGoods(List<BucketGood> listOfBucketGoods) {
        ArrayList<BoughtGood> listOfBoughtGoods = listOfBucketGoods.stream().map(bucketGood -> new BoughtGood(bucketGood.getGood(),
                bucketGood.getName(),
                bucketGood.getQuantity(),
                bucketGood.getPrice())).collect(Collectors.toCollection(ArrayList::new));

        return listOfBoughtGoods;
    }
}
