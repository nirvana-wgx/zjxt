package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmenbatteryProbeEdt;
import com.sea.dao.EquipmenbatteryProbeSer;
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
import com.sea.model.EquipmenbatteryProbeEntity;
import com.sea.model.EquipmenbatteryProbePo;

/**
 * EquipmenbatteryProbe Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenbatteryProbeLogicImpl   implements EquipmenbatteryProbeLogic {

    @Autowired
    private EquipmenbatteryProbeSer equipmenbatteryProbeSer;

    @Autowired
    private EquipmenbatteryProbeEdt equipmenbatteryProbeEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryProbePo> queryEquipmenbatteryProbeListByPageCond(QueryBean queryBean)
            throws LogicException {
        ResultPOListBean<EquipmenbatteryProbePo> resultPOListBean = new ResultPOListBean<EquipmenbatteryProbePo>();
        List<EquipmenbatteryProbePo> equipmenbatteryProbePos = new ArrayList<EquipmenbatteryProbePo>();
        // page query
        int count = equipmenbatteryProbeSer.selectEquipmenbatteryProbeListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenbatteryProbeEntity> equipmenbatteryProbes = equipmenbatteryProbeSer
                    .selectEquipmenbatteryProbeListByPageCond(queryBean);
            EquipmenbatteryProbePo equipmenbatteryProbePo = null;
            for (EquipmenbatteryProbeEntity equipmenbatteryProbe : equipmenbatteryProbes) {
                equipmenbatteryProbePo = new EquipmenbatteryProbePo();
                BeanUtil.copyProperties(equipmenbatteryProbe, equipmenbatteryProbePo);
                equipmenbatteryProbePos.add(equipmenbatteryProbePo);
            }
        }
        resultPOListBean.success(equipmenbatteryProbePos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryProbePo> queryEquipmenbatteryProbeListByCond(
            EquipmenbatteryProbePo equipmenbatteryProbePo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenbatteryProbePo> result = new ResultPOListBean<EquipmenbatteryProbePo>();
        // po list
        List<EquipmenbatteryProbePo> equipmenbatteryProbePos = new ArrayList<EquipmenbatteryProbePo>();
        // excute query
        List<EquipmenbatteryProbeEntity> equipmenbatteryProbes = equipmenbatteryProbeSer
                .selectEquipmenbatteryProbeListByCond(equipmenbatteryProbePo);
        EquipmenbatteryProbePo resultPo = null;
        // poList
        for (EquipmenbatteryProbeEntity equipmenbatteryProbe : equipmenbatteryProbes) {
            resultPo = new EquipmenbatteryProbePo();
            BeanUtil.copyProperties(equipmenbatteryProbe, resultPo);
            equipmenbatteryProbePos.add(resultPo);
        }
        // return success
        result.success(equipmenbatteryProbePos, equipmenbatteryProbePos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryProbePo> queryEquipmenbatteryProbeById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenbatteryProbePo> result = new ResultPOBean<EquipmenbatteryProbePo>();
        EquipmenbatteryProbePo po = null;
        EquipmenbatteryProbePo queryPo = new EquipmenbatteryProbePo();
        queryPo.setId(id);
        EquipmenbatteryProbeEntity entity = equipmenbatteryProbeSer.selectEquipmenbatteryProbeByCond(queryPo);
        if (null != entity) {
            po = new EquipmenbatteryProbePo();
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
    public ResultBean modifyEquipmenbatteryProbeByPo(EquipmenbatteryProbePo equipmenbatteryProbePo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryProbeEntity equipmenbatteryProbe = new EquipmenbatteryProbeEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenbatteryProbePo, equipmenbatteryProbe);
        this.equipmenbatteryProbeEdt.updateEquipmenbatteryProbeByPrimaryKey(equipmenbatteryProbe);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryProbe(EquipmenbatteryProbePo equipmenbatteryProbePo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryProbeEntity equipmenbatteryProbe = new EquipmenbatteryProbeEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenbatteryProbePo, equipmenbatteryProbe);
        int status = this.equipmenbatteryProbeEdt.insertEquipmenbatteryProbe(equipmenbatteryProbe);
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
    public ResultBean removeEquipmenbatteryProbeById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmenbatteryProbeEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenbatteryProbe(EquipmenbatteryProbePo equipmenbatteryProbePo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenbatteryProbeEntity equipmenbatteryProbe = new EquipmenbatteryProbeEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenbatteryProbePo, equipmenbatteryProbe);
        // excute delete
        this.equipmenbatteryProbeEdt.deleteEquipmenbatteryProbe(equipmenbatteryProbe);
        result.success();
        return result;
    }
}