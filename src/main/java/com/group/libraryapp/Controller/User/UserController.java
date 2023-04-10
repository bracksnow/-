package com.group.libraryapp.Controller.User;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.User.request.UserCreateRequest;
import com.group.libraryapp.dto.User.request.UserUpdateRequest;
import com.group.libraryapp.dto.User.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        String sql = "INSERT INTO USER (NAME, AGE) VALUES (?,?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    //람다를 사용해 함수를 줄임
    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, (rs, rowNum)-> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, new User(name, age));
        });
    }

    //삭제 및 업데이트는 jdbcTemplate.update 사용
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        String readSql = "SELECT * FROM USER WHERE NAME = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum)-> 0, name).isEmpty();
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }
        String sql = "DELETE FROM USER WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        String readSql = "SELECT * FROM USER WHERE ID = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum)->0, userUpdateRequest.getId()).isEmpty();
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }
        String sql = "UPDATE FROM NAME = ? WHERE ID = ?";
        jdbcTemplate.update(sql, userUpdateRequest.getName(), userUpdateRequest.getId());

    }
}
