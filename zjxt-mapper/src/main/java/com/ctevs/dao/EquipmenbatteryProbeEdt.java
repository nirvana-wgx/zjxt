package com.ctevs.dao;

import java.math.BigInteger;

import com.ctevs.po.EquipmenbatteryProbeEntity;

/**
 * EquipmenbatteryProbe Edit Dao
 * 
 * @author system
 */
public interface EquipmenbatteryProbeEdt {
    /**
     * add record
     * 
     * @author system
     * @param equipmenbatteryProbe equipmenbatteryProbe
     * @return int
     */
    public int insertEquipmenbatteryProbe(EquipmenbatteryProbeEntity equipmenbatteryProbe);

    /**
     * update record by Primary Key
     * 
     * @author system
     * @param equipmenbatteryProbe equipmenbatteryProbe
     * @return int
     */
    public int updateEquipmenbatteryProbeByPrimaryKey(EquipmenbatteryProbeEntity equipmenbatteryProbe);

    /**
     * delete record
     * 
     * @author system
     * @param equipmenbatteryProbe equipmenbatteryProbe
     * @return int
     */
    public int deleteEquipmenbatteryProbe(EquipmenbatteryProbeEntity equipmenbatteryProbe);

    /**
     * delete record by Primary Key
     * 
     * @author system
     * @param id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}