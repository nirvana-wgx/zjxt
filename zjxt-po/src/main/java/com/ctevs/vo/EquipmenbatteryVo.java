package com.ctevs.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EquipmenbatteryVo extends Vo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -6607828411639916359L;
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
     * 可充电储能温度探针个数（Nt）
     */
    private Integer probeNumber;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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

    public Integer getProbeNumber() {
        return probeNumber;
    }

    public void setProbeNumber(Integer probeNumber) {
        this.probeNumber = probeNumber;
    }

    public BigInteger getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(BigInteger batchNo) {
        this.batchNo = batchNo;
    }

}
