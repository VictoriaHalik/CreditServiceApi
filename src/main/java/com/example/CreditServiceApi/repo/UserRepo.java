package com.example.CreditServiceApi.repo;

import com.example.CreditServiceApi.domain.additionalEntity.Role;
import com.example.CreditServiceApi.domain.mainEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findOneByUserId (Long userId);
    User findByUsername(String username);
    List<User> findAllByRole(Role role);
}
