package com.archtype.mailtransfer.server;

import org.springframework.stereotype.Service;

/**
 * @author: Think
 * @date: 2019/9/15
 */
@Service
public interface MailSendServer {

    void mailSend() throws Exception;
}
