package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class FsItemRecord implements Serializable {

	@Override
	public String toString() {
		return "FsItemRecord [MediumDescription=" + MediumDescription + ", LongDescription=" + LongDescription
				+ ", LifeCycle=" + LifeCycle + ", BlockedforPosting=" + BlockedforPosting + ", Level=" + Level
				+ ", Code=" + Code + ", Order=" + Order + ", Parent=" + Parent + ", myLevel=" + myLevel
				+ ", DisplayCode=" + DisplayCode + "]";
	}
	private String MediumDescription;
	private String LongDescription;
	private String LifeCycle;
	private String BlockedforPosting;
	private String Level;
	private String Code;
	private String Order;
	private String Parent;
	private Integer myLevel;
	
	private String DisplayCode;
	
	

	
	public String getDisplayCode() {
		return DisplayCode;
	}
	public void setDisplayCode(String displayCode) {
		DisplayCode = displayCode;
	}
	public Integer getMyLevel() {
		return myLevel;
	}
	public void setMyLevel(Integer myLevel) {
		this.myLevel = myLevel;
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public String getParent() {
		return Parent;
	}
	public void setParent(String parent) {
		Parent = parent;
	}
	
	public String getMediumDescription() {
		return MediumDescription;
	}
	public void setMediumDescription(String mediumDescription) {
		MediumDescription = mediumDescription;
	}
	public String getLongDescription() {
		return LongDescription;
	}
	public void setLongDescription(String longDescription) {
		LongDescription = longDescription;
	}
	public String getLifeCycle() {
		return LifeCycle;
	}
	public void setLifeCycle(String lifeCycle) {
		LifeCycle = lifeCycle;
	}
	public String getBlockedforPosting() {
		return BlockedforPosting;
	}
	public void setBlockedforPosting(String blockedforPosting) {
		BlockedforPosting = blockedforPosting;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	
	
	
}
