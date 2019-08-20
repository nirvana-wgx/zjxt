package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmenstatusEdt;
import com.sea.dao.EquipmenstatusSer;
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
import com.sea.model.EquipmenstatusEntity;
import com.sea.model.EquipmenstatusPo;

/**
 * Equipmenstatus Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenstatusLogicImpl extends BaseLogic implements EquipmenstatusLogic {

    @Autowired
    private EquipmenstatusSer equipmenstatusSer;

    @Autowired
    private EquipmenstatusEdt equipmenstatusEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusPo> queryEquipmenstatusListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmenstatusPo> resultPOListBean = new ResultPOListBean<EquipmenstatusPo>();
        List<EquipmenstatusPo> equipmenstatusPos = new ArrayList<EquipmenstatusPo>();
        // page query
        int count = equipmenstatusSer.selectEquipmenstatusListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenstatusEntity> equipmenstatuss = equipmenstatusSer.selectEquipmenstatusListByPageCond(queryBean);
            EquipmenstatusPo equipmenstatusPo = null;
            for (EquipmenstatusEntity equipmenstatus : equipmenstatuss) {
                equipmenstatusPo = new EquipmenstatusPo();
                BeanUtil.copyProperties(equipmenstatus, equipmenstatusPo);
                equipmenstatusPos.add(equipmenstatusPo);
            }
        }
        resultPOListBean.success(equipmenstatusPos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenstatusPo> queryEquipmenstatusListByCond(EquipmenstatusPo equipmenstatusPo)
            throws LogicException {
        // return object
        ResultPOListBean<EquipmenstatusPo> result = new ResultPOListBean<EquipmenstatusPo>();
        // po list
        List<EquipmenstatusPo> equipmenstatusPos = new ArrayList<EquipmenstatusPo>();
        // excute query
        List<EquipmenstatusEntity> equipmenstatuss = equipmenstatusSer.selectEquipmenstatusListByCond(equipmenstatusPo);
        EquipmenstatusPo resultPo = null;
        // poList
        for (EquipmenstatusEntity equipmenstatus : equipmenstatuss) {
            resultPo = new EquipmenstatusPo();
            BeanUtil.copyProperties(equipmenstatus, resultPo);
            equipmenstatusPos.add(resultPo);
        }
        // return success
        result.success(equipmenstatusPos, equipmenstatusPos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenstatusPo> queryEquipmenstatusById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenstatusPo> result = new ResultPOBean<EquipmenstatusPo>();
        EquipmenstatusPo po = null;
        EquipmenstatusPo queryPo = new EquipmenstatusPo();
        queryPo.setId(id);
        EquipmenstatusEntity entity = equipmenstatusSer.selectEquipmenstatusByCond(queryPo);
        if (null != entity) {
            po = new EquipmenstatusPo();
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
    public ResultBean modifyEquipmenstatusByPo(EquipmenstatusPo equipmenstatusPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenstatusEntity equipmenstatus = new EquipmenstatusEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenstatusPo, equipmenstatus);
        this.equipmenstatusEdt.updateEquipmenstatusByPrimaryKey(equipmenstatus);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenstatus(EquipmenstatusPo equipmenstatusPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenstatusEntity equipmenstatus = new EquipmenstatusEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenstatusPo, equipmenstatus);
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
    public ResultBean removeEquipmenstatus(EquipmenstatusPo equipmenstatusPo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenstatusEntity equipmenstatus = new EquipmenstatusEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenstatusPo, equipmenstatus);
        // excute delete
        this.equipmenstatusEdt.deleteEquipmenstatus(equipmenstatus);
        result.success();
        return result;
    }
}