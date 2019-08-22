 package com.ctevs.common.exception;

import com.ctevs.common.beans.ExceptionBean;


public class LogicException extends BaseException {
    private static final long serialVersionUID = -1907958242636293000L;
    private String code;

    public LogicException() {
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }

    public LogicException(String code, String message) {
        super(message);
        this.code = code;
    }

    public LogicException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ExceptionBean getBean() {
        return super.getBean();
    }
}
