package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * Equipmenlimit Logic
 * 
 * @author system
 */
public interface EquipmenlimitLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitPo> queryEquipmenlimitListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitPo> queryEquipmenlimitListByCond(EquipmenlimitPo equipmenlimitPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenlimitPo> queryEquipmenlimitById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenlimitByPo(EquipmenlimitPo equipmenlimitPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenlimit(EquipmenlimitPo equipmenlimitPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenlimitById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenlimit(EquipmenlimitPo equipmenlimitPo) throws LogicException;
}