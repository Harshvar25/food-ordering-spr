package com.cognizant.webapp09.admin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.webapp09.entity.Admin;



@Repository
public class AdminDaoImpl implements AdminDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public AdminDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean checkCredentials(Admin admin) {
        try {
            Admin resultAdmin = jdbcTemplate.queryForObject(
                    "SELECT id, username, password FROM admin WHERE username=? AND password=?",
                    (rs, rowNum) -> {
                        Admin adminResult = new Admin();
                        adminResult.setId(rs.getInt("id"));
                        adminResult.setUsername(rs.getString("username"));
                        adminResult.setPassword(rs.getString("password"));
                        return adminResult;
                    },
                    admin.getUsername(), admin.getPassword());

            admin.setId(resultAdmin.getId());
            admin.setUsername(resultAdmin.getUsername());
            admin.setPassword(resultAdmin.getPassword());
            return true;

        } catch (EmptyResultDataAccessException erdaEx) {
            return false;
        }
    }
}