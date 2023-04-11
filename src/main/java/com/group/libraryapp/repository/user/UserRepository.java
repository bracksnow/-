package com.group.libraryapp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;

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
}
