package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * Equipment Logic
 * 
 * @author system
 */
public interface EquipmentLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentPo> queryEquipmentListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentPo> queryEquipmentListByCond(EquipmentPo equipmentPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmentPo> queryEquipmentById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmentByPo(EquipmentPo equipmentPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipment(EquipmentPo equipmentPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmentById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipment(EquipmentPo equipmentPo) throws LogicException;
}