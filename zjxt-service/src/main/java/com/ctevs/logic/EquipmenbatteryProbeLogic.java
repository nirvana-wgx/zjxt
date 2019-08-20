package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenbatteryProbeVo;

/**
 * EquipmenbatteryProbe Logic
 * 
 * @author system
 */
public interface EquipmenbatteryProbeLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryProbeVo> queryEquipmenbatteryProbeListByPageCond(QueryBean queryBean)
            throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryProbeVo> queryEquipmenbatteryProbeListByCond(
            EquipmenbatteryProbeVo equipmenbatteryProbePo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryProbeVo> queryEquipmenbatteryProbeById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenbatteryProbeByPo(EquipmenbatteryProbeVo equipmenbatteryProbePo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryProbe(EquipmenbatteryProbeVo equipmenbatteryProbePo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryProbeById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryProbe(EquipmenbatteryProbeVo equipmenbatteryProbePo) throws LogicException;
}