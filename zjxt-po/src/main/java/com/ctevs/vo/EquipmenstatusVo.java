package com.ctevs.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * EquipmenstatusPo Po
 * 
 * @author system
 */
public class EquipmenstatusVo extends Vo {
    /**
     */
    private static final long serialVersionUID = 1745392250905098756L;
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
    private Integer status;
    /**
     * 设备状态
     */
    private String statusName;
    /**
     * 状态功能码
     */
    private Integer statusCode;
    /**
     * 设备故障等级
     */
    private Integer failure;
    /**
     * 设备故障等级
     */
    private String failureName;
    /**
     * 总正继电器状态
     */
    private Integer positiveRelayStatus;
    /**
     * 总负继电器状态
     */
    private Integer negativeRelayStatus;
    /**
     * 预充继电器状态
     */
    private Integer forecastRelayStatus;
    /**
     * 电池簇编号
     */
    private Integer batteryNo;
    /**
     * 可充电储能子系统个数
     */
    private Integer batteryNumber;
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
    private Integer sysResistance;
    /**
     * 电池包正极对地绝缘电阻
     */
    private Integer batteryPositiveResistance;
    /**
     * 电池包负极对地绝缘电阻
     */
    private Integer batteryNegativeResistance;
    /**
     * SOC
     */
    private BigDecimal soc;
    /**
     * 总充电次数
     */
    private Integer chargeNumber;
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

    private String collectionTimeFormat;

    /**
     * 继电器状态
     */
    private String relayStatus;
    
    /**
     * 继电器状态
     */
    private String relayStatus7;
    
    /**
     * 继电器状态
     */
    private String relayStatus6;
    
    /**
     * 继电器状态
     */
    private String relayStatus5;
    
    /**
     * 继电器状态
     */
    private String relayStatus4;
    
    /**
     * 继电器状态
     */
    private String relayStatus3;
    
    /**
     * 继电器状态
     */
    private String relayStatus2;
    
    /**
     * 继电器状态
     */
    private String relayStatus1;
    
    /**
     * 继电器状态
     */
    private String relayStatus0;

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

    public Integer getPositiveRelayStatus() {
        return positiveRelayStatus;
    }

    public void setPositiveRelayStatus(Integer positiveRelayStatus) {
        this.positiveRelayStatus = positiveRelayStatus;
    }

    public Integer getNegativeRelayStatus() {
        return negativeRelayStatus;
    }

    public void setNegativeRelayStatus(Integer negativeRelayStatus) {
        this.negativeRelayStatus = negativeRelayStatus;
    }

    public Integer getForecastRelayStatus() {
        return forecastRelayStatus;
    }

    public void setForecastRelayStatus(Integer forecastRelayStatus) {
        this.forecastRelayStatus = forecastRelayStatus;
    }

    public Integer getBatteryNo() {
        return batteryNo;
    }

    public void setBatteryNo(Integer batteryNo) {
        this.batteryNo = batteryNo;
    }

    public Integer getBatteryNumber() {
        return batteryNumber;
    }

    public void setBatteryNumber(Integer batteryNumber) {
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

    public Integer getSysResistance() {
        return sysResistance;
    }

    public void setSysResistance(Integer sysResistance) {
        this.sysResistance = sysResistance;
    }

    public Integer getBatteryPositiveResistance() {
        return batteryPositiveResistance;
    }

    public void setBatteryPositiveResistance(Integer batteryPositiveResistance) {
        this.batteryPositiveResistance = batteryPositiveResistance;
    }

    public Integer getBatteryNegativeResistance() {
        return batteryNegativeResistance;
    }

    public void setBatteryNegativeResistance(Integer batteryNegativeResistance) {
        this.batteryNegativeResistance = batteryNegativeResistance;
    }

    public BigDecimal getSoc() {
        return soc;
    }

    public void setSoc(BigDecimal soc) {
        this.soc = soc;
    }

    public Integer getChargeNumber() {
        return chargeNumber;
    }

    public void setChargeNumber(Integer chargeNumber) {
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

    public String getCollectionTimeFormat() {
        return collectionTimeFormat;
    }

    public void setCollectionTimeFormat(String collectionTimeFormat) {
        this.collectionTimeFormat = collectionTimeFormat;
    }

    public String getRelayStatus() {
        return relayStatus;
    }

    public void setRelayStatus(String relayStatus) {
        this.relayStatus = relayStatus;
    }

    public String getRelayStatus7() {
        return relayStatus7;
    }

    public void setRelayStatus7(String relayStatus7) {
        this.relayStatus7 = relayStatus7;
    }

    public String getRelayStatus6() {
        return relayStatus6;
    }

    public void setRelayStatus6(String relayStatus6) {
        this.relayStatus6 = relayStatus6;
    }

    public String getRelayStatus5() {
        return relayStatus5;
    }

    public void setRelayStatus5(String relayStatus5) {
        this.relayStatus5 = relayStatus5;
    }

    public String getRelayStatus4() {
        return relayStatus4;
    }

    public void setRelayStatus4(String relayStatus4) {
        this.relayStatus4 = relayStatus4;
    }

    public String getRelayStatus3() {
        return relayStatus3;
    }

    public void setRelayStatus3(String relayStatus3) {
        this.relayStatus3 = relayStatus3;
    }

    public String getRelayStatus2() {
        return relayStatus2;
    }

    public void setRelayStatus2(String relayStatus2) {
        this.relayStatus2 = relayStatus2;
    }

    public String getRelayStatus1() {
        return relayStatus1;
    }

    public void setRelayStatus1(String relayStatus1) {
        this.relayStatus1 = relayStatus1;
    }

    public String getRelayStatus0() {
        return relayStatus0;
    }

    public void setRelayStatus0(String relayStatus0) {
        this.relayStatus0 = relayStatus0;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }
    
}