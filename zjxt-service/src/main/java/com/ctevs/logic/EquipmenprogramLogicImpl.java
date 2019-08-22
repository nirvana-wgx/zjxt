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
import com.ctevs.dao.EquipmenprogramEdt;
import com.ctevs.dao.EquipmenprogramSer;
import com.ctevs.po.EquipmenprogramEntity;
import com.ctevs.vo.EquipmenprogramVo;

/**
 * Equipmenprogram Logic Implement
 * @author system
 */
@Service
public class EquipmenprogramLogicImpl  implements EquipmenprogramLogic {
    
    @Autowired
    private EquipmenprogramSer equipmenprogramSer;

    @Autowired
    private EquipmenprogramEdt equipmenprogramEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenprogramVo> queryEquipmenprogramListByPageCond(QueryBean queryBean)
        throws LogicException {
        ResultPOListBean<EquipmenprogramVo> resultPOListBean = new ResultPOListBean<EquipmenprogramVo>();
        List<EquipmenprogramVo> EquipmenprogramVos = new ArrayList<EquipmenprogramVo>();
        // page query
        int count = equipmenprogramSer.selectEquipmenprogramListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenprogramEntity> equipmenprograms = equipmenprogramSer.selectEquipmenprogramListByPageCond(queryBean);
            EquipmenprogramVo EquipmenprogramVo = null;
            for (EquipmenprogramEntity equipmenprogram : equipmenprograms) {
                EquipmenprogramVo = new EquipmenprogramVo();
                BeanUtil.copyProperties(equipmenprogram, EquipmenprogramVo);
                EquipmenprogramVos.add(EquipmenprogramVo);
            }
        }
        resultPOListBean.success(EquipmenprogramVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenprogramVo> queryEquipmenprogramListByCond(EquipmenprogramVo EquipmenprogramVo)
        throws LogicException {
        // return object
        ResultPOListBean<EquipmenprogramVo> result = new ResultPOListBean<EquipmenprogramVo>();
        // po list
        List<EquipmenprogramVo> EquipmenprogramVos = new ArrayList<EquipmenprogramVo>();
        // excute query
        List<EquipmenprogramEntity> equipmenprograms = equipmenprogramSer.selectEquipmenprogramListByCond(EquipmenprogramVo);
        EquipmenprogramVo resultPo = null;
        // poList
        for (EquipmenprogramEntity equipmenprogram : equipmenprograms) {
            resultPo = new EquipmenprogramVo();
            BeanUtil.copyProperties(equipmenprogram, resultPo);
            EquipmenprogramVos.add(resultPo);
        }
        // return success
        result.success(EquipmenprogramVos, EquipmenprogramVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenprogramVo> queryEquipmenprogramById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenprogramVo> result = new ResultPOBean<EquipmenprogramVo>();
        EquipmenprogramVo po = null;
        EquipmenprogramVo queryPo = new EquipmenprogramVo();
        queryPo.setId(id);
        EquipmenprogramEntity entity = equipmenprogramSer.selectEquipmenprogramByCond(queryPo);
        if (null != entity) {
            po = new EquipmenprogramVo();
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
    public ResultBean modifyEquipmenprogramByPo(EquipmenprogramVo EquipmenprogramVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenprogramEntity equipmenprogram = new EquipmenprogramEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenprogramVo, equipmenprogram);
        this.equipmenprogramEdt.updateEquipmenprogramByPrimaryKey(equipmenprogram);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenprogram(EquipmenprogramVo EquipmenprogramVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenprogramEntity equipmenprogram = new EquipmenprogramEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenprogramVo, equipmenprogram);
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
    public ResultBean removeEquipmenprogram(EquipmenprogramVo EquipmenprogramVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenprogramEntity equipmenprogram = new EquipmenprogramEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenprogramVo, equipmenprogram);
        // excute delete
        this.equipmenprogramEdt.deleteEquipmenprogram(equipmenprogram);
        result.success();
        return result;
    }
}