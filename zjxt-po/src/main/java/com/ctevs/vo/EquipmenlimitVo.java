package com.ctevs.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * EquipmenlimitPo Po
 * 
 * @author system
 */
public class EquipmenlimitVo extends Vo {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 7450753977417461423L;
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
     * 最高电压电池子系统号
     */
    private Integer highVoltageSysNo;
    /**
     * 最高电压电池单体代号
     */
    private Integer highVoltageOneBatteryNo;
    /**
     * 最低电压电池子系统号
     */
    private Integer lowVoltageSysNo;
    /**
     * 最低电压电池单体代号
     */
    private Integer lowVoltageOneBatteryNo;
    /**
     * 电池单体电压最高值
     */
    private BigDecimal oneBatteryVoltageHigh;
    /**
     * 电池单体电压最低值
     */
    private BigDecimal oneBatteryVoltageLow;
    /**
     * 最高温度子系统号
     */
    private Integer highTemperatureSysNo;
    /**
     * 最高温度探针序号
     */
    private Integer highTemperatureProbeNo;
    /**
     * 最高温度值
     */
    private BigDecimal highTemperature;
    /**
     * 最低温度子系统号
     */
    private Integer lowTemperatureSysNo;
    /**
     * 最低温度探针序号
     */
    private Integer lowTemperatureProbeNo;
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

    public Integer getHighVoltageSysNo() {
        return highVoltageSysNo;
    }

    public void setHighVoltageSysNo(Integer highVoltageSysNo) {
        this.highVoltageSysNo = highVoltageSysNo;
    }

    public Integer getHighVoltageOneBatteryNo() {
        return highVoltageOneBatteryNo;
    }

    public void setHighVoltageOneBatteryNo(Integer highVoltageOneBatteryNo) {
        this.highVoltageOneBatteryNo = highVoltageOneBatteryNo;
    }

    public Integer getLowVoltageSysNo() {
        return lowVoltageSysNo;
    }

    public void setLowVoltageSysNo(Integer lowVoltageSysNo) {
        this.lowVoltageSysNo = lowVoltageSysNo;
    }

    public Integer getLowVoltageOneBatteryNo() {
        return lowVoltageOneBatteryNo;
    }

    public void setLowVoltageOneBatteryNo(Integer lowVoltageOneBatteryNo) {
        this.lowVoltageOneBatteryNo = lowVoltageOneBatteryNo;
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

    public Integer getHighTemperatureSysNo() {
        return highTemperatureSysNo;
    }

    public void setHighTemperatureSysNo(Integer highTemperatureSysNo) {
        this.highTemperatureSysNo = highTemperatureSysNo;
    }

    public Integer getHighTemperatureProbeNo() {
        return highTemperatureProbeNo;
    }

    public void setHighTemperatureProbeNo(Integer highTemperatureProbeNo) {
        this.highTemperatureProbeNo = highTemperatureProbeNo;
    }

    public BigDecimal getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(BigDecimal highTemperature) {
        this.highTemperature = highTemperature;
    }

    public Integer getLowTemperatureSysNo() {
        return lowTemperatureSysNo;
    }

    public void setLowTemperatureSysNo(Integer lowTemperatureSysNo) {
        this.lowTemperatureSysNo = lowTemperatureSysNo;
    }

    public Integer getLowTemperatureProbeNo() {
        return lowTemperatureProbeNo;
    }

    public void setLowTemperatureProbeNo(Integer lowTemperatureProbeNo) {
        this.lowTemperatureProbeNo = lowTemperatureProbeNo;
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