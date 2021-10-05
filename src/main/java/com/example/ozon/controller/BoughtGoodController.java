package com.example.ozon.controller;

import com.example.ozon.service.BoughtGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/bought")
public class BoughtGoodController {

    private BoughtGoodService boughtGoodService;

    @Autowired
    public BoughtGoodController(BoughtGoodService boughtGoodService) {
        this.boughtGoodService = boughtGoodService;
    }

    @PostMapping
    public void buyGoodsFromBucket(Principal principal) {
        boughtGoodService.buyGoodsFromBucket(principal.getName());

    }
}
