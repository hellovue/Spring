package org.github.spring.module.log.dao;

import org.github.spring.base.entity.LoginLogEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by l on 7/1/17.
 */
@Repository
public class LoginLogDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_LOGIN_LOG = " INSERT INTO t_login_log (user_id, ip, login_datetime) VALUES (?, ?, ?) ";

    public int insertLoginLog(LoginLogEntity entity) {
        return jdbcTemplate.update(SQL_INSERT_LOGIN_LOG, entity.getUserId(), entity.getIp(), entity.getLoginDatetime());
    }
}
