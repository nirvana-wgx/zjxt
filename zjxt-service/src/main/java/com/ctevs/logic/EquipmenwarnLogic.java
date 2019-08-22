package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenwarnVo;

/**
 * Equipmenwarn Logic
 * 
 * @author system
 */
public interface EquipmenwarnLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnVo> queryEquipmenwarnListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnVo> queryEquipmenwarnListByCond(EquipmenwarnVo EquipmenwarnVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenwarnVo> queryEquipmenwarnById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenwarnByPo(EquipmenwarnVo EquipmenwarnVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenwarn(EquipmenwarnVo EquipmenwarnVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenwarnById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenwarn(EquipmenwarnVo EquipmenwarnVo) throws LogicException;
}