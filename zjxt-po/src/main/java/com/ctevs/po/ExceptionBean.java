package com.ctevs.po;

import java.io.Serializable;

public class ExceptionBean implements Serializable {
    private static final long serialVersionUID = -1453032858436029542L;
    private int id;
    private String message;
    private Throwable throwable;

    public ExceptionBean() {
    }

    public ExceptionBean(String message) {
        this.message = message;
    }

    public ExceptionBean(String message, Throwable cause) {
        this.message = message;
        this.throwable = cause;
    }

    public ExceptionBean(Throwable cause) {
        this.throwable = cause;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
