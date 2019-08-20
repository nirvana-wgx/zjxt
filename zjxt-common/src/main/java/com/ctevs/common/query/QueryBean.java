package com.ctevs.common.query;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
/**
 * 原子服务基类
 *
 * @author haibing.xiao
 * @date: 2016年7月16日 上午9:54:09
 * @version 1.0
 * @since JDK 1.7
 */
public class QueryBean implements java.io.Serializable{
    private static final long serialVersionUID = -5016968481992128023L;
    private Map<String, Object> f = new LinkedHashMap<String, Object>();
    private int pageNo = 1;

    private int pageRows = 20;

    private int totalCount = 0;
    
    private int totalPages;
     

    public void addF(String paramKey, Object value) {
        if ((null != value) && (StringUtils.isNotBlank(value.toString())))
            this.f.put(paramKey, StringUtils.trim(value.toString()));
    }

     

    public int getPageRows() {
        return this.pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        if (this.pageNo < 1) {
            this.pageNo = 1;
        }

        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getFirst() {
        return (getPageNo() - 1) * this.pageRows + 1;
    }

    public int getLast() {
        return getFirst() + this.pageRows > getTotalCount() ? getTotalCount() : getFirst() + this.pageRows - 1;
    }

    public int getTotalPages() {
        this.totalPages = (this.totalCount + this.pageRows - 1) / this.pageRows;
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        if (totalPages == 0)
            totalPages = 1;
        this.totalPages = totalPages;
    }

    public void resetTotalCount(int totalCount) {
        if (totalCount < 0) {
            totalCount = 0;
        }
        int count = (totalCount + this.pageRows - 1) / this.pageRows;
        setTotalCount(totalCount);
        setTotalPages(count);
       if (getPageNo() > getTotalPages())
            setPageNo(getTotalPages()); 
    }

    public boolean isHasNext() {
        return this.pageNo + 1 <= getTotalPages();
    }

    public int getNextPage() {
        if (isHasNext()) {
            return this.pageNo + 1;
        }
        return this.pageNo;
    }

    public boolean isHasPre() {
        return this.pageNo - 1 >= 1;
    }

    public int getPrePage() {
        if (isHasPre()) {
            return this.pageNo - 1;
        }
        return this.pageNo;
    }

    public int getBeginCount() {
        int beginCount = (getPageNo() - 1) * this.pageRows;
        if (beginCount >= getTotalCount()) {
            int modpageSize = getTotalCount() % this.pageRows;
            if (modpageSize == 0) {
                modpageSize = this.pageRows;
            }
            beginCount = getTotalCount() + 1 - modpageSize;
        }
        return beginCount < 0 ? 0 : beginCount;
    }

     
    public Map<String, Object> getF() {
        return this.f;
    }

    public void setF(Map<String, Object> f) {
        this.f = f;
    }

    

   

    public String getParaValue(String key) {
        Object value = this.f.get(key);
        if (null != value) {
            return value.toString();
        }
        return null;
    }
   
   
     
      
    
}
