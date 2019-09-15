package com.archtype.mailtransfer.model;

/**
 * @author: Think
 * @date: 2019/9/15
 */
public class MailInfo {

    /**
     * mail subject
     */
    private String subject;

    /**
     * mail content
     */
    private String content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MailInfo(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public MailInfo() {
    }
}
