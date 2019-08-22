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
import com.ctevs.dao.EquipmentEdt;
import com.ctevs.dao.EquipmentSer;
import com.ctevs.po.EquipmentEntity;
import com.ctevs.vo.EquipmentVo;

/**
 * Equipment Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmentLogicImpl   implements EquipmentLogic {

    @Autowired
    private EquipmentSer equipmentSer;

    @Autowired
    private EquipmentEdt equipmentEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentVo> queryEquipmentListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmentVo> resultPOListBean = new ResultPOListBean<EquipmentVo>();
        List<EquipmentVo> EquipmentVos = new ArrayList<EquipmentVo>();
        // page query
        int count = equipmentSer.selectEquipmentListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmentEntity> equipments = equipmentSer.selectEquipmentListByPageCond(queryBean);
            EquipmentVo EquipmentVo = null;
            for (EquipmentEntity equipment : equipments) {
                EquipmentVo = new EquipmentVo();
                BeanUtil.copyProperties(equipment, EquipmentVo);
                EquipmentVos.add(EquipmentVo);
            }
        }
        resultPOListBean.success(EquipmentVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentVo> queryEquipmentListByCond(EquipmentVo EquipmentVo) throws LogicException {
        // return object
        ResultPOListBean<EquipmentVo> result = new ResultPOListBean<EquipmentVo>();
        // po list
        List<EquipmentVo> EquipmentVos = new ArrayList<EquipmentVo>();
        // excute query
        List<EquipmentEntity> equipments = equipmentSer.selectEquipmentListByCond(EquipmentVo);
        EquipmentVo resultPo = null;
        // poList
        for (EquipmentEntity equipment : equipments) {
            resultPo = new EquipmentVo();
            BeanUtil.copyProperties(equipment, resultPo);
            EquipmentVos.add(resultPo);
        }
        // return success
        result.success(EquipmentVos, EquipmentVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmentVo> queryEquipmentById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmentVo> result = new ResultPOBean<EquipmentVo>();
        EquipmentVo po = null;
        EquipmentVo queryPo = new EquipmentVo();
        queryPo.setId(id);
        EquipmentEntity entity = equipmentSer.selectEquipmentByCond(queryPo);
        if (null != entity) {
            po = new EquipmentVo();
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
    public ResultBean modifyEquipmentByPo(EquipmentVo EquipmentVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmentEntity equipment = new EquipmentEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmentVo, equipment);
        this.equipmentEdt.updateEquipmentByPrimaryKey(equipment);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipment(EquipmentVo EquipmentVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmentEntity equipment = new EquipmentEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmentVo, equipment);
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
    public ResultBean removeEquipment(EquipmentVo EquipmentVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmentEntity equipment = new EquipmentEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmentVo, equipment);
        // excute delete
        this.equipmentEdt.deleteEquipment(equipment);
        result.success();
        return result;
    }
}