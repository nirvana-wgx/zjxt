package com.ctevs.po;

import java.math.BigInteger;
import java.util.Date;

/**
 * EquipmentEntity BaseEntity
 * 
 * @author system
 */
public class EquipmentEntity extends Entity {
    /**
     * 序列
     */
    private BigInteger id;
    /**
     * 设备ID
     */
    private String equipmentId;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备组表
     */
    private Integer equipmentGroup;
    /**
     * 状态0=离线,1=在线,2=删除
     */
    private Integer state;
    /**
     * 创建用户
     */
    private BigInteger adduserid;
    /**
     * 创建时间
     */
    private Date addtime;
    /**
     * 修改用户
     */
    private BigInteger updateuserid;
    /**
     * 修改时间
     */
    private Date updatetime;
    /**
     * 软件版本号
     */
    private String equipmentSoftVersion;
    /**
     * 硬件版本号
     */
    private String equipmentHardwareVersion;

    /**
     * 最新采集时间
     */
    private Date lastCollectTime;

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

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getEquipmentGroup() {
        return equipmentGroup;
    }

    public void setEquipmentGroup(Integer equipmentGroup) {
        this.equipmentGroup = equipmentGroup;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigInteger getAdduserid() {
        return adduserid;
    }

    public void setAdduserid(BigInteger adduserid) {
        this.adduserid = adduserid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public BigInteger getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(BigInteger updateuserid) {
        this.updateuserid = updateuserid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getLastCollectTime() {
        return lastCollectTime;
    }

    public void setLastCollectTime(Date lastCollectTime) {
        this.lastCollectTime = lastCollectTime;
    }

    public String getEquipmentSoftVersion() {
        return equipmentSoftVersion;
    }

    public void setEquipmentSoftVersion(String equipmentSoftVersion) {
        this.equipmentSoftVersion = equipmentSoftVersion;
    }

    public String getEquipmentHardwareVersion() {
        return equipmentHardwareVersion;
    }

    public void setEquipmentHardwareVersion(String equipmentHardwareVersion) {
        this.equipmentHardwareVersion = equipmentHardwareVersion;
    }
    
}