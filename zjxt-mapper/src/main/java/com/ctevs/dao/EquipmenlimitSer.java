package com.ctevs.dao;

import java.util.List;

import com.ctevs.common.query.QueryBean;
import com.ctevs.po.EquipmenlimitEntity;
import com.ctevs.vo.EquipmenlimitVo;

/**
 * Equipmenlimit Query Dao
 * 
 * @author system
 */
public interface EquipmenlimitSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmenlimitEntity>
     */
    public List<EquipmenlimitEntity> selectEquipmenlimitListByPageCond(QueryBean queryBean);

    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmenlimitListTotalCount(QueryBean queryBean);

    /**
     * query list without page
     * 
     * @author system
     * @param equipmenlimit equipmenlimit
     * @return List<EquipmenlimitEntity>
     */
    public List<EquipmenlimitEntity> selectEquipmenlimitListByCond(EquipmenlimitVo equipmenlimit);

    /**
     * query record
     * 
     * @author system
     * @param equipmenlimit equipmenlimit
     * @return EquipmenlimitEntity
     */
    public EquipmenlimitEntity selectEquipmenlimitByCond(EquipmenlimitVo equipmenlimit);
}