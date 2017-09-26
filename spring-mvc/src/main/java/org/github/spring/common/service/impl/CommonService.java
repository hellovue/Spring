package org.github.spring.common.service.impl;

import org.github.spring.common.base.Service;
import org.github.spring.common.dao.CommonDao;
import org.github.spring.common.service.ICommonService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * CommonService.
 *
 * @author JYD_XL
 */
public abstract class CommonService extends Service implements ICommonService {
    @Resource
    private CommonDao commonDao;

    @Override
    public Date getNow() {
        return commonDao.getNow();
    }
}