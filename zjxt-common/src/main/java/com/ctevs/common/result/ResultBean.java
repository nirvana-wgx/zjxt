package com.ctevs.common.result;

import java.io.Serializable;
import java.math.BigInteger;

public class ResultBean implements Serializable {
    private static final long serialVersionUID = 8400341083688596453L;
    private String status;
    private String message;
    private  BigInteger id ;//返回插入主键
    private  Object data ;//

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void success() {
        this.status = Result.success.toString();
    }

    public void failure(String message) {
        this.status = Result.failure.toString();
        this.message = message;
    }

    public void error(String message) {
        this.status = Result.Error.toString();
        this.message = message;
    }

    public boolean isSuccess() {
        return Result.success.compare(this.status);
    }

    public boolean isFailure() {
        return Result.failure.compare(this.status);
    }

    public boolean isError() {
        return Result.Error.compare(this.status);
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    
    
}
