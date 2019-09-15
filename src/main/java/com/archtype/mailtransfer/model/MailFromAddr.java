package com.archtype.mailtransfer.model;

/**
 * @author: Think
 * @date: 2019/9/15
 */
public class MailFromAddr {

    /**
     * mail username
     */
    private String username;

    /**
     * mail password
     */
    private String password;

    /**
     * mail host
     */
    private String host;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public MailFromAddr(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }
}
