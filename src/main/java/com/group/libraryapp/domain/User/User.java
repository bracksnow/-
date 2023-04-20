package com.group.libraryapp.domain.User;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB마다 자동생성 전략이 다르다 -> MySQL은 IDENTITY로 생성
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    private Integer age;

    protected User() {//JPA 생성시 기본생성자 필요

    }

    public User(String name, Integer age) {

        if (name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name (%s)가 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void updateNser(String name){
        this.name = name;

    }
}
