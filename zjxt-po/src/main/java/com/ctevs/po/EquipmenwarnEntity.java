package com.ctevs.po;

import java.math.BigInteger;
import java.util.Date;

/**
 * EquipmenwarnEntity BaseEntity
 * @author system
 */
public class EquipmenwarnEntity extends Entity {
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
     * 电池充电高温报警
     */
	private Integer batteryChargeHigh;	
	/**
     * 电池充电低温报警，
     */
	private Integer batteryChargeLow;	
	/**
     * 电池充电过流报警，
     */
	private Integer batteryChargeOut;	
	/**
     * 电池放电过流报警，
     */
	private Integer batteryDischargeOut;	
	/**
     * 电池放电高温报警，
     */
	private Integer batteryDischargeHigh;	
	/**
     * 电池放电低温报警，
     */
	private Integer batteryDischargeLow;	
	/**
     * 总压过压报警，
     */
	private Integer voltageHigh;	
	/**
     * 总压欠压报警，
     */
	private Integer voltageLow;	
	/**
     * 总压温差报警，
     */
	private Integer voltageDifference;	
	/**
     * 单体电池压差报警，
     */
	private Integer temperatureDifference;	
	/**
     * 单体电池过压报警
     */
	private Integer oneBatteryVoltageOut;	
	/**
     * 单体电池欠压报警
     */
	private Integer oneBatteryVoltageLow;	
	/**
     * SOC过低报警
     */
	private Integer socLow;	
	/**
     * SOC过高报警
     */
	private Integer socHigh;	
	/**
     * SOC跳变报警，
     */
	private Integer socJump;	
	/**
     * 绝缘报警，
     */
	private Integer resistance;	
	/**
     * DC-DC状态报警
     */
	private Integer dcStatus;	
	/**
     * 高压互锁状态报警，
     */
	private Integer highLock;	
	/**
     * 信息采集时间
     */
	private Date collectionTime;	
	/**
     * 创建时间
     */
	private Date addtime;	
    
	public BigInteger getId(){
		return id;
	}
	public void setId(BigInteger id){
		this.id = id;
	}
	public String getEquipmentId(){
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId){
		this.equipmentId = equipmentId;
	}
	public BigInteger getBatchNo(){
		return batchNo;
	}
	public void setBatchNo(BigInteger batchNo){
		this.batchNo = batchNo;
	}
	public Integer getBatteryChargeHigh(){
		return batteryChargeHigh;
	}
	public void setBatteryChargeHigh(Integer batteryChargeHigh){
		this.batteryChargeHigh = batteryChargeHigh;
	}
	public Integer getBatteryChargeLow(){
		return batteryChargeLow;
	}
	public void setBatteryChargeLow(Integer batteryChargeLow){
		this.batteryChargeLow = batteryChargeLow;
	}
	public Integer getBatteryChargeOut(){
		return batteryChargeOut;
	}
	public void setBatteryChargeOut(Integer batteryChargeOut){
		this.batteryChargeOut = batteryChargeOut;
	}
	public Integer getBatteryDischargeOut(){
		return batteryDischargeOut;
	}
	public void setBatteryDischargeOut(Integer batteryDischargeOut){
		this.batteryDischargeOut = batteryDischargeOut;
	}
	public Integer getBatteryDischargeHigh(){
		return batteryDischargeHigh;
	}
	public void setBatteryDischargeHigh(Integer batteryDischargeHigh){
		this.batteryDischargeHigh = batteryDischargeHigh;
	}
	public Integer getBatteryDischargeLow(){
		return batteryDischargeLow;
	}
	public void setBatteryDischargeLow(Integer batteryDischargeLow){
		this.batteryDischargeLow = batteryDischargeLow;
	}
	public Integer getVoltageHigh(){
		return voltageHigh;
	}
	public void setVoltageHigh(Integer voltageHigh){
		this.voltageHigh = voltageHigh;
	}
	public Integer getVoltageLow(){
		return voltageLow;
	}
	public void setVoltageLow(Integer voltageLow){
		this.voltageLow = voltageLow;
	}
	public Integer getVoltageDifference(){
		return voltageDifference;
	}
	public void setVoltageDifference(Integer voltageDifference){
		this.voltageDifference = voltageDifference;
	}
	public Integer getTemperatureDifference(){
		return temperatureDifference;
	}
	public void setTemperatureDifference(Integer temperatureDifference){
		this.temperatureDifference = temperatureDifference;
	}
	public Integer getOneBatteryVoltageOut(){
		return oneBatteryVoltageOut;
	}
	public void setOneBatteryVoltageOut(Integer oneBatteryVoltageOut){
		this.oneBatteryVoltageOut = oneBatteryVoltageOut;
	}
	public Integer getOneBatteryVoltageLow(){
		return oneBatteryVoltageLow;
	}
	public void setOneBatteryVoltageLow(Integer oneBatteryVoltageLow){
		this.oneBatteryVoltageLow = oneBatteryVoltageLow;
	}
	public Integer getSocLow(){
		return socLow;
	}
	public void setSocLow(Integer socLow){
		this.socLow = socLow;
	}
	public Integer getSocHigh(){
		return socHigh;
	}
	public void setSocHigh(Integer socHigh){
		this.socHigh = socHigh;
	}
	public Integer getSocJump(){
		return socJump;
	}
	public void setSocJump(Integer socJump){
		this.socJump = socJump;
	}
	public Integer getResistance(){
		return resistance;
	}
	public void setResistance(Integer resistance){
		this.resistance = resistance;
	}
	public Integer getDcStatus(){
		return dcStatus;
	}
	public void setDcStatus(Integer dcStatus){
		this.dcStatus = dcStatus;
	}
	public Integer getHighLock(){
		return highLock;
	}
	public void setHighLock(Integer highLock){
		this.highLock = highLock;
	}
	public Date getCollectionTime(){
		return collectionTime;
	}
	public void setCollectionTime(Date collectionTime){
		this.collectionTime = collectionTime;
	}
	public Date getAddtime(){
		return addtime;
	}
	public void setAddtime(Date addtime){
		this.addtime = addtime;
	}
}