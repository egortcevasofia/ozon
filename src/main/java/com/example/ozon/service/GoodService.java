package com.example.ozon.service;

import com.example.ozon.criterias.GoodPageAndSort;
import com.example.ozon.criterias.GoodSearchCriteria;
import com.example.ozon.domain.BucketGood;
import com.example.ozon.domain.Good;
import com.example.ozon.dto.GoodDto;
import com.example.ozon.exception.GoodNotFoundException;
import com.example.ozon.exception.GoodQuantityNotEnoughException;
import com.example.ozon.mapper.GoodMapper;
import com.example.ozon.repository.GoodCritariaRepository;
import com.example.ozon.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ozon.enums.GoodStatus.ACTIVE;

@Service
public class GoodService {
    private GoodRepository goodRepository;
    private GoodMapper goodMapper;
    private GoodCritariaRepository goodCritariaRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository, GoodMapper goodMapper, GoodCritariaRepository goodCritariaRepository) {
        this.goodRepository = goodRepository;
        this.goodMapper = goodMapper;
        this.goodCritariaRepository = goodCritariaRepository;
    }

    public GoodDto createGood(GoodDto goodDto) {
        return goodMapper.goodToGoodDto(goodRepository.save(goodMapper.goodDtoToGood(goodDto)));
    }

    public List<GoodDto> findAll(GoodPageAndSort goodPageAndSort,
                                 GoodSearchCriteria goodSearchCriteria) {
        return goodCritariaRepository.findAllWithFilters(goodPageAndSort, goodSearchCriteria)
                .stream()
                .map(good -> goodMapper.goodToGoodDto(good)).collect(Collectors.toList());
    }

    public GoodDto findById(Long id) {
        return goodMapper.goodToGoodDto(goodRepository.findById(id).orElseThrow(GoodNotFoundException::new));
    }

    public void buyAllGoods(List<BucketGood> listOfBucketGoods) {
        for (int i = 0; i < listOfBucketGoods.size(); i++) {
            Good good = goodRepository.findById(listOfBucketGoods.get(i).getGood().getId())
                    .orElseThrow(GoodNotFoundException::new);
            int realQuantity = good.getQuantity();

            if (realQuantity < listOfBucketGoods.get(i).getQuantity()) {
                throw new GoodQuantityNotEnoughException();
            }
            goodRepository.setNewQuantity(good.getId(), realQuantity - listOfBucketGoods.get(i).getQuantity());
        }

    }

    public List<Good> saveAll(List<Good> list) {
        list.forEach(good -> good.setGoodStatus(ACTIVE));
        return goodRepository.saveAll(list);
    }
}
