package com.ctevs.common.vo;

import java.io.Serializable;

/**
 * @author hu_qzh
 * @version : 1.0
 * @Date Created on 2019/8/16 14:32
 * @Description : demoVo
 */
public class DemoVo implements Serializable {

    //序列化ID
    private static final long serialVersionUID = -8556276327956295162L;

    private String userId;
    private String userName;
    private int age;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
