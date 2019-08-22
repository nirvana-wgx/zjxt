package com.ctevs.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctevs.common.BeanUtil;
import com.ctevs.common.exception.LogicException;
import com.ctevs.common.message.MessageManager;
import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultPOListBean;
import com.ctevs.dao.EquipmentDataSer;
import com.ctevs.po.EquipmenbatteryEntity;
import com.ctevs.po.EquipmentDataEntity;
import com.ctevs.vo.EquipmenbatteryVo;
import com.ctevs.vo.EquipmentDataVo;

/**
 * Equipment Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmentDataLogicImpl   implements EquipmentDataLogic {

    @Autowired
    private EquipmentDataSer equipmentDataSer;

    

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentDataVo> queryEquipmentDataListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmentDataVo> resultPOListBean = new ResultPOListBean<EquipmentDataVo>();
        List<EquipmentDataVo> equipmentPos = new ArrayList<EquipmentDataVo>();
        // page query
        int count = equipmentDataSer.selectEquipmentDataListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmentDataEntity> equipments = equipmentDataSer.selectEquipmentDataListByPageCond(queryBean);
            EquipmentDataVo equipmentPo = null;
            for (EquipmentDataEntity equipment : equipments) {
                equipmentPo = new EquipmentDataVo();
                BeanUtil.copyProperties(equipment, equipmentPo);
                equipmentPos.add(equipmentPo);
            }
        }
        resultPOListBean.success(equipmentPos, count);
        return resultPOListBean;
    }

    @Override
    public ResultPOListBean<EquipmentDataVo> queryEquipmentDataNewListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmentDataVo> resultPOListBean = new ResultPOListBean<EquipmentDataVo>();
        List<EquipmentDataVo> equipmentPos = new ArrayList<EquipmentDataVo>();
        // page query
        int count = equipmentDataSer.selectEquipmentDataNewListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmentDataEntity> equipments = equipmentDataSer.selectEquipmentDataNewListByPageCond(queryBean);
            EquipmentDataVo equipmentPo = null;
            for (EquipmentDataEntity equipment : equipments) {
                equipmentPo = new EquipmentDataVo();
                BeanUtil.copyProperties(equipment, equipmentPo);
                equipmentPos.add(equipmentPo);
            }
        }
        resultPOListBean.success(equipmentPos, count);
        return resultPOListBean;
    }
    @Override
    public ResultPOListBean<EquipmenbatteryVo> selectEquipmentBatteryDataListByCond(EquipmenbatteryVo EquipmenbatteryVo) throws LogicException{
            // return object
            ResultPOListBean<EquipmenbatteryVo> result = new ResultPOListBean<EquipmenbatteryVo>();
            // po list
            List<EquipmenbatteryVo> EquipmenbatteryVos = new ArrayList<EquipmenbatteryVo>();
            // excute query
            List<EquipmenbatteryEntity> equipmenbatteryEntitys = equipmentDataSer.selectEquipmentBatteryDataListByCond(EquipmenbatteryVo);
            EquipmenbatteryVo resultPo = null;
            // poList
            for (EquipmenbatteryEntity equipmenbatteryEntity : equipmenbatteryEntitys) {
                resultPo = new EquipmenbatteryVo();
                BeanUtil.copyProperties(equipmenbatteryEntity, resultPo);
                EquipmenbatteryVos.add(resultPo);
            }
            // return success
            result.success(EquipmenbatteryVos, EquipmenbatteryVos.size());
            return result;
    }

    @Override
    public boolean createShardTable() throws LogicException {
        equipmentDataSer.copyTableStatus("true");
        equipmentDataSer.copyTableLimit("true");
        equipmentDataSer.copyTableWarn("true");
        equipmentDataSer.copyTableBatteryVoltage("true");
        equipmentDataSer.copyTableBatteryProbe("true");
        return false;
    }

   

    
}