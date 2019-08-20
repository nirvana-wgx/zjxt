package com.ctevs.dao;

import java.math.BigInteger;

import com.ctevs.po.EquipmenwarnEntity;

/**
 * Equipmenwarn Edit Dao
 * 
 * @author system
 */
public interface EquipmenwarnEdt {
    /**
     * add record
     * 
     * @author system
     * @param equipmenwarn equipmenwarn
     * @return int
     */
    public int insertEquipmenwarn(EquipmenwarnEntity equipmenwarn);

    /**
     * update record by Primary Key
     * 
     * @author system
     * @param equipmenwarn equipmenwarn
     * @return int
     */
    public int updateEquipmenwarnByPrimaryKey(EquipmenwarnEntity equipmenwarn);

    /**
     * delete record
     * 
     * @author system
     * @param equipmenwarn equipmenwarn
     * @return int
     */
    public int deleteEquipmenwarn(EquipmenwarnEntity equipmenwarn);

    /**
     * delete record by Primary Key
     * 
     * @author system
     * @param id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}