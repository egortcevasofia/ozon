package com.example.ozon.service;

import com.example.ozon.criterias.GoodPageAndSort;
import com.example.ozon.criterias.GoodSearchCriteria;
import com.example.ozon.dto.GoodDto;
import com.example.ozon.dto.GoodMapper;
import com.example.ozon.repository.GoodCritariaRepository;
import com.example.ozon.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
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

}
