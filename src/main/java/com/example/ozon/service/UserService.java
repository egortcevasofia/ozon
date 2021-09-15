package com.example.ozon.service;

import com.example.ozon.domain.User;
import com.example.ozon.dto.UserDto;
import com.example.ozon.dto.UserMapper;
import com.example.ozon.enums.UserStatus;
import com.example.ozon.exception.UserAlreadyExistsException;
import com.example.ozon.exception.UserNotFoundException;
import com.example.ozon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findUserById(id).orElseThrow(UserNotFoundException::new));
    }

    public List<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(user -> userMapper.userToUserDto(user)).collect(Collectors.toList());
    }

    public UserDto createUser(UserDto user) {

        if (userRepository.existsByeMail(user.getEMail())) {
            throw new UserAlreadyExistsException();
        }

        return userMapper.userToUserDto(userRepository.save(userMapper.UserDtoToUser(user)));
    }

    @Transactional
    public UserDto updateUserStatus(UserDto userDto, UserStatus userStatus){
        if (!userRepository.existsById(userDto.getId())){
            throw new UserNotFoundException();
        }
        userRepository.updateUserStatus(userDto.getId(), userStatus);
        return userMapper.userToUserDto(userRepository.findUserById(userDto.getId()).orElseThrow(UserNotFoundException::new));
    }
}
