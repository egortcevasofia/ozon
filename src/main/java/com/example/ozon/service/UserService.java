package com.example.ozon.service;

import com.example.ozon.domain.Role;
import com.example.ozon.domain.User;
import com.example.ozon.dto.UserDto;
import com.example.ozon.mapper.UserMapper;
import com.example.ozon.enums.UserStatus;
import com.example.ozon.exception.UserAlreadyExistsException;
import com.example.ozon.exception.UserNotFoundException;
import com.example.ozon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.ozon.common.Constant.RoleName.ROLE_CUSTOMER;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final RoleService roleService;


    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    public UserDto findUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findUserById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserDto findUserByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findUserByeMail(email).orElseThrow(UserNotFoundException::new));
    }

    public List<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(user -> userMapper.userToUserDto(user)).collect(Collectors.toList());
    }


    public UserDto createUser(UserDto userDto) {

        if (userRepository.existsByeMail(userDto.getEMail())) {
            throw new UserAlreadyExistsException();
        }
        return userMapper.userToUserDto(userRepository.save(new User(
                userDto.getId(),
                userDto.getEMail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                encoder.encode(userDto.getPassword()),
                userDto.getUserStatus(),
                new HashSet<Role>(Collections.singletonList(roleService.findRoleByName(ROLE_CUSTOMER))))));
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
