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
import com.ctevs.dao.EquipmenwarnEdt;
import com.ctevs.dao.EquipmenwarnSer;
import com.ctevs.po.EquipmenwarnEntity;
import com.ctevs.vo.EquipmenwarnVo;

/**
 * Equipmenwarn Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmenwarnLogicImpl   implements EquipmenwarnLogic {

    @Autowired
    private EquipmenwarnSer equipmenwarnSer;

    @Autowired
    private EquipmenwarnEdt equipmenwarnEdt;

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnVo> queryEquipmenwarnListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmenwarnVo> resultPOListBean = new ResultPOListBean<EquipmenwarnVo>();
        List<EquipmenwarnVo> EquipmenwarnVos = new ArrayList<EquipmenwarnVo>();
        // page query
        int count = equipmenwarnSer.selectEquipmenwarnListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmenwarnEntity> equipmenwarns = equipmenwarnSer.selectEquipmenwarnListByPageCond(queryBean);
            EquipmenwarnVo EquipmenwarnVo = null;
            for (EquipmenwarnEntity equipmenwarn : equipmenwarns) {
                EquipmenwarnVo = new EquipmenwarnVo();
                BeanUtil.copyProperties(equipmenwarn, EquipmenwarnVo);
                EquipmenwarnVos.add(EquipmenwarnVo);
            }
        }
        resultPOListBean.success(EquipmenwarnVos, count);
        return resultPOListBean;
    }

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmenwarnVo> queryEquipmenwarnListByCond(EquipmenwarnVo EquipmenwarnVo) throws LogicException {
        // return object
        ResultPOListBean<EquipmenwarnVo> result = new ResultPOListBean<EquipmenwarnVo>();
        // po list
        List<EquipmenwarnVo> EquipmenwarnVos = new ArrayList<EquipmenwarnVo>();
        // excute query
        List<EquipmenwarnEntity> equipmenwarns = equipmenwarnSer.selectEquipmenwarnListByCond(EquipmenwarnVo);
        EquipmenwarnVo resultPo = null;
        // poList
        for (EquipmenwarnEntity equipmenwarn : equipmenwarns) {
            resultPo = new EquipmenwarnVo();
            BeanUtil.copyProperties(equipmenwarn, resultPo);
            EquipmenwarnVos.add(resultPo);
        }
        // return success
        result.success(EquipmenwarnVos, EquipmenwarnVos.size());
        return result;
    }

    /**
     * @throws LogicException
     */
    public ResultPOBean<EquipmenwarnVo> queryEquipmenwarnById(BigInteger id) throws LogicException {
        // return object
        ResultPOBean<EquipmenwarnVo> result = new ResultPOBean<EquipmenwarnVo>();
        EquipmenwarnVo po = null;
        EquipmenwarnVo queryPo = new EquipmenwarnVo();
        queryPo.setId(id);
        EquipmenwarnEntity entity = equipmenwarnSer.selectEquipmenwarnByCond(queryPo);
        if (null != entity) {
            po = new EquipmenwarnVo();
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
    public ResultBean modifyEquipmenwarnByPo(EquipmenwarnVo EquipmenwarnVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenwarnEntity equipmenwarn = new EquipmenwarnEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenwarnVo, equipmenwarn);
        this.equipmenwarnEdt.updateEquipmenwarnByPrimaryKey(equipmenwarn);
        resultBean.success();
        return resultBean;
    }

    /**
     * @throws LogicException
     */
    public ResultBean addEquipmenwarn(EquipmenwarnVo EquipmenwarnVo) throws LogicException {
        ResultBean resultBean = new ResultBean();
        EquipmenwarnEntity equipmenwarn = new EquipmenwarnEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenwarnVo, equipmenwarn);
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
    public ResultBean removeEquipmenwarn(EquipmenwarnVo EquipmenwarnVo) throws LogicException {
        // return object
        ResultBean result = new ResultBean();
        EquipmenwarnEntity equipmenwarn = new EquipmenwarnEntity();
        // po->entity
        BeanUtil.copyProperties(EquipmenwarnVo, equipmenwarn);
        // excute delete
        this.equipmenwarnEdt.deleteEquipmenwarn(equipmenwarn);
        result.success();
        return result;
    }
}