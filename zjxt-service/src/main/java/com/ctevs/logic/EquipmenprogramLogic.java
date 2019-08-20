package com.ctevs.logic;
import java.math.BigInteger;

import com.ctevs.common.exception.LogicException;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;

/**
 * Equipmenprogram Logic
 * @author system
 */
public interface EquipmenprogramLogic {
     /**
     * @throws LogicException 
     */
    public ResultPOListBean<EquipmenprogramPo> queryEquipmenprogramListByPageCond(QueryBean queryBean) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenprogramPo> queryEquipmenprogramListByCond(EquipmenprogramPo equipmenprogramPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenprogramPo> queryEquipmenprogramById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenprogramByPo(EquipmenprogramPo equipmenprogramPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenprogram(EquipmenprogramPo equipmenprogramPo) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenprogramById(BigInteger id) throws LogicException;

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenprogram(EquipmenprogramPo equipmenprogramPo) throws LogicException;
}