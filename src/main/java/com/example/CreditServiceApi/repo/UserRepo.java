package com.example.CreditServiceApi.repo;

import com.example.CreditServiceApi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserId (Long userId);
}
