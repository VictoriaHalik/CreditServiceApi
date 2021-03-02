package com.example.CreditServiceApi.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
@ToString(of = {"first_name", "last_name", "email_address", "age", "password"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.CreditInfo.class, Views.UserInfo.class})
    @Column(name="user_id")
    private Long userId;

    @JsonView(Views.UserInfo.class)
    private  int age;

    @Size(max = 25)
    @JsonView({Views.CreditInfo.class, Views.UserInfo.class})
    private String firstName;

    @Size(max = 25)
    @JsonView({Views.CreditInfo.class, Views.UserInfo.class})
    private String lastName;

    @Email
    @JsonView(Views.UserInfo.class)
    private String emailAddress;

    private String password;

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
