//*****************************************************************************
// The file is automatically generated by the program, do not manually modify. 
//*****************************************************************************

package org.github.spring.base.key;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import org.github.spring.footstone.AbstractEntity;

/**
 * LoginLogKey [ sampledb.t_login_log ].
 *
 * @author MyBatisGenerator
 * @version 2017-09-21
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class LoginLogKey extends AbstractEntity implements Serializable {
  /** login_log_id [ t_login_log.login_log_id ]. */
  private Integer loginLogId;

  /**
   * GET login_log_id [ t_login_log.login_log_id ].
   *
   * @return java.lang.Integer
   */
  public Integer getLoginLogId() {
    return loginLogId;
  }

  /**
   * WITH login_log_id [ t_login_log.login_log_id ].
   *
   * @param loginLogId java.lang.Integer
   * @return org.github.spring.base.key.LoginLogKey
   */
  public LoginLogKey withLoginLogId(Integer loginLogId) {
    this.setLoginLogId(loginLogId);
    return this;
  }

  /**
   * SET login_log_id [ t_login_log.login_log_id ].
   *
   * @param loginLogId java.lang.Integer
   */
  public void setLoginLogId(Integer loginLogId) {
    this.loginLogId = loginLogId;
  }
}