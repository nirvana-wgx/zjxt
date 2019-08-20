package com.ctevs.dao;
import java.util.List;

import com.ctevs.page.QueryBean;
import com.ctevs.po.EquipmenprogramEntity;
import com.ctevs.vo.EquipmenprogramVo;

/**
 * Equipmenprogram Query Dao
 * @author system
 */
public interface EquipmenprogramSer {
	/**
     * query list with page
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmenprogramEntity>
     */
    public List<EquipmenprogramEntity> selectEquipmenprogramListByPageCond(QueryBean queryBean);
    
    /**
     * query list count
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmenprogramListTotalCount(QueryBean queryBean);
    
    /**
     * query list without page
     * @author system
     * @param equipmenprogram equipmenprogram 
     * @return List<EquipmenprogramEntity>
     */
    public List<EquipmenprogramEntity> selectEquipmenprogramListByCond(EquipmenprogramVo equipmenprogram);
    
    /**
     * query record
     * @author system
     * @param equipmenprogram equipmenprogram
     * @return EquipmenprogramEntity
     */
    public EquipmenprogramEntity selectEquipmenprogramByCond(EquipmenprogramVo equipmenprogram);
}