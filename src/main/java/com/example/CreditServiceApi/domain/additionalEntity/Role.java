package com.example.CreditServiceApi.domain.additionalEntity;

import com.example.CreditServiceApi.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    @JsonView({Views.UserInfo.class})
    private Integer id;

    @Column
    @JsonView({Views.UserInfo.class})
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}