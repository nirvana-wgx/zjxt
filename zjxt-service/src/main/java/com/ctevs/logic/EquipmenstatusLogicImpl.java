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
import com.ctevs.dao.EquipmenstatusEdt;
import com.ctevs.dao.EquipmenstatusSer;
import com.ctevs.po.EquipmenstatusEntity;
import com.ctevs.vo.EquipmenstatusVo;

/**
 * Equipmenstatus Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenstatusLogicImpl   implements EquipmenstatusLogic {

    @Autowired
    private EquipmenstatusSer equipmenstatusSer;

    @Autowired
    private EquipmenstatusEdt equipmenstatusEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusVo> queryEquipmenstatusListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmenstatusVo> resultPOListBean = new ResultPOListBean<EquipmenstatusVo>();
        List<EquipmenstatusVo> EquipmenstatusVos = new ArrayList<EquipmenstatusVo>();
        // page query
        int count = equipmenstatusSer.selectEquipmenstatusListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenstatusEntity> equipmenstatuss = equipmenstatusSer.selectEquipmenstatusListByPageCond(queryBean);
            EquipmenstatusVo EquipmenstatusVo = null;
            for (EquipmenstatusEntity equipmenstatus : equipmenstatuss) {
                EquipmenstatusVo = new EquipmenstatusVo();
                BeanUtil.copyProperties(equipmenstatus, EquipmenstatusVo);
                EquipmenstatusVos.add(EquipmenstatusVo);
            }
        }
        resultPOListBean.success(EquipmenstatusVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusVo> queryEquipmenstatusListByCond(EquipmenstatusVo EquipmenstatusVo)
            throws LogicException {
        // return object
        ResultPOListBean<EquipmenstatusVo> result = new ResultPOListBean<EquipmenstatusVo>();
        // po list
        List<EquipmenstatusVo> EquipmenstatusVos = new ArrayList<EquipmenstatusVo>();
        // excute query
        List<EquipmenstatusEntity> equipmenstatuss = equipmenstatusSer.selectEquipmenstatusListByCond(EquipmenstatusVo);
        EquipmenstatusVo resultPo = null;
        // poList
        for (EquipmenstatusEntity equipmenstatus : equipmenstatuss) {
            resultPo = new EquipmenstatusVo();
            BeanUtil.copyProperties(equipmenstatus, resultPo);
            EquipmenstatusVos.add(resultPo);
        }
        // return success
        result.success(EquipmenstatusVos, EquipmenstatusVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenstatusVo> queryEquipmenstatusById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenstatusVo> result = new ResultPOBean<EquipmenstatusVo>();
        EquipmenstatusVo po = null;
        EquipmenstatusVo queryPo = new EquipmenstatusVo();
        queryPo.setId(id);
        EquipmenstatusEntity entity = equipmenstatusSer.selectEquipmenstatusByCond(queryPo);
        if (null != entity) {
            po = new EquipmenstatusVo();
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
    public ResultBean modifyEquipmenstatusByPo(EquipmenstatusVo EquipmenstatusVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenstatusEntity equipmenstatus = new EquipmenstatusEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenstatusVo, equipmenstatus);
        this.equipmenstatusEdt.updateEquipmenstatusByPrimaryKey(equipmenstatus);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenstatus(EquipmenstatusVo EquipmenstatusVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenstatusEntity equipmenstatus = new EquipmenstatusEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenstatusVo, equipmenstatus);
        int status = this.equipmenstatusEdt.insertEquipmenstatus(equipmenstatus);
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
    public ResultBean removeEquipmenstatusById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmenstatusEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenstatus(EquipmenstatusVo EquipmenstatusVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenstatusEntity equipmenstatus = new EquipmenstatusEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenstatusVo, equipmenstatus);
        // excute delete
        this.equipmenstatusEdt.deleteEquipmenstatus(equipmenstatus);
        result.success();
        return result;
    }
}