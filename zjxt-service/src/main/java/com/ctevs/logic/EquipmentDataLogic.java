package com.ctevs.logic;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenbatteryVo;
import com.ctevs.vo.EquipmentDataVo;

/**
 * Equipment Logic
 * 
 * @author system
 */
public interface EquipmentDataLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentDataVo> queryEquipmentDataListByPageCond(QueryBean queryBean) throws LogicException;
    
    public ResultPOListBean<EquipmentDataVo> queryEquipmentDataNewListByPageCond(QueryBean queryBean) throws LogicException;

    public ResultPOListBean<EquipmenbatteryVo> selectEquipmentBatteryDataListByCond(EquipmenbatteryVo equipmenbatteryPo) throws LogicException;

    public  boolean createShardTable() throws LogicException;
}