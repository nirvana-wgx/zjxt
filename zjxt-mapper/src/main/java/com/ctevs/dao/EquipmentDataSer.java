package com.ctevs.dao;

import java.util.List;

import com.ctevs.page.QueryBean;
import com.ctevs.po.EquipmenbatteryEntity;
import com.ctevs.po.EquipmentDataEntity;
import com.ctevs.vo.EquipmenbatteryVo;

/**
 * Equipment Query Dao
 * 
 * @author system
 */
public interface EquipmentDataSer {
    /**
     * query list with page
     * 
     * @author system
     * @param queryBean queryBean
     * @return List<EquipmentEntity>
     */
    public List<EquipmentDataEntity> selectEquipmentDataListByPageCond(QueryBean queryBean);
    
    public List<EquipmentDataEntity> selectEquipmentDataNewListByPageCond(QueryBean queryBean);
    
    /**
     * query list count
     * 
     * @author system
     * @param queryBean queryBean
     * @return number
     */
    public int selectEquipmentDataListTotalCount(QueryBean queryBean);
    public int selectEquipmentDataNewListTotalCount(QueryBean queryBean);
    
    /**
     * 
     * @author haibing.xiao
     * @date: 2018年8月7日 下午3:57:59
     * @version 1.0
     *
     * @param EquipmenbatteryPo
     * @return
     */
    public List<EquipmenbatteryEntity> selectEquipmentBatteryDataListByCond(EquipmenbatteryVo equipmenbatteryPo);

   
    
    
    public  int  copyTableStatus(String isShard) ;
    
    public  int  copyTableLimit(String isShard) ;
    
    public  int  copyTableWarn(String isShard) ;
    
    public  int  copyTableBatteryProbe(String isShard) ;
    
    public  int  copyTableBatteryVoltage(String isShard) ;

   
}