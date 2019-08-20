package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmentEdt;
import com.sea.dao.EquipmentSer;
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
import com.sea.model.EquipmentEntity;
import com.sea.model.EquipmentPo;

/**
 * Equipment Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmentLogicImpl extends BaseLogic implements EquipmentLogic {

    @Autowired
    private EquipmentSer equipmentSer;

    @Autowired
    private EquipmentEdt equipmentEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentPo> queryEquipmentListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmentPo> resultPOListBean = new ResultPOListBean<EquipmentPo>();
        List<EquipmentPo> equipmentPos = new ArrayList<EquipmentPo>();
        // page query
        int count = equipmentSer.selectEquipmentListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmentEntity> equipments = equipmentSer.selectEquipmentListByPageCond(queryBean);
            EquipmentPo equipmentPo = null;
            for (EquipmentEntity equipment : equipments) {
                equipmentPo = new EquipmentPo();
                BeanUtil.copyProperties(equipment, equipmentPo);
                equipmentPos.add(equipmentPo);
            }
        }
        resultPOListBean.success(equipmentPos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentPo> queryEquipmentListByCond(EquipmentPo equipmentPo) throws LogicException {
        // return object
        ResultPOListBean<EquipmentPo> result = new ResultPOListBean<EquipmentPo>();
        // po list
        List<EquipmentPo> equipmentPos = new ArrayList<EquipmentPo>();
        // excute query
        List<EquipmentEntity> equipments = equipmentSer.selectEquipmentListByCond(equipmentPo);
        EquipmentPo resultPo = null;
        // poList
        for (EquipmentEntity equipment : equipments) {
            resultPo = new EquipmentPo();
            BeanUtil.copyProperties(equipment, resultPo);
            equipmentPos.add(resultPo);
        }
        // return success
        result.success(equipmentPos, equipmentPos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmentPo> queryEquipmentById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmentPo> result = new ResultPOBean<EquipmentPo>();
        EquipmentPo po = null;
        EquipmentPo queryPo = new EquipmentPo();
        queryPo.setId(id);
        EquipmentEntity entity = equipmentSer.selectEquipmentByCond(queryPo);
        if (null != entity) {
            po = new EquipmentPo();
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
    public ResultBean modifyEquipmentByPo(EquipmentPo equipmentPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmentEntity equipment = new EquipmentEntity();
        // po->entity
        BeanUtil.copyProperties(equipmentPo, equipment);
        this.equipmentEdt.updateEquipmentByPrimaryKey(equipment);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipment(EquipmentPo equipmentPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmentEntity equipment = new EquipmentEntity();
        // po->entity
        BeanUtil.copyProperties(equipmentPo, equipment);
        int status = this.equipmentEdt.insertEquipment(equipment);
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
    public ResultBean removeEquipmentById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmentEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipment(EquipmentPo equipmentPo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmentEntity equipment = new EquipmentEntity();
        // po->entity
        BeanUtil.copyProperties(equipmentPo, equipment);
        // excute delete
        this.equipmentEdt.deleteEquipment(equipment);
        result.success();
        return result;
    }
}