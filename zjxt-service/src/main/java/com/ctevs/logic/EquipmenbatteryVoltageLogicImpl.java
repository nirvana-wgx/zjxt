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
import com.ctevs.dao.EquipmenbatteryVoltageEdt;
import com.ctevs.dao.EquipmenbatteryVoltageSer;
import com.ctevs.po.EquipmenbatteryVoltageEntity;
import com.ctevs.vo.EquipmenbatteryVoltageVo;

/**
 * EquipmenbatteryVoltage Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenbatteryVoltageLogicImpl   implements EquipmenbatteryVoltageLogic {

    @Autowired
    private EquipmenbatteryVoltageSer equipmenbatteryVoltageSer;

    @Autowired
    private EquipmenbatteryVoltageEdt equipmenbatteryVoltageEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltageVo> queryEquipmenbatteryVoltageListByPageCond(QueryBean queryBean)
            throws LogicException {
        ResultPOListBean<EquipmenbatteryVoltageVo> resultPOListBean = new ResultPOListBean<EquipmenbatteryVoltageVo>();
        List<EquipmenbatteryVoltageVo> EquipmenbatteryVoltageVos = new ArrayList<EquipmenbatteryVoltageVo>();
        // page query
        int count = equipmenbatteryVoltageSer.selectEquipmenbatteryVoltageListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenbatteryVoltageEntity> equipmenbatteryVoltages = equipmenbatteryVoltageSer
                    .selectEquipmenbatteryVoltageListByPageCond(queryBean);
            EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo = null;
            for (EquipmenbatteryVoltageEntity equipmenbatteryVoltage : equipmenbatteryVoltages) {
                EquipmenbatteryVoltageVo = new EquipmenbatteryVoltageVo();
                BeanUtil.copyProperties(equipmenbatteryVoltage, EquipmenbatteryVoltageVo);
                EquipmenbatteryVoltageVos.add(EquipmenbatteryVoltageVo);
            }
        }
        resultPOListBean.success(EquipmenbatteryVoltageVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltageVo> queryEquipmenbatteryVoltageListByCond(
            EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenbatteryVoltageVo> result = new ResultPOListBean<EquipmenbatteryVoltageVo>();
        // po list
        List<EquipmenbatteryVoltageVo> EquipmenbatteryVoltageVos = new ArrayList<EquipmenbatteryVoltageVo>();
        // excute query
        List<EquipmenbatteryVoltageEntity> equipmenbatteryVoltages = equipmenbatteryVoltageSer
                .selectEquipmenbatteryVoltageListByCond(EquipmenbatteryVoltageVo);
        EquipmenbatteryVoltageVo resultPo = null;
        // poList
        for (EquipmenbatteryVoltageEntity equipmenbatteryVoltage : equipmenbatteryVoltages) {
            resultPo = new EquipmenbatteryVoltageVo();
            BeanUtil.copyProperties(equipmenbatteryVoltage, resultPo);
            EquipmenbatteryVoltageVos.add(resultPo);
        }
        // return success
        result.success(EquipmenbatteryVoltageVos, EquipmenbatteryVoltageVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryVoltageVo> queryEquipmenbatteryVoltageById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenbatteryVoltageVo> result = new ResultPOBean<EquipmenbatteryVoltageVo>();
        EquipmenbatteryVoltageVo po = null;
        EquipmenbatteryVoltageVo queryPo = new EquipmenbatteryVoltageVo();
        queryPo.setId(id);
        EquipmenbatteryVoltageEntity entity = equipmenbatteryVoltageSer.selectEquipmenbatteryVoltageByCond(queryPo);
        if (null != entity) {
            po = new EquipmenbatteryVoltageVo();
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
    public ResultBean modifyEquipmenbatteryVoltageByPo(EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryVoltageEntity equipmenbatteryVoltage = new EquipmenbatteryVoltageEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenbatteryVoltageVo, equipmenbatteryVoltage);
        this.equipmenbatteryVoltageEdt.updateEquipmenbatteryVoltageByPrimaryKey(equipmenbatteryVoltage);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryVoltage(EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryVoltageEntity equipmenbatteryVoltage = new EquipmenbatteryVoltageEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenbatteryVoltageVo, equipmenbatteryVoltage);
        int status = this.equipmenbatteryVoltageEdt.insertEquipmenbatteryVoltage(equipmenbatteryVoltage);
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
    public ResultBean removeEquipmenbatteryVoltageById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmenbatteryVoltageEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryVoltage(EquipmenbatteryVoltageVo EquipmenbatteryVoltageVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenbatteryVoltageEntity equipmenbatteryVoltage = new EquipmenbatteryVoltageEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenbatteryVoltageVo, equipmenbatteryVoltage);
        // excute delete
        this.equipmenbatteryVoltageEdt.deleteEquipmenbatteryVoltage(equipmenbatteryVoltage);
        result.success();
        return result;
    }
}