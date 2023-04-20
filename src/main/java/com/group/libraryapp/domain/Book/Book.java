package com.group.libraryapp.domain.Book;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;

    @Column(nullable = false)
    String name;

    public Book(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name (%s)가 들어왔습니다", name));
        }
        this.name = name;
    }

    public Book() {
    }
}
