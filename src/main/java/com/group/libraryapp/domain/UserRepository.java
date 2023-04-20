package com.group.libraryapp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {//객체 및 기본키

    Optional<User> findByName(String name);

    boolean existsByName(String name);

}
