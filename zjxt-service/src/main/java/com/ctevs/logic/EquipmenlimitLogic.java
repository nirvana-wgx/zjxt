package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenlimitVo;

/**
 * Equipmenlimit Logic
 * 
 * @author system
 */
public interface EquipmenlimitLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitVo> queryEquipmenlimitListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitVo> queryEquipmenlimitListByCond(EquipmenlimitVo EquipmenlimitVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenlimitVo> queryEquipmenlimitById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenlimitByPo(EquipmenlimitVo EquipmenlimitVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenlimit(EquipmenlimitVo EquipmenlimitVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenlimitById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenlimit(EquipmenlimitVo EquipmenlimitVo) throws LogicException;
}