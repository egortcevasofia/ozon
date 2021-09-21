package com.example.ozon.dto;

import com.example.ozon.domain.Good;
import com.example.ozon.domain.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface GoodMapper {
    Good goodDtoToGood(GoodDto goodDto);

    GoodDto goodToGoodDto(Good good);
}
