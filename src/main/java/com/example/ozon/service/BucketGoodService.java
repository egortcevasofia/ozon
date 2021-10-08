package com.example.ozon.service;

import com.example.ozon.domain.BucketGood;
import com.example.ozon.domain.Good;
import com.example.ozon.domain.User;
import com.example.ozon.dto.GoodMapper;
import com.example.ozon.dto.UserMapper;
import com.example.ozon.exception.GoodNotFoundException;
import com.example.ozon.repository.BucketGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class BucketGoodService {
    private GoodService goodService;
    private GoodMapper goodMapper;
    private UserService userService;
    private UserMapper userMapper;
    private BucketGoodRepository bucketGoodRepository;

    @Autowired
    public BucketGoodService(GoodService goodService, GoodMapper goodMapper,
                             UserService userService, UserMapper userMapper,
                             BucketGoodRepository bucketGoodRepository) {
        this.goodService = goodService;
        this.goodMapper = goodMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.bucketGoodRepository = bucketGoodRepository;
    }


    public BucketGood creatBucketGood(String username, Long goodId) {
        Good good = goodMapper.goodDtoToGood(goodService.findById(goodId));
        User user = userMapper.UserDtoToUser(userService.findUserByEmail(username));

        if (good.getQuantity() < 1) {
            throw new GoodNotFoundException();
        }

        BucketGood bucketGood = new BucketGood(good, good.getName(), 1, good.getPrice(), user, LocalDateTime.now());

        return bucketGoodRepository.save(bucketGood);
    }

    public List<BucketGood> findAllByUserId(Long id) {
        return bucketGoodRepository.findBucketGoodsByUserId(id);

    }

    public void updateQuantity(long bucketGoodId, int quantity) {
        BucketGood bucketGood = bucketGoodRepository.findById(bucketGoodId).orElseThrow(GoodNotFoundException::new);
        long goodId = bucketGood.getGood().getId();
        int realGoodQuantity = goodService.findById(goodId).getQuantity();
        if (realGoodQuantity < quantity) {
            throw new RuntimeException();
        }
        bucketGood.setQuantity(quantity);
        bucketGoodRepository.save(bucketGood);

    }

    public void deleteAll(List<BucketGood> listOfBucketGoods) {
        bucketGoodRepository.deleteAll(listOfBucketGoods);
    }

   // @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void cleanBucket(){
      deleteAll(bucketGoodRepository.findOldGoods());
    }

}
