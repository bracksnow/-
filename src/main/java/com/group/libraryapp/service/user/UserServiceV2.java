package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.domain.UserRepository;
import com.group.libraryapp.dto.User.request.UserCreateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;


    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void saveUser(UserCreateRequest userCreateRequest){
        userRepository.save(new User(userCreateRequest.getName(), userCreateRequest.getAge()));
    }
    public List<UserResponse> getUser() {
        return userRepository.findAll().stream()
                .map(UserResponse::new).collect(Collectors.toList());
    }
}
