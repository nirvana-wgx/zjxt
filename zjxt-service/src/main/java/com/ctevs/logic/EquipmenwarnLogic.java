package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * Equipmenwarn Logic
 * 
 * @author system
 */
public interface EquipmenwarnLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnPo> queryEquipmenwarnListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnPo> queryEquipmenwarnListByCond(EquipmenwarnPo equipmenwarnPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenwarnPo> queryEquipmenwarnById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenwarnByPo(EquipmenwarnPo equipmenwarnPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenwarn(EquipmenwarnPo equipmenwarnPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenwarnById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenwarn(EquipmenwarnPo equipmenwarnPo) throws LogicException;
}