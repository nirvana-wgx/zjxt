package com.ctevs.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmenwarnEdt;
import com.sea.dao.EquipmenwarnSer;
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
import com.sea.model.EquipmenwarnEntity;
import com.sea.model.EquipmenwarnPo;

/**
 * Equipmenwarn Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenwarnLogicImpl extends BaseLogic implements EquipmenwarnLogic {

    @Autowired
    private EquipmenwarnSer equipmenwarnSer;

    @Autowired
    private EquipmenwarnEdt equipmenwarnEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnPo> queryEquipmenwarnListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmenwarnPo> resultPOListBean = new ResultPOListBean<EquipmenwarnPo>();
        List<EquipmenwarnPo> equipmenwarnPos = new ArrayList<EquipmenwarnPo>();
        // page query
        int count = equipmenwarnSer.selectEquipmenwarnListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenwarnEntity> equipmenwarns = equipmenwarnSer.selectEquipmenwarnListByPageCond(queryBean);
            EquipmenwarnPo equipmenwarnPo = null;
            for (EquipmenwarnEntity equipmenwarn : equipmenwarns) {
                equipmenwarnPo = new EquipmenwarnPo();
                BeanUtil.copyProperties(equipmenwarn, equipmenwarnPo);
                equipmenwarnPos.add(equipmenwarnPo);
            }
        }
        resultPOListBean.success(equipmenwarnPos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnPo> queryEquipmenwarnListByCond(EquipmenwarnPo equipmenwarnPo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenwarnPo> result = new ResultPOListBean<EquipmenwarnPo>();
        // po list
        List<EquipmenwarnPo> equipmenwarnPos = new ArrayList<EquipmenwarnPo>();
        // excute query
        List<EquipmenwarnEntity> equipmenwarns = equipmenwarnSer.selectEquipmenwarnListByCond(equipmenwarnPo);
        EquipmenwarnPo resultPo = null;
        // poList
        for (EquipmenwarnEntity equipmenwarn : equipmenwarns) {
            resultPo = new EquipmenwarnPo();
            BeanUtil.copyProperties(equipmenwarn, resultPo);
            equipmenwarnPos.add(resultPo);
        }
        // return success
        result.success(equipmenwarnPos, equipmenwarnPos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenwarnPo> queryEquipmenwarnById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenwarnPo> result = new ResultPOBean<EquipmenwarnPo>();
        EquipmenwarnPo po = null;
        EquipmenwarnPo queryPo = new EquipmenwarnPo();
        queryPo.setId(id);
        EquipmenwarnEntity entity = equipmenwarnSer.selectEquipmenwarnByCond(queryPo);
        if (null != entity) {
            po = new EquipmenwarnPo();
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
    public ResultBean modifyEquipmenwarnByPo(EquipmenwarnPo equipmenwarnPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenwarnEntity equipmenwarn = new EquipmenwarnEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenwarnPo, equipmenwarn);
        this.equipmenwarnEdt.updateEquipmenwarnByPrimaryKey(equipmenwarn);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenwarn(EquipmenwarnPo equipmenwarnPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenwarnEntity equipmenwarn = new EquipmenwarnEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenwarnPo, equipmenwarn);
        int status = this.equipmenwarnEdt.insertEquipmenwarn(equipmenwarn);
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
    public ResultBean removeEquipmenwarnById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmenwarnEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenwarn(EquipmenwarnPo equipmenwarnPo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenwarnEntity equipmenwarn = new EquipmenwarnEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenwarnPo, equipmenwarn);
        // excute delete
        this.equipmenwarnEdt.deleteEquipmenwarn(equipmenwarn);
        result.success();
        return result;
    }
}