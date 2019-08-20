package com.ctevs.dao;

import java.util.List;

import com.ctevs.page.QueryBean;
import com.ctevs.po.EquipmenstatusEntity;
import com.ctevs.vo.EquipmenstatusVo;

/**
 * Equipmenstatus Query Dao
 * 
 * @author system
 */
public interface EquipmenstatusSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmenstatusEntity>
     */
    public List<EquipmenstatusEntity> selectEquipmenstatusListByPageCond(QueryBean queryBean);

    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmenstatusListTotalCount(QueryBean queryBean);

    /**
     * query list without page
     * 
     * @author system
     * @param equipmenstatus equipmenstatus
     * @return List<EquipmenstatusEntity>
     */
    public List<EquipmenstatusEntity> selectEquipmenstatusListByCond(EquipmenstatusVo equipmenstatus);

    /**
     * query record
     * 
     * @author system
     * @param equipmenstatus equipmenstatus
     * @return EquipmenstatusEntity
     */
    public EquipmenstatusEntity selectEquipmenstatusByCond(EquipmenstatusVo equipmenstatus);
}