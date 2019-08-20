package com.ctevs.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * EquipmenbatteryProbePo Po
 * 
 * @author system
 */
public class EquipmenbatteryProbeVo extends Vo {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 8249095477004841938L;
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
     * 可充电储能温度探针个数（Nt）
     */
    private Integer probeNumber;
    /**
     * 单体探针编号
     */
    private Integer oneProbeNo;
    /**
     * 单体探针温度
     */
    private BigDecimal oneProbeTemperature;
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

    public Integer getProbeNumber() {
        return probeNumber;
    }

    public void setProbeNumber(Integer probeNumber) {
        this.probeNumber = probeNumber;
    }

    public Integer getOneProbeNo() {
        return oneProbeNo;
    }

    public void setOneProbeNo(Integer oneProbeNo) {
        this.oneProbeNo = oneProbeNo;
    }

    public BigDecimal getOneProbeTemperature() {
        return oneProbeTemperature;
    }

    public void setOneProbeTemperature(BigDecimal oneProbeTemperature) {
        this.oneProbeTemperature = oneProbeTemperature;
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