package org.github.spring.common.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

import javax.annotation.Resource;

/**
 * CommonDao.
 *
 * @author JYD_XL
 */
@Repository
public class CommonDao {
    /** jdbcTemplate. */
    @Resource
    private JdbcTemplate jdbcTemplate;

    /** SQL-now. */
    private String sql = "SELECT NOW()";

    /**
     * getNow.
     *
     * @return Date
     */
    public Date getNow() {
        return jdbcTemplate.queryForObject(sql, null, Date.class);
    }
}
