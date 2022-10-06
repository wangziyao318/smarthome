package com.bupt.smarthome.vo;

/**
 * This class defines value object for user and family
 * Use alt+insert to generate constructor, setter&getter, and toString
 * @author wzy
 * @version 1.0
 */
public class UserFamily {
	private String username;
	private String uPassword;
	private String fName;
	private String lName;
	private int familyId;
	private String fAddress; // foreign key only exists in mysql data to assists select operation

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getfAddress() {
		return fAddress;
	}

	public void setfAddress(String fAddress) {
		this.fAddress = fAddress;
	}

	@Override
	public String toString() {
		return "UserFamily{" +
				"username='" + username + '\'' +
				", uPassword='" + uPassword + '\'' +
				", fName='" + fName + '\'' +
				", lName='" + lName + '\'' +
				", familyId=" + familyId +
				", fAddress='" + fAddress + '\'' +
				'}';
	}
}
