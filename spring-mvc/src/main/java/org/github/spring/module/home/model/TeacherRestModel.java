package org.github.spring.module.home.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.github.spring.base.entity.UsersEntity;
import org.github.spring.common.constant.EntityInterface;

import java.util.Date;

/**
 * TeacherRestModel.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class TeacherRestModel extends UsersEntity implements EntityInterface {
    @JsonFormat(pattern = FORMAT_TIME)
    public Date getDate() {
        return new Date();
    }

    public String getNull() {
        return null;
    }

    public Date getTime() {
        return new Date();
    }
}
