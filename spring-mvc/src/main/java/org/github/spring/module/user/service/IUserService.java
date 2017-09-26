package org.github.spring.module.user.service;

import org.github.spring.base.entity.UserEntity;

/**
 * Created by l on 7/1/17.
 */
public interface IUserService {
    public boolean hasMatchUser(String username, String password);

    public UserEntity findUserByUserName(String username);

    public void loginSuccess(UserEntity userEntity);
}
