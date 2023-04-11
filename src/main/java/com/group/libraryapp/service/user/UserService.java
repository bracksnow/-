package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.User.request.UserUpdateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {
    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.userRepository = new UserRepository(jdbcTemplate);
    }

    public void updateUser(UserUpdateRequest userUpdateRequest){
        if(userRepository.isUserNotExist(userUpdateRequest.getId())){
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(userUpdateRequest.getName(), userUpdateRequest.getId());
    }
}
