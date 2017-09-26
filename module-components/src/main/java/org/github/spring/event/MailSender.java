package org.github.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * MailSender.
 * 
 * @author JYD_XL
 */
public class MailSender implements ApplicationContextAware {
    /** applicationContext. */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /** sendMail. */
    public void sendMail() {
        MailSendEvent event = new MailSendEvent(applicationContext);
        applicationContext.publishEvent(event);
    }
}