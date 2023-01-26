package com.spring.learnbruteforcesecurity.core.email.service;

import javax.mail.MessagingException;

import com.spring.learnbruteforcesecurity.core.email.context.AbstractEmailContext;

public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;
}

