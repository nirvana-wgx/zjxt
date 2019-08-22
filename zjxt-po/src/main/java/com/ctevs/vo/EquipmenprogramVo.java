package com.ctevs.vo;

import java.math.BigInteger;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.ctevs.common.beans.Vo;

/**
 * EquipmenprogramPo Po
 * 
 * @author system
 */
@SuppressWarnings("serial")
public class EquipmenprogramVo extends Vo {
    /**
     * 序列
     */
    private BigInteger id;
    /**
     * 软件版本号
     */
    private String equipmentSoftVersion;
    /**
     * 硬件版本号
     */
    private String equipmentHardwareVersion;
    /**
     * 程序名称
     */
    private String equipmentProgramName;
    /**
     * 程序指令
     */
    private String equipmentProgramContent;
    /**
     * 状态0-无效,1-有效
     */
    private Integer state;
    /**
     * 创建用户
     */
    private BigInteger adduserid;
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

    public String getEquipmentProgramName() {
        return equipmentProgramName;
    }

    public void setEquipmentProgramName(String equipmentProgramName) {
        this.equipmentProgramName = equipmentProgramName;
    }

    public String getEquipmentProgramContent() {
        return equipmentProgramContent;
    }

    public void setEquipmentProgramContent(String equipmentProgramContent) {
        this.equipmentProgramContent = equipmentProgramContent;
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
}