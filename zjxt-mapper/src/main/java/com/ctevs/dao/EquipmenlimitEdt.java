package com.ctevs.dao;

import java.math.BigInteger;

import com.ctevs.po.EquipmenlimitEntity;

/**
 * Equipmenlimit Edit Dao
 * 
 * @author system
 */
public interface EquipmenlimitEdt {
    /**
     * add record
     * 
     * @author system
     * @param equipmenlimit equipmenlimit
     * @return int
     */
    public int insertEquipmenlimit(EquipmenlimitEntity equipmenlimit);

    /**
     * update record by Primary Key
     * 
     * @author system
     * @param equipmenlimit equipmenlimit
     * @return int
     */
    public int updateEquipmenlimitByPrimaryKey(EquipmenlimitEntity equipmenlimit);

    /**
     * delete record
     * 
     * @author system
     * @param equipmenlimit equipmenlimit
     * @return int
     */
    public int deleteEquipmenlimit(EquipmenlimitEntity equipmenlimit);

    /**
     * delete record by Primary Key
     * 
     * @author system
     * @param id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}