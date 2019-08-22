package com.ctevs.logic;

import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenstatusVo;

/**
 * Equipmenstatus Logic
 * 
 * @author system
 */
public interface EquipmenstatusLogic {
    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusVo> queryEquipmenstatusListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusVo> queryEquipmenstatusListByCond(EquipmenstatusVo EquipmenstatusVo)
            throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenstatusVo> queryEquipmenstatusById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenstatusByPo(EquipmenstatusVo EquipmenstatusVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenstatus(EquipmenstatusVo EquipmenstatusVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenstatusById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenstatus(EquipmenstatusVo EquipmenstatusVo) throws LogicException;
}