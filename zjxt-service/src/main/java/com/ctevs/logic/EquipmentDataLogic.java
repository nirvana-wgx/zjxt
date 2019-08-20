package com.ctevs.logic;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * Equipment Logic
 * 
 * @author system
 */
public interface EquipmentDataLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentDataPo> queryEquipmentDataListByPageCond(QueryBean queryBean) throws LogicException;
    
    public ResultPOListBean<EquipmentDataPo> queryEquipmentDataNewListByPageCond(QueryBean queryBean) throws LogicException;

    public ResultPOListBean<EquipmenbatteryPo> selectEquipmentBatteryDataListByCond(EquipmenbatteryPo equipmenbatteryPo) throws LogicException;

    public  boolean createShardTable() throws LogicException;
}