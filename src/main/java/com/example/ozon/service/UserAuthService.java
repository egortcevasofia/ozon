package com.example.ozon.service;

import com.example.ozon.domain.User;
import com.example.ozon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFromDb = userRepository.findUserByeMail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                userFromDb.getEMail(),
                userFromDb.getPassword(),
                userFromDb.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));

    }

}
