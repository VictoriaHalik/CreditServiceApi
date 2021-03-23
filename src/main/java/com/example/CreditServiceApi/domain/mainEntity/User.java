package com.example.CreditServiceApi.domain.mainEntity;

import com.example.CreditServiceApi.domain.additionalEntity.Role;
import com.example.CreditServiceApi.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
@ToString(of = {"first_name", "last_name", "email_address", "age", "password"})
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.CreditInfo.class, Views.UserInfo.class})
    @Column(name="user_id")
    private Long userId;

    @NotNull
    @JsonView(Views.UserInfo.class)
    private String username;

    @NotNull
    @JsonView(Views.UserInfo.class)
    private  int age;

    @NotNull
    @Size(max = 25)
    @JsonView({Views.CreditInfo.class, Views.UserInfo.class})
    private String firstName;

    @NotNull
    @Size(max = 25)
    @JsonView({Views.CreditInfo.class, Views.UserInfo.class})
    private String lastName;

    @NotNull
    @Email
    @JsonView(Views.UserInfo.class)
    private String emailAddress;

    @NotNull
    private String password;

    @ManyToOne
    @JsonView(Views.UserInfo.class)
    @JoinColumn(name = "roles_id")
    private Role role;



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.emailAddress = mailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
