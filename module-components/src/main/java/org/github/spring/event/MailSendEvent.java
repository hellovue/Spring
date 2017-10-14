package org.github.spring.event;

import org.github.spring.bootstrap.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * MailSendEvent.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class MailSendEvent extends ApplicationContextEvent {
    /** Constructor. */
    public MailSendEvent() {
        this(ApplicationContextHolder.getApplicationContext());
    }

    /**
     * Constructor.
     *
     * @param applicationContext
     */
    public MailSendEvent(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}