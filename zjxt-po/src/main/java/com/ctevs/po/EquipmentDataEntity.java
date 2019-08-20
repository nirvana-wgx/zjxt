package com.ctevs.po;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class EquipmentDataEntity extends Entity {

    /**
     * 设备ID 12位
     */
    private String equipmentId;

    private String equipmentName;
    /**
     * 设备数据批次号
     */
    private String batchNo;
    /**
     * 电池单体电压最高值
     */
    private BigDecimal oneBatteryVoltageHigh;
    /**
     * 电池单体电压最低值
     */
    private BigDecimal oneBatteryVoltageLow;

    /**
     * 最高温度值
     */
    private BigDecimal highTemperature;

    /**
     * 最低温度值
     */
    private BigDecimal lowTemperature;
    /**
     * 压差值
     */
    private BigDecimal voltageDifference;
    /**
     * 温差值
     */
    private BigDecimal temperatureDifference;

    /**
     * 设备状态
     */
    private Integer status;
    /**
     * 状态功能码
     */
    private Integer statusCode;
    /**
     * 设备故障等级
     */
    private Integer failure;
    
    /**
     * 系统电压
     */
    private BigDecimal sysVoltage;
    /**
     * 系统电流
     */
    private BigDecimal sysProbe;
    
    /**
     * SOC
     */
    private BigDecimal soc;
    

    /**
     * 信息采集时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public BigDecimal getOneBatteryVoltageHigh() {
        return oneBatteryVoltageHigh;
    }

    public void setOneBatteryVoltageHigh(BigDecimal oneBatteryVoltageHigh) {
        this.oneBatteryVoltageHigh = oneBatteryVoltageHigh;
    }

    public BigDecimal getOneBatteryVoltageLow() {
        return oneBatteryVoltageLow;
    }

    public void setOneBatteryVoltageLow(BigDecimal oneBatteryVoltageLow) {
        this.oneBatteryVoltageLow = oneBatteryVoltageLow;
    }

    public BigDecimal getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(BigDecimal highTemperature) {
        this.highTemperature = highTemperature;
    }

    public BigDecimal getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(BigDecimal lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public BigDecimal getVoltageDifference() {
        return voltageDifference;
    }

    public void setVoltageDifference(BigDecimal voltageDifference) {
        this.voltageDifference = voltageDifference;
    }

    public BigDecimal getTemperatureDifference() {
        return temperatureDifference;
    }

    public void setTemperatureDifference(BigDecimal temperatureDifference) {
        this.temperatureDifference = temperatureDifference;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public BigDecimal getSysVoltage() {
        return sysVoltage;
    }

    public void setSysVoltage(BigDecimal sysVoltage) {
        this.sysVoltage = sysVoltage;
    }

    public BigDecimal getSysProbe() {
        return sysProbe;
    }

    public void setSysProbe(BigDecimal sysProbe) {
        this.sysProbe = sysProbe;
    }

    public BigDecimal getSoc() {
        return soc;
    }

    public void setSoc(BigDecimal soc) {
        this.soc = soc;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    
}
