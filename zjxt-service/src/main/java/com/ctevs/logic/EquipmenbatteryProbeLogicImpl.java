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
import com.ctevs.dao.EquipmenbatteryProbeEdt;
import com.ctevs.dao.EquipmenbatteryProbeSer;
import com.ctevs.po.EquipmenbatteryProbeEntity;
import com.ctevs.vo.EquipmenbatteryProbeVo;

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
    public ResultPOListBean<EquipmenbatteryProbeVo> queryEquipmenbatteryProbeListByPageCond(QueryBean queryBean)
            throws LogicException {
        ResultPOListBean<EquipmenbatteryProbeVo> resultPOListBean = new ResultPOListBean<EquipmenbatteryProbeVo>();
        List<EquipmenbatteryProbeVo> EquipmenbatteryProbeVos = new ArrayList<EquipmenbatteryProbeVo>();
        // page query
        int count = equipmenbatteryProbeSer.selectEquipmenbatteryProbeListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenbatteryProbeEntity> equipmenbatteryProbes = equipmenbatteryProbeSer
                    .selectEquipmenbatteryProbeListByPageCond(queryBean);
            EquipmenbatteryProbeVo EquipmenbatteryProbeVo = null;
            for (EquipmenbatteryProbeEntity equipmenbatteryProbe : equipmenbatteryProbes) {
                EquipmenbatteryProbeVo = new EquipmenbatteryProbeVo();
                BeanUtil.copyProperties(equipmenbatteryProbe, EquipmenbatteryProbeVo);
                EquipmenbatteryProbeVos.add(EquipmenbatteryProbeVo);
            }
        }
        resultPOListBean.success(EquipmenbatteryProbeVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenbatteryProbeVo> queryEquipmenbatteryProbeListByCond(
            EquipmenbatteryProbeVo EquipmenbatteryProbeVo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenbatteryProbeVo> result = new ResultPOListBean<EquipmenbatteryProbeVo>();
        // po list
        List<EquipmenbatteryProbeVo> EquipmenbatteryProbeVos = new ArrayList<EquipmenbatteryProbeVo>();
        // excute query
        List<EquipmenbatteryProbeEntity> equipmenbatteryProbes = equipmenbatteryProbeSer
                .selectEquipmenbatteryProbeListByCond(EquipmenbatteryProbeVo);
        EquipmenbatteryProbeVo resultPo = null;
        // poList
        for (EquipmenbatteryProbeEntity equipmenbatteryProbe : equipmenbatteryProbes) {
            resultPo = new EquipmenbatteryProbeVo();
            BeanUtil.copyProperties(equipmenbatteryProbe, resultPo);
            EquipmenbatteryProbeVos.add(resultPo);
        }
        // return success
        result.success(EquipmenbatteryProbeVos, EquipmenbatteryProbeVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenbatteryProbeVo> queryEquipmenbatteryProbeById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenbatteryProbeVo> result = new ResultPOBean<EquipmenbatteryProbeVo>();
        EquipmenbatteryProbeVo po = null;
        EquipmenbatteryProbeVo queryPo = new EquipmenbatteryProbeVo();
        queryPo.setId(id);
        EquipmenbatteryProbeEntity entity = equipmenbatteryProbeSer.selectEquipmenbatteryProbeByCond(queryPo);
        if (null != entity) {
            po = new EquipmenbatteryProbeVo();
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
    public ResultBean modifyEquipmenbatteryProbeByPo(EquipmenbatteryProbeVo EquipmenbatteryProbeVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryProbeEntity equipmenbatteryProbe = new EquipmenbatteryProbeEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenbatteryProbeVo, equipmenbatteryProbe);
        this.equipmenbatteryProbeEdt.updateEquipmenbatteryProbeByPrimaryKey(equipmenbatteryProbe);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenbatteryProbe(EquipmenbatteryProbeVo EquipmenbatteryProbeVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenbatteryProbeEntity equipmenbatteryProbe = new EquipmenbatteryProbeEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenbatteryProbeVo, equipmenbatteryProbe);
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
    public ResultBean removeEquipmenbatteryProbe(EquipmenbatteryProbeVo EquipmenbatteryProbeVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenbatteryProbeEntity equipmenbatteryProbe = new EquipmenbatteryProbeEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenbatteryProbeVo, equipmenbatteryProbe);
        // excute delete
        this.equipmenbatteryProbeEdt.deleteEquipmenbatteryProbe(equipmenbatteryProbe);
        result.success();
        return result;
    }
}