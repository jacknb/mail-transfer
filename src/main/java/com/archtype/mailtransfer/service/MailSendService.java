package com.archtype.mailtransfer.service;

import org.springframework.stereotype.Service;

/**
 * @author: Think
 * @date: 2019/9/15
 */
@Service
public interface MailSendService {

    /**
     * send mail
     *
     * @throws Exception
     */
    void mailSend() throws Exception;
}
