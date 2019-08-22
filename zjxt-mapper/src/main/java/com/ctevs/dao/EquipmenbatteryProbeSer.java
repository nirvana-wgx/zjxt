package com.ctevs.dao;

import java.util.List;

import com.ctevs.common.query.QueryBean;
import com.ctevs.po.EquipmenbatteryProbeEntity;
import com.ctevs.vo.EquipmenbatteryProbeVo;

/**
 * EquipmenbatteryProbe Query Dao
 * 
 * @author system
 */
public interface EquipmenbatteryProbeSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmenbatteryProbeEntity>
     */
    public List<EquipmenbatteryProbeEntity> selectEquipmenbatteryProbeListByPageCond(QueryBean queryBean);

    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmenbatteryProbeListTotalCount(QueryBean queryBean);

    /**
     * query list without page
     * 
     * @author system
     * @param equipmenbatteryProbe equipmenbatteryProbe
     * @return List<EquipmenbatteryProbeEntity>
     */
    public List<EquipmenbatteryProbeEntity> selectEquipmenbatteryProbeListByCond(EquipmenbatteryProbeVo equipmenbatteryProbe);

    /**
     * query record
     * 
     * @author system
     * @param equipmenbatteryProbe equipmenbatteryProbe
     * @return EquipmenbatteryProbeEntity
     */
    public EquipmenbatteryProbeEntity selectEquipmenbatteryProbeByCond(EquipmenbatteryProbeVo equipmenbatteryProbe);
}