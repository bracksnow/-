package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.User.request.UserCreateRequest;
import com.group.libraryapp.dto.User.request.UserUpdateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserServiceV1 {
    private final UserJdbcRepository userRepository;

    public UserServiceV1(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserJdbcRepository(jdbcTemplate);
    }

    public void updateUser(UserUpdateRequest userUpdateRequest){
        if(userRepository.isUserNotExist(userUpdateRequest.getId())){
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    public void saveUser(UserCreateRequest userCreateRequest) {
        userRepository.saveUser(userCreateRequest.getName(), userCreateRequest.getAge());
    }
    public void deleteUser(String name){
        if(userRepository.isUserNotExistByName(name)){
            throw new IllegalArgumentException();
        }
        userRepository.deleteUser(name);
    }

    public List<UserResponse> getUser(){
        return userRepository.getUser();

    }
}
