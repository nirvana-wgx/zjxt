package com.ctevs.dao;

import java.math.BigInteger;

import com.ctevs.po.EquipmenstatusEntity;

/**
 * Equipmenstatus Edit Dao
 * 
 * @author system
 */
public interface EquipmenstatusEdt {
    /**
     * add record
     * 
     * @author system
     * @param equipmenstatus equipmenstatus
     * @return int
     */
    public int insertEquipmenstatus(EquipmenstatusEntity equipmenstatus);

    /**
     * update record by Primary Key
     * 
     * @author system
     * @param equipmenstatus equipmenstatus
     * @return int
     */
    public int updateEquipmenstatusByPrimaryKey(EquipmenstatusEntity equipmenstatus);

    /**
     * delete record
     * 
     * @author system
     * @param equipmenstatus equipmenstatus
     * @return int
     */
    public int deleteEquipmenstatus(EquipmenstatusEntity equipmenstatus);

    /**
     * delete record by Primary Key
     * 
     * @author system
     * @param id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}