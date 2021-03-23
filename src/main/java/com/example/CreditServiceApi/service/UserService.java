package com.example.CreditServiceApi.service;

import com.example.CreditServiceApi.domain.mainEntity.Credit;
import com.example.CreditServiceApi.domain.mainEntity.User;
import com.example.CreditServiceApi.repo.CreditRepo;
import com.example.CreditServiceApi.repo.RoleRepo;
import com.example.CreditServiceApi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final CreditRepo creditRepo;

    @Autowired
    public UserService(CreditRepo creditRepo, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.creditRepo = creditRepo;
    }

    public User saveUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null){
            return null;
        }
        user.setRole(roleRepo.findByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public Optional<User> userEdit(User newUser, Long userId){
        return userRepo.findById(userId).map(user -> {
            user.setUsername(newUser.getUsername() != null ? newUser.getUsername() : user.getUsername());
            user.setFirstName(newUser.getFirstName() != null ? newUser.getFirstName() : user.getFirstName());
            user.setLastName(newUser.getLastName() != null ? newUser.getLastName() : user.getLastName());
            user.setAge(newUser.getAge() != 0 ? newUser.getAge() : user.getAge());
            user.setEmailAddress(newUser.getEmailAddress() != null ? newUser.getEmailAddress() : user.getEmailAddress());
            user.setPassword(passwordEncoder.encode(newUser.getPassword() != null ? newUser.getPassword() : user.getPassword()));
            return userRepo.save(user);
        });
    }

    public boolean deleteUser(Long userId){
        List<Credit> debts = creditRepo.findByUserOwner(userRepo.findOneByUserId(userId));
        for( Credit credit : debts){
            if(!credit.isPaidOff())
                return false;
        }
        userRepo.deleteById(userId);
        return true;
    }
    
    public User saveAdmin(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null){
            return null;
        }
        user.setRole(roleRepo.findByName("ROLE_ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}