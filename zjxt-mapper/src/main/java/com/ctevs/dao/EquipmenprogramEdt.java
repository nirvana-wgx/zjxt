package com.ctevs.dao;
import java.math.BigInteger;

import com.ctevs.po.EquipmenprogramEntity;

/**
 * Equipmenprogram Edit Dao
 * @author system
 */
public interface EquipmenprogramEdt {
	/**
     * add record
     * @author system
     * @param equipmenprogram equipmenprogram
     * @return int
     */
    public int insertEquipmenprogram(EquipmenprogramEntity equipmenprogram);
    
    /**
     * update record by Primary Key
     * @author system
     * @param  equipmenprogram equipmenprogram
     * @return int
     */
    public int updateEquipmenprogramByPrimaryKey(EquipmenprogramEntity equipmenprogram);
    
    /**
     * delete record
     * @author system
     * @param equipmenprogram equipmenprogram
     * @return int
     */
    public int deleteEquipmenprogram(EquipmenprogramEntity equipmenprogram);
	
	/**
     * delete record by Primary Key
     * @author system
     * @param  id id
     * @return int
     */
    public int deleteByPrimaryKey(BigInteger id);
}