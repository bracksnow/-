package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.domain.UserRepository;
import com.group.libraryapp.dto.User.request.UserCreateRequest;
import com.group.libraryapp.dto.User.request.UserUpdateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;


    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest userCreateRequest){
        userRepository.save(new User(userCreateRequest.getName(), userCreateRequest.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUser() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    //영속성 컨텍스트는 더티체크를 진행해 굳이 명시적으로 save를 하지 않아도 된다
    @Transactional
    public void updateUser(UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(IllegalAccessError::new);
        user.updateNser(userUpdateRequest.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
