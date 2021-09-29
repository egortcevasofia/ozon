package com.example.ozon.controller;

import com.example.ozon.service.BoughtGoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bought")
public class BoughtGoodController {
    private BoughtGoodService boughtGoodService;

    @PostMapping
    public void buyGoodsFromBucket(HttpServletRequest request){
        boughtGoodService.buyGoodsFromBucket(request.getRemoteUser());

    }
}
