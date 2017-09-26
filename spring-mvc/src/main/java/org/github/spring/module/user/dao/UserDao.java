package org.github.spring.module.user.dao;

import org.github.spring.base.entity.UserEntity;
import org.github.spring.exception.HandlingException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

/**
 * Created by l on 7/1/17.
 */
@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_MATCH_COUNT = " SELECT count(0) " + " FROM t_user u " + "WHERE u.user_name = ? AND u.password = ? ";

    private static final String SQL_FIND_USER_BY_USER_NAME = " SELECT * " + " FROM t_user u " + " WHERE u.user_name = ? ";

    private static final String SQL_UPDATE_LOGIN_INFO = " UPDATE t_user " + " SET last_visit = ?, last_ip = ?, credits = ? " + " WHERE user_id = ? ";

    public int getMatchCount(String username, String password) {
        Integer result = jdbcTemplate.queryForObject(SQL_GET_MATCH_COUNT, Integer.class, username, password);
        return result == null ? 0 : result;
    }

    public UserEntity findUserByUserName(String userName) {
        return jdbcTemplate.queryForObject(SQL_FIND_USER_BY_USER_NAME, this::mapRowToUser, userName);
    }

    public int updateLoginInfo(UserEntity entity) {
        return jdbcTemplate.update(SQL_UPDATE_LOGIN_INFO, entity.getLastVisit(), entity.getLastIp(), entity.getCredits(), entity.getUserId());
    }

    private UserEntity mapRowToUser(ResultSet rs, int rowNum) {
        try {
            return (UserEntity) new UserEntity().withUserName(rs.getString("user_name")).withCredits(rs.getInt("credits")).withPassword(rs.getString("password")).withUserId(rs.getInt("user_id"));
        } catch (SQLException e) {
            throw new HandlingException(e.getMessage(), e);
        }
    }
}
