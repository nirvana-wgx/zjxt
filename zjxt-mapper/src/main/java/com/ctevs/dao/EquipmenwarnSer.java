package com.ctevs.dao;

import java.util.List;

import com.ctevs.page.QueryBean;
import com.ctevs.po.EquipmenwarnEntity;
import com.ctevs.vo.EquipmenwarnVo;

/**
 * Equipmenwarn Query Dao
 * 
 * @author system
 */
public interface EquipmenwarnSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmenwarnEntity>
     */
    public List<EquipmenwarnEntity> selectEquipmenwarnListByPageCond(QueryBean queryBean);

    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmenwarnListTotalCount(QueryBean queryBean);

    /**
     * query list without page
     * 
     * @author system
     * @param equipmenwarn equipmenwarn
     * @return List<EquipmenwarnEntity>
     */
    public List<EquipmenwarnEntity> selectEquipmenwarnListByCond(EquipmenwarnVo equipmenwarn);

    /**
     * query record
     * 
     * @author system
     * @param equipmenwarn equipmenwarn
     * @return EquipmenwarnEntity
     */
    public EquipmenwarnEntity selectEquipmenwarnByCond(EquipmenwarnVo equipmenwarn);
}