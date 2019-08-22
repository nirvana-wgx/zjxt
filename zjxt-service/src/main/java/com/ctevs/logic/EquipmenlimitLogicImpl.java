package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctevs.common.BeanUtil;
import com.ctevs.common.Constants;
import com.ctevs.common.exception.LogicException;
import com.ctevs.common.message.MessageManager;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultBean;
import com.ctevs.common.result.ResultPOBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.dao.EquipmenlimitEdt;
import com.ctevs.dao.EquipmenlimitSer;
import com.ctevs.po.EquipmenlimitEntity;
import com.ctevs.vo.EquipmenlimitVo;

/**
 * Equipmenlimit Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenlimitLogicImpl   implements EquipmenlimitLogic {

    @Autowired
    private EquipmenlimitSer equipmenlimitSer;

    @Autowired
    private EquipmenlimitEdt equipmenlimitEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitVo> queryEquipmenlimitListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmenlimitVo> resultPOListBean = new ResultPOListBean<EquipmenlimitVo>();
        List<EquipmenlimitVo> EquipmenlimitVos = new ArrayList<EquipmenlimitVo>();
        // page query
        int count = equipmenlimitSer.selectEquipmenlimitListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenlimitEntity> equipmenlimits = equipmenlimitSer.selectEquipmenlimitListByPageCond(queryBean);
            EquipmenlimitVo EquipmenlimitVo = null;
            for (EquipmenlimitEntity equipmenlimit : equipmenlimits) {
                EquipmenlimitVo = new EquipmenlimitVo();
                BeanUtil.copyProperties(equipmenlimit, EquipmenlimitVo);
                EquipmenlimitVos.add(EquipmenlimitVo);
            }
        }
        resultPOListBean.success(EquipmenlimitVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitVo> queryEquipmenlimitListByCond(EquipmenlimitVo EquipmenlimitVo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenlimitVo> result = new ResultPOListBean<EquipmenlimitVo>();
        // po list
        List<EquipmenlimitVo> EquipmenlimitVos = new ArrayList<EquipmenlimitVo>();
        // excute query
        List<EquipmenlimitEntity> equipmenlimits = equipmenlimitSer.selectEquipmenlimitListByCond(EquipmenlimitVo);
        EquipmenlimitVo resultPo = null;
        // poList
        for (EquipmenlimitEntity equipmenlimit : equipmenlimits) {
            resultPo = new EquipmenlimitVo();
            BeanUtil.copyProperties(equipmenlimit, resultPo);
            EquipmenlimitVos.add(resultPo);
        }
        // return success
        result.success(EquipmenlimitVos, EquipmenlimitVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenlimitVo> queryEquipmenlimitById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenlimitVo> result = new ResultPOBean<EquipmenlimitVo>();
        EquipmenlimitVo po = null;
        EquipmenlimitVo queryPo = new EquipmenlimitVo();
        queryPo.setId(id);
        EquipmenlimitEntity entity = equipmenlimitSer.selectEquipmenlimitByCond(queryPo);
        if (null != entity) {
            po = new EquipmenlimitVo();
            BeanUtil.copyProperties(entity, po);
            // return success
            result.setValue(po);
        }
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean modifyEquipmenlimitByPo(EquipmenlimitVo EquipmenlimitVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenlimitEntity equipmenlimit = new EquipmenlimitEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenlimitVo, equipmenlimit);
        this.equipmenlimitEdt.updateEquipmenlimitByPrimaryKey(equipmenlimit);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenlimit(EquipmenlimitVo EquipmenlimitVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenlimitEntity equipmenlimit = new EquipmenlimitEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenlimitVo, equipmenlimit);
        int status = this.equipmenlimitEdt.insertEquipmenlimit(equipmenlimit);
        if (status != Constants.ZERO) {
            resultBean.success();
        } else {
            // error
            resultBean.failure("");
        }
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenlimitById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmenlimitEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenlimit(EquipmenlimitVo EquipmenlimitVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenlimitEntity equipmenlimit = new EquipmenlimitEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenlimitVo, equipmenlimit);
        // excute delete
        this.equipmenlimitEdt.deleteEquipmenlimit(equipmenlimit);
        result.success();
        return result;
    }
}