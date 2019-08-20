package com.ctevs.dao;

import java.util.List;

import com.ctevs.page.QueryBean;
import com.ctevs.po.EquipmentEntity;
import com.ctevs.vo.EquipmentVo;

/**
 * Equipment Query Dao
 * 
 * @author system
 */
public interface EquipmentSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmentEntity>
     */
    public List<EquipmentEntity> selectEquipmentListByPageCond(QueryBean queryBean);

    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmentListTotalCount(QueryBean queryBean);

    /**
     * query list without page
     * 
     * @author system
     * @param equipment equipment
     * @return List<EquipmentEntity>
     */
    public List<EquipmentEntity> selectEquipmentListByCond(EquipmentVo equipment);

    /**
     * query record
     * 
     * @author system
     * @param equipment equipment
     * @return EquipmentEntity
     */
    public EquipmentEntity selectEquipmentByCond(EquipmentVo equipment);
}