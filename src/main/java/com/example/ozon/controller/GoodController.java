package com.example.ozon.controller;

import com.example.ozon.criterias.GoodPageAndSort;
import com.example.ozon.criterias.GoodSearchCriteria;
import com.example.ozon.domain.BucketGood;
import com.example.ozon.dto.GoodDto;
import com.example.ozon.service.BucketGoodService;
import com.example.ozon.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/good")
public class GoodController {
    private GoodService goodService;
    private BucketGoodService bucketGoodService;

    @Autowired
    public GoodController(GoodService goodService, BucketGoodService bucketGoodService) {
        this.goodService = goodService;
        this.bucketGoodService = bucketGoodService;
    }

    @GetMapping
    public ResponseEntity<List<GoodDto>> getGoods(GoodPageAndSort goodPageAndSort,
                                                  GoodSearchCriteria goodSearchCriteria) {
        return new ResponseEntity<>(goodService.findAll(goodPageAndSort, goodSearchCriteria),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GoodDto findGoodById(@PathVariable(value = "id") Long id) {
        return goodService.findById(id);
    }

    @PostMapping
    public ResponseEntity<GoodDto> createGood(@RequestBody GoodDto goodDto) {
        return new ResponseEntity<>(goodService.createGood(goodDto), HttpStatus.OK);
    }


    @PostMapping("/{id}/bucket")
    public BucketGood putInBucket(@PathVariable(value = "id") Long goodId, HttpServletRequest request) {
        return bucketGoodService.creatBucketGood(request.getRemoteUser(), goodId);
    }
}
