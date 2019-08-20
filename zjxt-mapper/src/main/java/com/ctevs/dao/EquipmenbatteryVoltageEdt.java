package com.ctevs.dao;

import java.math.BigInteger;

import com.ctevs.po.EquipmenbatteryVoltageEntity;

/**
 * EquipmenbatteryVoltage Edit Dao
 * 
 * @author system
 */
public interface EquipmenbatteryVoltageEdt {
    /**
     * add record
     * 
     * @author system
     * @param equipmenbatteryVoltage equipmenbatteryVoltage
     * @return int
     */
    public int insertEquipmenbatteryVoltage(EquipmenbatteryVoltageEntity equipmenbatteryVoltage);

    /**
     * update record by Primary Key
     * 
     * @author system
     * @param equipmenbatteryVoltage equipmenbatteryVoltage
     * @return int
     */
    public int updateEquipmenbatteryVoltageByPrimaryKey(EquipmenbatteryVoltageEntity equipmenbatteryVoltage);

    /**
     * delete record
     * 
     * @author system
     * @param equipmenbatteryVoltage equipmenbatteryVoltage
     * @return int
     */
    public int deleteEquipmenbatteryVoltage(EquipmenbatteryVoltageEntity equipmenbatteryVoltage);

    /**
     * delete record by Primary Key
     * 
     * @author system
     * @param id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}