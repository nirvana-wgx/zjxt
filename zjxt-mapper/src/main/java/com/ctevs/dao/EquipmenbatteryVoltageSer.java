package com.ctevs.dao;

import java.util.List;

import com.ctevs.common.query.QueryBean;
import com.ctevs.po.EquipmenbatteryVoltageEntity;
import com.ctevs.vo.EquipmenbatteryVoltageVo;

/**
 * EquipmenbatteryVoltage Query Dao
 * 
 * @author system
 */
public interface EquipmenbatteryVoltageSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmenbatteryVoltageEntity>
     */
    public List<EquipmenbatteryVoltageEntity> selectEquipmenbatteryVoltageListByPageCond(QueryBean queryBean);

    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmenbatteryVoltageListTotalCount(QueryBean queryBean);

    /**
     * query list without page
     * 
     * @author system
     * @param equipmenbatteryVoltage equipmenbatteryVoltage
     * @return List<EquipmenbatteryVoltageEntity>
     */
    public List<EquipmenbatteryVoltageEntity> selectEquipmenbatteryVoltageListByCond(
            EquipmenbatteryVoltageVo equipmenbatteryVoltage);

    /**
     * query record
     * 
     * @author system
     * @param equipmenbatteryVoltage equipmenbatteryVoltage
     * @return EquipmenbatteryVoltageEntity
     */
    public EquipmenbatteryVoltageEntity selectEquipmenbatteryVoltageByCond(EquipmenbatteryVoltageVo equipmenbatteryVoltage);
}