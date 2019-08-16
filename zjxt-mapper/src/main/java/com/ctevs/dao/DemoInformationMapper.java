package com.ctevs.dao;

import com.ctevs.entity.DemoInformation;

public interface DemoInformationMapper {
    int deleteByPrimaryKey(String userId);

    int insert(DemoInformation record);

    int insertSelective(DemoInformation record);

    DemoInformation selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(DemoInformation record);

    int updateByPrimaryKey(DemoInformation record);
}