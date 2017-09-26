package org.github.spring.footstone;

import java.io.Serializable;

/**
 * Const of entity.
 *
 * @author JYD_XL
 * @see java.io.Serializable
 * @see org.github.spring.footstone.ConstInterface
 */
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable, ConstInterface {
  @Override
  public String toString() {
    return JSONMapperHolder.getWebJSONMapper().toJson(this);
  }
}
