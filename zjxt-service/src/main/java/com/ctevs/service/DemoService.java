package com.ctevs.service;

import com.ctevs.po.DemoPo;

/**
 * @author hu_qzh
 * @version : 1.0
 * @Date Created on 2019/8/16 14:19
 * @Description : DemoService
 */
public interface DemoService {
    /**
     * 查询
     * @param demoPo
     * @return
     */
    DemoPo queryDemo(DemoPo demoPo);

    /**
     * 插入
     * @param demoPo
     */
    void insertDemo(DemoPo demoPo) throws Exception;
}
