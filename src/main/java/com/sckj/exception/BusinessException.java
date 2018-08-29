//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sckj.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 2332608236621015980L;
    private String type = "B-";
    private Object[] msgArgs;
    private Object data;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object[] getMsgArgs() {
        return msgArgs;
    }

    public void setMsgArgs(Object[] msgArgs) {
        this.msgArgs = msgArgs;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
