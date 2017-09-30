package org.github.spring.module.home.service;

import org.github.spring.common.service.ICommonService;
import org.github.spring.module.home.model.TeacherCondModel;
import org.github.spring.restful.JSONReturn;

/**
 * CommonService.
 *
 * @author JYD_XL
 */
public interface IHomeService extends ICommonService {
  /** search. */
  JSONReturn search(TeacherCondModel condModel);
}
