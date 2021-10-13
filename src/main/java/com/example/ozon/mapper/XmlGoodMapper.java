package com.example.ozon.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface XmlGoodMapper {

    com.example.ozon.domain.Good xmlGoodToGood(generated.Good good);

}
