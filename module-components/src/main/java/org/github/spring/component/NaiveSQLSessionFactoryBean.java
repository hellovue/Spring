package org.github.spring.component;

import java.util.EventListener;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;

/**
 * LocaleSqlSessionFactoryBean.
 *
 * @author JYD_XL
 * @see EventListener
 * @see SqlSessionFactoryBean
 * @see FactoryBean
 * @see ApplicationListener
 * @see InitializingBean
 */
public class NaiveSQLSessionFactoryBean extends MybatisSqlSessionFactoryBean {
  @Autowired
  @Override
  public void setPlugins(Interceptor[] plugins) {
    super.setPlugins(plugins);
  }
}