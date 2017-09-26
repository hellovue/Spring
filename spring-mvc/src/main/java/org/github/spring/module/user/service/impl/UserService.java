package org.github.spring.module.user.service.impl;

import lombok.val;
import org.github.spring.base.entity.LoginLogEntity;
import org.github.spring.base.entity.UserEntity;
import org.github.spring.module.log.dao.LoginLogDao;
import org.github.spring.module.user.dao.UserDao;
import org.github.spring.module.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by l on 7/1/17.
 */
@Service
public class UserService implements IUserService {
    @Resource
    private UserDao userDao;

    @Resource
    private LoginLogDao loginLogDao;

    @Override
    public boolean hasMatchUser(String username, String password) {
        return userDao.getMatchCount(username, password) == 1;
    }

    @Override
    public UserEntity findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public void loginSuccess(UserEntity userEntity) {
        userEntity.setCredits(userEntity.getCredits() + 5);
        userDao.updateLoginInfo(userEntity);

        val loginLogEntity = new LoginLogEntity();
        loginLogEntity.withUserId(userEntity.getUserId()).withIp(userEntity.getLastIp()).withLoginDatetime(userEntity.getLastVisit());
        loginLogDao.insertLoginLog(loginLogEntity);
    }
}
