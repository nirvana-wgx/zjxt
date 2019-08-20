package com.ctevs.logic;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmenprogramEdt;
import com.sea.dao.EquipmenprogramSer;
import com.sea.framework.constants.Constants;
import com.sea.framework.exception.LogicException;
import com.sea.framework.logic.BaseLogic;
import com.sea.framework.message.MessageManager;
import com.sea.framework.query.QueryBean;
import com.sea.framework.result.ResultBean;
import com.sea.framework.result.ResultPOBean;
import com.sea.framework.result.ResultPOListBean;
import com.sea.framework.util.BeanUtil;
import com.sea.model.EquipmenprogramEntity;
import com.sea.model.EquipmenprogramPo;

/**
 * Equipmenprogram Logic Implement
 * @author system
 */
@Service
public class EquipmenprogramLogicImpl extends BaseLogic implements EquipmenprogramLogic {
    
    @Autowired
    private EquipmenprogramSer equipmenprogramSer;

    @Autowired
    private EquipmenprogramEdt equipmenprogramEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenprogramPo> queryEquipmenprogramListByPageCond(QueryBean queryBean)
        throws LogicException {
        ResultPOListBean<EquipmenprogramPo> resultPOListBean = new ResultPOListBean<EquipmenprogramPo>();
        List<EquipmenprogramPo> equipmenprogramPos = new ArrayList<EquipmenprogramPo>();
        // page query
        int count = equipmenprogramSer.selectEquipmenprogramListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenprogramEntity> equipmenprograms = equipmenprogramSer.selectEquipmenprogramListByPageCond(queryBean);
            EquipmenprogramPo equipmenprogramPo = null;
            for (EquipmenprogramEntity equipmenprogram : equipmenprograms) {
                equipmenprogramPo = new EquipmenprogramPo();
                BeanUtil.copyProperties(equipmenprogram, equipmenprogramPo);
                equipmenprogramPos.add(equipmenprogramPo);
            }
        }
        resultPOListBean.success(equipmenprogramPos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenprogramPo> queryEquipmenprogramListByCond(EquipmenprogramPo equipmenprogramPo)
        throws LogicException {
        // return object
        ResultPOListBean<EquipmenprogramPo> result = new ResultPOListBean<EquipmenprogramPo>();
        // po list
        List<EquipmenprogramPo> equipmenprogramPos = new ArrayList<EquipmenprogramPo>();
        // excute query
        List<EquipmenprogramEntity> equipmenprograms = equipmenprogramSer.selectEquipmenprogramListByCond(equipmenprogramPo);
        EquipmenprogramPo resultPo = null;
        // poList
        for (EquipmenprogramEntity equipmenprogram : equipmenprograms) {
            resultPo = new EquipmenprogramPo();
            BeanUtil.copyProperties(equipmenprogram, resultPo);
            equipmenprogramPos.add(resultPo);
        }
        // return success
        result.success(equipmenprogramPos, equipmenprogramPos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenprogramPo> queryEquipmenprogramById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenprogramPo> result = new ResultPOBean<EquipmenprogramPo>();
        EquipmenprogramPo po = null;
        EquipmenprogramPo queryPo = new EquipmenprogramPo();
        queryPo.setId(id);
        EquipmenprogramEntity entity = equipmenprogramSer.selectEquipmenprogramByCond(queryPo);
        if (null != entity) {
            po = new EquipmenprogramPo();
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
    public ResultBean modifyEquipmenprogramByPo(EquipmenprogramPo equipmenprogramPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenprogramEntity equipmenprogram = new EquipmenprogramEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenprogramPo, equipmenprogram);
        this.equipmenprogramEdt.updateEquipmenprogramByPrimaryKey(equipmenprogram);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenprogram(EquipmenprogramPo equipmenprogramPo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenprogramEntity equipmenprogram = new EquipmenprogramEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenprogramPo, equipmenprogram);
        int status = this.equipmenprogramEdt.insertEquipmenprogram(equipmenprogram);
        if (status != Constants.ZERO) {
            resultBean.success();
        } else {
            // error
            resultBean.failure(messageManager.get(""));
        }
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenprogramById(BigInteger id) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        // excute delete
        this.equipmenprogramEdt.deleteByPrimaryKey(id);
        result.success();
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultBean removeEquipmenprogram(EquipmenprogramPo equipmenprogramPo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenprogramEntity equipmenprogram = new EquipmenprogramEntity();
        // po->entity
        BeanUtil.copyProperties(equipmenprogramPo, equipmenprogram);
        // excute delete
        this.equipmenprogramEdt.deleteEquipmenprogram(equipmenprogram);
        result.success();
        return result;
    }
}