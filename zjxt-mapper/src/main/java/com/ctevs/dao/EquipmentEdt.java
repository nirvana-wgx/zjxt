package com.ctevs.dao;

import java.math.BigInteger;

import com.ctevs.po.EquipmentEntity;

/**
 * Equipment Edit Dao
 * 
 * @author system
 */
public interface EquipmentEdt {
    /**
     * add record
     * 
     * @author system
     * @param equipment equipment
     * @return int
     */
    public int insertEquipment(EquipmentEntity equipment);

    /**
     * update record by Primary Key
     * 
     * @author system
     * @param equipment equipment
     * @return int
     */
    public int updateEquipmentByPrimaryKey(EquipmentEntity equipment);

    /**
     * delete record
     * 
     * @author system
     * @param equipment equipment
     * @return int
     */
    public int deleteEquipment(EquipmentEntity equipment);

    /**
     * delete record by Primary Key
     * 
     * @author system
     * @param id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}