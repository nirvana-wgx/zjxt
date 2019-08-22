package com.ctevs.logic;
import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.vo.EquipmenprogramVo;

/**
 * Equipmenprogram Logic
 * @author system
 */
public interface EquipmenprogramLogic {
     /**
     * @throws LogicException 
     */
    public ResultPOListBean<EquipmenprogramVo> queryEquipmenprogramListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenprogramVo> queryEquipmenprogramListByCond(EquipmenprogramVo EquipmenprogramVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenprogramVo> queryEquipmenprogramById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenprogramByPo(EquipmenprogramVo EquipmenprogramVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenprogram(EquipmenprogramVo EquipmenprogramVo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenprogramById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenprogram(EquipmenprogramVo EquipmenprogramVo) throws LogicException;
}