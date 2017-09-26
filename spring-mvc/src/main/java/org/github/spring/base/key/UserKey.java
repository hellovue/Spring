//*****************************************************************************
// The file is automatically generated by the program, do not manually modify. 
//*****************************************************************************

package org.github.spring.base.key;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import org.github.spring.footstone.AbstractEntity;

/**
 * UserKey [ sampledb.t_user ].
 *
 * @author MyBatisGenerator
 * @version 2017-09-21
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UserKey extends AbstractEntity implements Serializable {
  /** user_id [ t_user.user_id ]. */
  private Integer userId;

  /**
   * GET user_id [ t_user.user_id ].
   *
   * @return java.lang.Integer
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * WITH user_id [ t_user.user_id ].
   *
   * @param userId java.lang.Integer
   * @return org.github.spring.base.key.UserKey
   */
  public UserKey withUserId(Integer userId) {
    this.setUserId(userId);
    return this;
  }

  /**
   * SET user_id [ t_user.user_id ].
   *
   * @param userId java.lang.Integer
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}