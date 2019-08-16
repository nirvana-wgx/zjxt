package com.ctevs.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hu_qzh
 * @version : 1.0
 * @Date Created on 2019/8/16 14:32
 * @Description : demoVo
 */
@Data
public class DemoVo implements Serializable {

    //序列化ID
    private static final long serialVersionUID = -8556276327956295162L;

    private String userId;
    private String userName;
    private int age;

}
