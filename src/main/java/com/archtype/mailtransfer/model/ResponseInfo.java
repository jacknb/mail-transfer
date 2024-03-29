package com.archtype.mailtransfer.model;

/**
 * @author: Think
 * @date: 2019/9/15
 */
public class ResponseInfo {

    private String retCode;

    private String retMsg;

    private Object data;

    public String getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public Object getData() {
        return data;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseInfo(String retCode, String retMsg, Object data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.data = data;
    }

    public ResponseInfo() {
        this.retCode = "0000";
        this.retMsg = "Success";
    }
}
