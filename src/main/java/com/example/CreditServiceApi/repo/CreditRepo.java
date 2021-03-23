package com.example.CreditServiceApi.repo;

import com.example.CreditServiceApi.domain.mainEntity.User;
import com.example.CreditServiceApi.domain.mainEntity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CreditRepo extends JpaRepository<Credit, Long> {
    List<Credit> findByUserOwner(User user);
    Credit findOneById(Long creditId);
}
