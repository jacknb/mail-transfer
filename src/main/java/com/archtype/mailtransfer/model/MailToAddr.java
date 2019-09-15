package com.archtype.mailtransfer.model;

/**
 * @author: Think
 * @date: 2019/9/15
 */
public class MailToAddr {

    private String to;

    public MailToAddr(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
