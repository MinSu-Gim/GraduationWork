package com.brother.graduationwork.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    private String user_email;

    private String user_pw;

    private String user_nickname;

    private String user_phoneNumber;

    public User(String user_email, String user_pw, String nickname, String phoneNumber) {
        this.user_email = user_email;
        this.user_pw = user_pw;
        this.user_nickname = nickname;
        this.user_phoneNumber = phoneNumber;
    }

    public User() {

    }
}
