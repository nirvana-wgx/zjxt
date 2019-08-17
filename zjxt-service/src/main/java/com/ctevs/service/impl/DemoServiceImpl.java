package com.ctevs.service.impl;

import com.ctevs.dao.DemoInformationMapper;
import com.ctevs.entity.DemoInformation;
import com.ctevs.po.DemoPo;
import com.ctevs.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hu_qzh
 * @version : 1.0
 * @Date Created on 2019/8/16 14:20
 * @Description : DemoServiceImpl
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    DemoInformationMapper demoInformationDao;

    @Override
    public DemoPo queryDemo(DemoPo demoPo) {

        DemoInformation demoInformation = demoInformationDao.selectByPrimaryKey(demoPo.getUserId());

        demoPo.setUserId(demoInformation.getUserId());
        demoPo.setUserName(demoInformation.getUserName());
        demoPo.setAge(demoInformation.getAge());

        return demoPo;
    }

    @Override
    public void insertDemo(DemoPo demoPo) throws Exception {
        DemoInformation demoInformation = new DemoInformation();
        demoInformation.setUserId(demoPo.getUserId());
        demoInformation.setUserName(demoPo.getUserName());
        demoInformation.setAge(demoPo.getAge());

        if(demoInformationDao.insert(demoInformation)<=0){
            throw new Exception();
        }
    }
}
