package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * EquipmenbatteryVoltage Logic
 * 
 * @author system
 */
public interface EquipmenbatteryVoltageLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltagePo> queryEquipmenbatteryVoltageListByPageCond(QueryBean queryBean)
            throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltagePo> queryEquipmenbatteryVoltageListByCond(
            EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryVoltagePo> queryEquipmenbatteryVoltageById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenbatteryVoltageByPo(EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryVoltage(EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryVoltageById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryVoltage(EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException;
}