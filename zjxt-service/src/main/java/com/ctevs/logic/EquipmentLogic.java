package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmentVo;

/**
 * Equipment Logic
 * 
 * @author system
 */
public interface EquipmentLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentVo> queryEquipmentListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentVo> queryEquipmentListByCond(EquipmentVo EquipmentVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmentVo> queryEquipmentById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmentByPo(EquipmentVo EquipmentVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipment(EquipmentVo EquipmentVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmentById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipment(EquipmentVo EquipmentVo) throws LogicException;
}