package com.example.ozon.service;

import com.example.ozon.domain.BoughtGood;
import com.example.ozon.domain.Receipt;
import com.example.ozon.domain.User;
import com.example.ozon.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReceiptService {
    private ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt createReceipt(List<BoughtGood> listOfBoughtGoods, User user) {
        Receipt receipt = new Receipt(user, LocalDateTime.now(), listOfBoughtGoods);
        return receiptRepository.save(receipt);
    }
}
