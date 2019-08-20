package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmenlimitEdt;
import com.sea.dao.EquipmenlimitSer;
import com.sea.framework.constants.Constants;
import com.sea.framework.exception.LogicException;
import com.sea.framework.logic.BaseLogic;
import com.sea.framework.message.MessageManager;
import com.sea.framework.query.QueryBean;
import com.sea.framework.result.ResultBean;
import com.sea.framework.result.ResultPOBean;
import com.sea.framework.result.ResultPOListBean;
import com.sea.framework.util.BeanUtil;
import com.sea.framework.util.MessageCode;
import com.sea.model.EquipmenlimitEntity;
import com.sea.model.EquipmenlimitPo;

/**
 * Equipmenlimit Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenlimitLogicImpl extends BaseLogic implements EquipmenlimitLogic {

    @Autowired
    private EquipmenlimitSer equipmenlimitSer;

    @Autowired
    private EquipmenlimitEdt equipmenlimitEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitPo> queryEquipmenlimitListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmenlimitPo> resultPOListBean = new ResultPOListBean<EquipmenlimitPo>();
        List<EquipmenlimitPo> equipmenlimitPos = new ArrayList<EquipmenlimitPo>();
        // page query
        int count = equipmenlimitSer.selectEquipmenlimitListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenlimitEntity> equipmenlimits = equipmenlimitSer.selectEquipmenlimitListByPageCond(queryBean);
            EquipmenlimitPo equipmenlimitPo = null;
            for (EquipmenlimitEntity equipmenlimit : equipmenlimits) {
                equipmenlimitPo = new EquipmenlimitPo();
                BeanUtil.copyProperties(equipmenlimit, equipmenlimitPo);
                equipmenlimitPos.add(equipmenlimitPo);
            }
        }
        resultPOListBean.success(equipmenlimitPos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenlimitPo> queryEquipmenlimitListByCond(EquipmenlimitPo equipmenlimitPo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenlimitPo> result = new ResultPOListBean<EquipmenlimitPo>();
        // po list
        List<EquipmenlimitPo> equipmenlimitPos = new ArrayList<EquipmenlimitPo>();
        // excute query
        List<EquipmenlimitEntity> equipmenlimits = equipmenlimitSer.selectEquipmenlimitListByCond(equipmenlimitPo);
        EquipmenlimitPo resultPo = null;
        // poList
        for (EquipmenlimitEntity equipmenlimit : equipmenlimits) {
            resultPo = new EquipmenlimitPo();
            BeanUtil.copyProperties(equipmenlimit, resultPo);
            equipmenlimitPos.add(resultPo);
        }
        // return success
        result.success(equipmenlimitPos, equipmenlimitPos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenlimitPo> queryEquipmenlimitById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenlimitPo> result = new ResultPOBean<EquipmenlimitPo>();
        EquipmenlimitPo po = null;
        EquipmenlimitPo queryPo = new EquipmenlimitPo();
        queryPo.setId(id);
        EquipmenlimitEntity entity = equipmenlimitSer.selectEquipmenlimitByCond(queryPo);
        if (null != entity) {
            po = new EquipmenlimitPo();
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
    public ResultBean modifyEquipmenlimitByPo(EquipmenlimitPo equipmenlimitPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenlimitEntity equipmenlimit = new EquipmenlimitEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenlimitPo, equipmenlimit);
        this.equipmenlimitEdt.updateEquipmenlimitByPrimaryKey(equipmenlimit);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenlimit(EquipmenlimitPo equipmenlimitPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenlimitEntity equipmenlimit = new EquipmenlimitEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenlimitPo, equipmenlimit);
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
    public ResultBean removeEquipmenlimit(EquipmenlimitPo equipmenlimitPo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenlimitEntity equipmenlimit = new EquipmenlimitEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenlimitPo, equipmenlimit);
        // excute delete
        this.equipmenlimitEdt.deleteEquipmenlimit(equipmenlimit);
        result.success();
        return result;
    }
}