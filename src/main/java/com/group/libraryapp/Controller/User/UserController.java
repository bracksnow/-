package com.group.libraryapp.Controller.User;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.User.request.UserCreateRequest;
import com.group.libraryapp.dto.User.request.UserUpdateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(JdbcTemplate jdbcTemplate){
        this.userService = new UserService(jdbcTemplate);
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

    //람다를 사용해 함수를 줄임
    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userService.getUser();
    }

    //삭제 및 업데이트는 jdbcTemplate.update 사용
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUser(userUpdateRequest);
    }
}
