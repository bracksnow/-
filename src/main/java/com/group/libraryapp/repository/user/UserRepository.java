package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.dto.User.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(Long id){
        String readSql = "SELECT * FROM USER WHERE ID = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum)->0, id).isEmpty();
    }
    public void updateUserName(String name, long id){
        String sql = "UPDATE FROM NAME = ? WHERE ID = ?";
        jdbcTemplate.update(sql, name, id);
    }
    public void saveUser(String name, int age){
        String sql = "INSERT INTO USER (NAME, AGE) VALUES (?,?)";
        jdbcTemplate.update(sql,name, age);
    }
    public boolean isUserNotExistByName(String name){
        String readSql = "SELECT * FROM USER WHERE NAME = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum)->0, name).isEmpty();
    }
    public void deleteUser(String name){
        String sql = "DELETE FROM USER WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public List<UserResponse> getUser(){
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, (rs, rowNum)-> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, new User(name, age));
        });

    }
}
