package com.example.ozon.repository;

import com.example.ozon.domain.User;
import com.example.ozon.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findUserById(Long id);
    User save(User user);
    boolean existsByeMail(String email);
    Optional<User> findUserByeMail(String email);
    @Modifying
    @Query("update User u set u.userStatus = :userStatus where u.id = :userId")
    void updateUserStatus(@Param("userId") Long userId, @Param("userStatus") UserStatus userStatus);

}
