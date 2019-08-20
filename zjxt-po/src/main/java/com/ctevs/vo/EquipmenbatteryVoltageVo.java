package com.ctevs.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * EquipmenbatteryVoltagePo Po
 * 
 * @author system
 */
public class EquipmenbatteryVoltageVo extends Vo {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1947664470055695166L;
    /**
     * 序列
     */
    private BigInteger id;
    /**
     * 设备ID 12位
     */
    private String equipmentId;
    /**
     * 设备数据批次号
     */
    private BigInteger batchNo;
    /**
     * 可充电储能子系统编号
     */
    private Integer batterySysNo;
    /**
     * 可充电储能装置总电压
     */
    private BigDecimal batteryVolage;
    /**
     * 电池总数（Nv）
     */
    private Integer batteryNumber;
    /**
     * 单体电池编号
     */
    private Integer oneBatteryNo;
    /**
     * 单体电池电压
     */
    private BigDecimal oneBatteryVolage;
    /**
     * 信息采集时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public BigInteger getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(BigInteger batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getBatterySysNo() {
        return batterySysNo;
    }

    public void setBatterySysNo(Integer batterySysNo) {
        this.batterySysNo = batterySysNo;
    }

    public BigDecimal getBatteryVolage() {
        return batteryVolage;
    }

    public void setBatteryVolage(BigDecimal batteryVolage) {
        this.batteryVolage = batteryVolage;
    }

    public Integer getBatteryNumber() {
        return batteryNumber;
    }

    public void setBatteryNumber(Integer batteryNumber) {
        this.batteryNumber = batteryNumber;
    }

    public Integer getOneBatteryNo() {
        return oneBatteryNo;
    }

    public void setOneBatteryNo(Integer oneBatteryNo) {
        this.oneBatteryNo = oneBatteryNo;
    }

    public BigDecimal getOneBatteryVolage() {
        return oneBatteryVolage;
    }

    public void setOneBatteryVolage(BigDecimal oneBatteryVolage) {
        this.oneBatteryVolage = oneBatteryVolage;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}