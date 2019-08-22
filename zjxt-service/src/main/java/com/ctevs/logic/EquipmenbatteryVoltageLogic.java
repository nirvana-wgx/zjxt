package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenbatteryVoltageVo;

/**
 * EquipmenbatteryVoltage Logic
 * 
 * @author system
 */
public interface EquipmenbatteryVoltageLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltageVo> queryEquipmenbatteryVoltageListByPageCond(QueryBean queryBean)
            throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltageVo> queryEquipmenbatteryVoltageListByCond(
            EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryVoltageVo> queryEquipmenbatteryVoltageById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenbatteryVoltageByPo(EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryVoltage(EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryVoltageById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryVoltage(EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException;
}