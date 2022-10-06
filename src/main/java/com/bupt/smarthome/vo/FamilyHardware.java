package com.bupt.smarthome.vo;

import java.util.Date;

/**
 * This class defines value object for hardware
 * @author wzy
 * @version 1.0
 */
public class FamilyHardware {
	// PK
	private int familyId; // int not null, Integer may be null
	private int hardwareId;
	// Hardware
	private String hwName;
	private String description;
	// Belonging
	private Date buyTime;
	private int damageSign;
	// Manufacturer
	private String name;
	private String factoryAddress;
	private int phoneNumber;
	// HwType
	private String hwTypeName;

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public int getHardwareId() {
		return hardwareId;
	}

	public void setHardwareId(int hardwareId) {
		this.hardwareId = hardwareId;
	}

	public String getHwName() {
		return hwName;
	}

	public void setHwName(String hwName) {
		this.hwName = hwName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public int getDamageSign() {
		return damageSign;
	}

	public void setDamageSign(int damageSign) {
		this.damageSign = damageSign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHwTypeName() {
		return hwTypeName;
	}

	public void setHwTypeName(String hwTypeName) {
		this.hwTypeName = hwTypeName;
	}

	@Override
	public String toString() {
		return "FamilyHardware{" +
				"familyId=" + familyId +
				", hardwareId=" + hardwareId +
				", hwName='" + hwName + '\'' +
				", description='" + description + '\'' +
				", buyTime=" + buyTime +
				", damageSign=" + damageSign +
				", name='" + name + '\'' +
				", factoryAddress='" + factoryAddress + '\'' +
				", phoneNumber=" + phoneNumber +
				", hwTypeName='" + hwTypeName + '\'' +
				'}';
	}
}
