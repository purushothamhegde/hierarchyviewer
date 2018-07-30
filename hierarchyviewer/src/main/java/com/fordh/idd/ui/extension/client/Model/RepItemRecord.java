package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class RepItemRecord implements Serializable {
	private String Hierarchy_code=""; 
	private  String Description="";
	private  String Planning_Level ="";
	private  String Rep_Item ="";
	private  String Parent ="";
	private  String Lighting ="";
	private  String Lifestyle ="";
	private  String Healthcare ="";
	private  String CorporateControl=""; 
	private  String PhilipsHealthTech ="";
	private  String PMA ="";
	private String Order="";
	private String Level;
	private String DisplayCode;
	
	private String LineNumber="";
	
	

	
	public String getLineNumber() {
		return LineNumber;
	}
	public void setLineNumber(String lineNumber) {
		LineNumber = lineNumber;
	}
	public String getDisplayCode() {
		return DisplayCode;
	}
	public void setDisplayCode(String displayCode) {
		DisplayCode = displayCode;
	}

	
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public String getHierarchy_code() {
		return Hierarchy_code;
	}
	public void setHierarchy_code(String hierarchy_code) {
		Hierarchy_code = hierarchy_code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPlanning_Level() {
		return Planning_Level;
	}
	public void setPlanning_Level(String planning_Level) {
		Planning_Level = planning_Level;
	}
	public String getRep_Item() {
		return Rep_Item;
	}
	public void setRep_Item(String rep_Item) {
		Rep_Item = rep_Item;
	}
	public String getParent() {
		return Parent;
	}
	public void setParent(String parent) {
		Parent = parent;
	}
	public String getLighting() {
		return Lighting;
	}
	public void setLighting(String lighting) {
		Lighting = lighting;
	}
	public String getLifestyle() {
		return Lifestyle;
	}
	public void setLifestyle(String lifestyle) {
		Lifestyle = lifestyle;
	}
	public String getHealthcare() {
		return Healthcare;
	}
	public void setHealthcare(String healthcare) {
		Healthcare = healthcare;
	}
	public String getCorporateControl() {
		return CorporateControl;
	}
	public void setCorporateControl(String corporateControl) {
		CorporateControl = corporateControl;
	}
	public String getPhilipsHealthTech() {
		return PhilipsHealthTech;
	}
	public void setPhilipsHealthTech(String philipsHealthTech) {
		PhilipsHealthTech = philipsHealthTech;
	}
	public String getPMA() {
		return PMA;
	}
	public void setPMA(String pMA) {
		PMA = pMA;
	}
	
}
