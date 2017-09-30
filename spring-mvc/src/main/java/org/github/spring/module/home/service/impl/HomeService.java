package org.github.spring.module.home.service.impl;

import javax.annotation.Resource;

import org.github.spring.annotation.Logger;
import org.github.spring.base.example.UsersExample;
import org.github.spring.base.mapper.UsersMapper;
import org.github.spring.common.service.impl.CommonService;
import org.github.spring.module.home.model.TeacherCondModel;
import org.github.spring.module.home.model.TeacherRestModel;
import org.github.spring.module.home.service.IHomeService;
import org.github.spring.restful.JSONReturn;
import org.github.spring.restful.json.JSONPageReturn;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Service
public class HomeService extends CommonService implements IHomeService {
  @Resource
  private UsersMapper usersMapper;

  @Logger
  @Override
  public JSONReturn search(TeacherCondModel condModel) {
    val criteria = new UsersExample().createCriteria();
    condModel.getCrudHelper().startCrud(criteria);
    condModel.getPageHelper().startPageOrderByDefault();
    val page = usersMapper.selectByExample(criteria.example());
    return JSONPageReturn.of(page, TeacherRestModel.class);
  }
}
