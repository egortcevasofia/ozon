package com.example.ozon.dto;

import com.example.ozon.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
