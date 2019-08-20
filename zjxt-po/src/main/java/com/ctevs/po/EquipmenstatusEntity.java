package com.ctevs.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * EquipmenstatusEntity BaseEntity
 * 
 * @author system
 */
public class EquipmenstatusEntity extends Entity {
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
     * 设备状态
     */
    private int status;
    /**
     * 状态功能码
     */
    private int statusCode;
    /**
     * 设备故障等级
     */
    private int failure;
    /**
     * 总正继电器状态
     */
    private int positiveRelayStatus;
    /**
     * 总负继电器状态
     */
    private int negativeRelayStatus;
    /**
     * 预充继电器状态
     */
    private int forecastRelayStatus;
    /**
     * 电池簇编号
     */
    private int batteryNo;
    /**
     * 可充电储能子系统个数
     */
    private int batteryNumber;
    /**
     * 系统电压
     */
    private BigDecimal sysVoltage;
    /**
     * 系统电流
     */
    private BigDecimal sysProbe;
    /**
     * 系统绝缘电阻
     */
    private int sysResistance;
    /**
     * 电池包正极对地绝缘电阻
     */
    private int batteryPositiveResistance;
    /**
     * 电池包负极对地绝缘电阻
     */
    private int batteryNegativeResistance;
    /**
     * SOC
     */
    private BigDecimal soc;
    /**
     * 总充电次数
     */
    private int chargeNumber;
    /**
     * 总充电功率
     */
    private BigDecimal charge;
    /**
     * 总放电功率
     */
    private BigDecimal discharge;
    /**
     * 信息采集时间
     */
    private Date collectionTime;
    /**
     * 创建时间
     */
    private Date addtime;

    /**
     * 继电器状态
     */
    private String relayStatus;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getPositiveRelayStatus() {
        return positiveRelayStatus;
    }

    public void setPositiveRelayStatus(int positiveRelayStatus) {
        this.positiveRelayStatus = positiveRelayStatus;
    }

    public int getNegativeRelayStatus() {
        return negativeRelayStatus;
    }

    public void setNegativeRelayStatus(int negativeRelayStatus) {
        this.negativeRelayStatus = negativeRelayStatus;
    }

    public int getForecastRelayStatus() {
        return forecastRelayStatus;
    }

    public void setForecastRelayStatus(int forecastRelayStatus) {
        this.forecastRelayStatus = forecastRelayStatus;
    }

    public int getBatteryNo() {
        return batteryNo;
    }

    public void setBatteryNo(int batteryNo) {
        this.batteryNo = batteryNo;
    }

    public int getBatteryNumber() {
        return batteryNumber;
    }

    public void setBatteryNumber(int batteryNumber) {
        this.batteryNumber = batteryNumber;
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

    public int getSysResistance() {
        return sysResistance;
    }

    public void setSysResistance(int sysResistance) {
        this.sysResistance = sysResistance;
    }

    public int getBatteryPositiveResistance() {
        return batteryPositiveResistance;
    }

    public void setBatteryPositiveResistance(int batteryPositiveResistance) {
        this.batteryPositiveResistance = batteryPositiveResistance;
    }

    public int getBatteryNegativeResistance() {
        return batteryNegativeResistance;
    }

    public void setBatteryNegativeResistance(int batteryNegativeResistance) {
        this.batteryNegativeResistance = batteryNegativeResistance;
    }

    public BigDecimal getSoc() {
        return soc;
    }

    public void setSoc(BigDecimal soc) {
        this.soc = soc;
    }

    public int getChargeNumber() {
        return chargeNumber;
    }

    public void setChargeNumber(int chargeNumber) {
        this.chargeNumber = chargeNumber;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getDischarge() {
        return discharge;
    }

    public void setDischarge(BigDecimal discharge) {
        this.discharge = discharge;
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

    public String getRelayStatus() {
        return relayStatus;
    }

    public void setRelayStatus(String relayStatus) {
        this.relayStatus = relayStatus;
    }
    
    
}