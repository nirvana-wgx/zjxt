package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * Equipmenstatus Logic
 * 
 * @author system
 */
public interface EquipmenstatusLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusPo> queryEquipmenstatusListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusPo> queryEquipmenstatusListByCond(EquipmenstatusPo equipmenstatusPo)
            throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenstatusPo> queryEquipmenstatusById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenstatusByPo(EquipmenstatusPo equipmenstatusPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenstatus(EquipmenstatusPo equipmenstatusPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenstatusById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenstatus(EquipmenstatusPo equipmenstatusPo) throws LogicException;
}