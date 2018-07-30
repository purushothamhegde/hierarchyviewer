package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class BMCRecord implements Serializable{

private String Sector;
private String BusinessGroup;
private String Business;
private String Level;
private String Description;
private String Parent;
private String Order;
private String Id;
private String Global;
private String Hidden;


public String getGlobal() {
	return Global;
}
public void setGlobal(String global) {
	Global = global;
}
public String getHidden() {
	return Hidden;
}
public void setHidden(String hidden) {
	Hidden = hidden;
}



public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getOrder() {
	return Order;
}
public void setOrder(String order) {
	Order = order;
}
public String getSector() {
	return Sector;
}
public void setSector(String sector) {
	Sector = sector;
}
public String getBusinessGroup() {
	return BusinessGroup;
}
public void setBusinessGroup(String businessGroup) {
	BusinessGroup = businessGroup;
}
public String getBusiness() {
	return Business;
}
public void setBusiness(String business) {
	Business = business;
}
public String getLevel() {
	return Level;
}
public void setLevel(String level) {
	Level = level;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
public String getParent() {
	return Parent;
}
public void setParent(String parent) {
	Parent = parent;
}


	
	
}
