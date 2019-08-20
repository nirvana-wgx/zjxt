package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmenbatteryVoltageEdt;
import com.sea.dao.EquipmenbatteryVoltageSer;
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
import com.sea.model.EquipmenbatteryVoltageEntity;
import com.sea.model.EquipmenbatteryVoltagePo;

/**
 * EquipmenbatteryVoltage Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenbatteryVoltageLogicImpl extends BaseLogic implements EquipmenbatteryVoltageLogic {

    @Autowired
    private EquipmenbatteryVoltageSer equipmenbatteryVoltageSer;

    @Autowired
    private EquipmenbatteryVoltageEdt equipmenbatteryVoltageEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltagePo> queryEquipmenbatteryVoltageListByPageCond(QueryBean queryBean)
            throws LogicException {
        ResultPOListBean<EquipmenbatteryVoltagePo> resultPOListBean = new ResultPOListBean<EquipmenbatteryVoltagePo>();
        List<EquipmenbatteryVoltagePo> equipmenbatteryVoltagePos = new ArrayList<EquipmenbatteryVoltagePo>();
        // page query
        int count = equipmenbatteryVoltageSer.selectEquipmenbatteryVoltageListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenbatteryVoltageEntity> equipmenbatteryVoltages = equipmenbatteryVoltageSer
                    .selectEquipmenbatteryVoltageListByPageCond(queryBean);
            EquipmenbatteryVoltagePo equipmenbatteryVoltagePo = null;
            for (EquipmenbatteryVoltageEntity equipmenbatteryVoltage : equipmenbatteryVoltages) {
                equipmenbatteryVoltagePo = new EquipmenbatteryVoltagePo();
                BeanUtil.copyProperties(equipmenbatteryVoltage, equipmenbatteryVoltagePo);
                equipmenbatteryVoltagePos.add(equipmenbatteryVoltagePo);
            }
        }
        resultPOListBean.success(equipmenbatteryVoltagePos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryVoltagePo> queryEquipmenbatteryVoltageListByCond(
            EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenbatteryVoltagePo> result = new ResultPOListBean<EquipmenbatteryVoltagePo>();
        // po list
        List<EquipmenbatteryVoltagePo> equipmenbatteryVoltagePos = new ArrayList<EquipmenbatteryVoltagePo>();
        // excute query
        List<EquipmenbatteryVoltageEntity> equipmenbatteryVoltages = equipmenbatteryVoltageSer
                .selectEquipmenbatteryVoltageListByCond(equipmenbatteryVoltagePo);
        EquipmenbatteryVoltagePo resultPo = null;
        // poList
        for (EquipmenbatteryVoltageEntity equipmenbatteryVoltage : equipmenbatteryVoltages) {
            resultPo = new EquipmenbatteryVoltagePo();
            BeanUtil.copyProperties(equipmenbatteryVoltage, resultPo);
            equipmenbatteryVoltagePos.add(resultPo);
        }
        // return success
        result.success(equipmenbatteryVoltagePos, equipmenbatteryVoltagePos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryVoltagePo> queryEquipmenbatteryVoltageById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenbatteryVoltagePo> result = new ResultPOBean<EquipmenbatteryVoltagePo>();
        EquipmenbatteryVoltagePo po = null;
        EquipmenbatteryVoltagePo queryPo = new EquipmenbatteryVoltagePo();
        queryPo.setId(id);
        EquipmenbatteryVoltageEntity entity = equipmenbatteryVoltageSer.selectEquipmenbatteryVoltageByCond(queryPo);
        if (null != entity) {
            po = new EquipmenbatteryVoltagePo();
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
    public ResultBean modifyEquipmenbatteryVoltageByPo(EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryVoltageEntity equipmenbatteryVoltage = new EquipmenbatteryVoltageEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenbatteryVoltagePo, equipmenbatteryVoltage);
        this.equipmenbatteryVoltageEdt.updateEquipmenbatteryVoltageByPrimaryKey(equipmenbatteryVoltage);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryVoltage(EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryVoltageEntity equipmenbatteryVoltage = new EquipmenbatteryVoltageEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenbatteryVoltagePo, equipmenbatteryVoltage);
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
    public ResultBean removeEquipmenbatteryVoltage(EquipmenbatteryVoltagePo equipmenbatteryVoltagePo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenbatteryVoltageEntity equipmenbatteryVoltage = new EquipmenbatteryVoltageEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenbatteryVoltagePo, equipmenbatteryVoltage);
        // excute delete
        this.equipmenbatteryVoltageEdt.deleteEquipmenbatteryVoltage(equipmenbatteryVoltage);
        result.success();
        return result;
    }
}