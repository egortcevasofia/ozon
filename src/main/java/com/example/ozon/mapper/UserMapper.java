package com.example.ozon.mapper;

import com.example.ozon.domain.User;
import com.example.ozon.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User UserDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
