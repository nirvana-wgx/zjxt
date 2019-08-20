package com.ctevs.po;


public class Entity {
    private int limit;
    private int offset;
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
