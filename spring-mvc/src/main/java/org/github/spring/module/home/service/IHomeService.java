package org.github.spring.module.home.service;

import org.github.spring.footstone.PageHelperModel;
import org.github.spring.module.home.model.TeacherCondModel;
import org.github.spring.restful.JSONReturn;
import org.github.spring.common.service.ICommonService;

/**
 * CommonService.
 *
 * @author JYD_XL
 */
public interface IHomeService extends ICommonService {
    /** search. */
    JSONReturn search(TeacherCondModel condModel, PageHelperModel pageHelperModel);
}
