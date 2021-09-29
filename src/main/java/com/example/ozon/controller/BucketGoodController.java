package com.example.ozon.controller;

import com.example.ozon.domain.BucketGood;
import com.example.ozon.service.BucketGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bucket")
public class BucketGoodController {
    private BucketGoodService bucketGoodService;

    @Autowired
    public BucketGoodController(BucketGoodService bucketGoodService) {
        this.bucketGoodService = bucketGoodService;
    }

    @GetMapping
    public List<BucketGood> getUsersBucket(HttpServletRequest request) {
        return bucketGoodService.findAllByUserEmail(request.getRemoteUser());
    }
    @PostMapping
    public void updateQuantity(@RequestParam(value = "bucketGoodId") long bucketGoodId,
                               @RequestParam(value = "quantity") int quantity){

        bucketGoodService.updateQuantity(bucketGoodId, quantity);
    }


}
