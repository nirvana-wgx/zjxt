package com.ctevs.vo;

import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * EquipmentPo Po
 * 
 * @author system
 */
public class EquipmentVo extends Vo {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -1550980129336356166L;
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
     * 状态0=注销,1=活跃,2=冻结
     */
    private String statename;
    /**
     * 创建用户
     */
    private BigInteger adduserid;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /**
     * 修改用户
     */
    private BigInteger updateuserid;
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
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