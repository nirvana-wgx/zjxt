package com.ctevs.common.beans;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
 
public class Vo implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     *  
     */
    @JSONField(serialize = false)
    private int limit;
    /**
     *  
     */
    @JSONField(serialize = false)
    private int offset;

    @JSONField(serialize = false)
    private boolean isShard;
    
    private String collectime;

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isShard() {
        return isShard;
    }

    public void setShard(boolean isShard) {
        this.isShard = isShard;
    }

    public String getCollectime() {
        return collectime;
    }

    public void setCollectime(String collectime) {
        this.collectime = collectime;
    }

     

    
    
    
}
