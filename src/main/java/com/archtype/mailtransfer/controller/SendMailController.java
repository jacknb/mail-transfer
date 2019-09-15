package com.archtype.mailtransfer.controller;

import com.archtype.mailtransfer.model.ResponseInfo;
import com.archtype.mailtransfer.service.MailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Think
 * @date: 2019/9/15
 */
@RestController
public class SendMailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailController.class);

    @Autowired
    private MailSendService mailSendService;

    @RequestMapping(value = "/send/mail", method = RequestMethod.GET)
    public ResponseInfo sendMail() throws Exception {
        ResponseInfo responseInfo = new ResponseInfo();
        mailSendService.mailSend();
        return responseInfo;
    }

}
