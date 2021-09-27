package com.example.ozon.controller;

import com.example.ozon.dto.UserDto;
import com.example.ozon.enums.UserStatus;
import com.example.ozon.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto findUserById(@PathVariable(value = "id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll(
            @RequestParam(value = "size", required = false, defaultValue = "2") Integer size,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            Pageable pageable) {
        return userService.findAll(PageRequest.of(page, size));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @PostMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUserStatus(@RequestBody UserDto userDto) {
        return userService.updateUserStatus(userDto, userDto.getUserStatus());
    }

}
