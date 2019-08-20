package com.ctevs.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sea.dao.EquipmentDataSer;
import com.sea.framework.exception.LogicException;
import com.sea.framework.logic.BaseLogic;
import com.sea.framework.message.MessageManager;
import com.sea.framework.query.QueryBean;
import com.sea.framework.result.ResultPOListBean;
import com.sea.framework.util.BeanUtil;
import com.sea.model.EquipmenbatteryEntity;
import com.sea.model.EquipmenbatteryPo;
import com.sea.model.EquipmentDataEntity;
import com.sea.model.EquipmentDataPo;

/**
 * Equipment Logic Implement
 * 
 * @author system
 */
@Service
public class EquipmentDataLogicImpl extends BaseLogic implements EquipmentDataLogic {

    @Autowired
    private EquipmentDataSer equipmentDataSer;

    

    @Autowired
    private MessageManager messageManager;

    /**
     * @throws LogicException
     */
    public ResultPOListBean<EquipmentDataPo> queryEquipmentDataListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmentDataPo> resultPOListBean = new ResultPOListBean<EquipmentDataPo>();
        List<EquipmentDataPo> equipmentPos = new ArrayList<EquipmentDataPo>();
        // page query
        int count = equipmentDataSer.selectEquipmentDataListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmentDataEntity> equipments = equipmentDataSer.selectEquipmentDataListByPageCond(queryBean);
            EquipmentDataPo equipmentPo = null;
            for (EquipmentDataEntity equipment : equipments) {
                equipmentPo = new EquipmentDataPo();
                BeanUtil.copyProperties(equipment, equipmentPo);
                equipmentPos.add(equipmentPo);
            }
        }
        resultPOListBean.success(equipmentPos, count);
        return resultPOListBean;
    }

    @Override
    public ResultPOListBean<EquipmentDataPo> queryEquipmentDataNewListByPageCond(QueryBean queryBean) throws LogicException {
        ResultPOListBean<EquipmentDataPo> resultPOListBean = new ResultPOListBean<EquipmentDataPo>();
        List<EquipmentDataPo> equipmentPos = new ArrayList<EquipmentDataPo>();
        // page query
        int count = equipmentDataSer.selectEquipmentDataNewListTotalCount(queryBean);
        if (count > 0) {
            // total count
            queryBean.resetTotalCount(count);
            List<EquipmentDataEntity> equipments = equipmentDataSer.selectEquipmentDataNewListByPageCond(queryBean);
            EquipmentDataPo equipmentPo = null;
            for (EquipmentDataEntity equipment : equipments) {
                equipmentPo = new EquipmentDataPo();
                BeanUtil.copyProperties(equipment, equipmentPo);
                equipmentPos.add(equipmentPo);
            }
        }
        resultPOListBean.success(equipmentPos, count);
        return resultPOListBean;
    }
    @Override
    public ResultPOListBean<EquipmenbatteryPo> selectEquipmentBatteryDataListByCond(EquipmenbatteryPo equipmenbatteryPo) throws LogicException{
            // return object
            ResultPOListBean<EquipmenbatteryPo> result = new ResultPOListBean<EquipmenbatteryPo>();
            // po list
            List<EquipmenbatteryPo> equipmenbatteryPos = new ArrayList<EquipmenbatteryPo>();
            // excute query
            List<EquipmenbatteryEntity> equipmenbatteryEntitys = equipmentDataSer.selectEquipmentBatteryDataListByCond(equipmenbatteryPo);
            EquipmenbatteryPo resultPo = null;
            // poList
            for (EquipmenbatteryEntity equipmenbatteryEntity : equipmenbatteryEntitys) {
                resultPo = new EquipmenbatteryPo();
                BeanUtil.copyProperties(equipmenbatteryEntity, resultPo);
                equipmenbatteryPos.add(resultPo);
            }
            // return success
            result.success(equipmenbatteryPos, equipmenbatteryPos.size());
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