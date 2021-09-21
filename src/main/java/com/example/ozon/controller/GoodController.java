package com.example.ozon.controller;

import com.example.ozon.criterias.GoodPageAndSort;
import com.example.ozon.criterias.GoodSearchCriteria;
import com.example.ozon.dto.GoodDto;
import com.example.ozon.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/good")
public class GoodController {
    private GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping
    public ResponseEntity<List<GoodDto>> getEmployees(GoodPageAndSort goodPageAndSort,
                                                      GoodSearchCriteria goodSearchCriteria) {
        return new ResponseEntity<>(goodService.findAll(goodPageAndSort, goodSearchCriteria),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoodDto> createGood(@RequestBody GoodDto goodDto) {
        return new ResponseEntity<>(goodService.createGood(goodDto), HttpStatus.OK);
    }

}
