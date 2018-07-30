package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class ProductTreeRecord implements Serializable {

	private String Id="";
	private String LD="";
	private String Level="";
	private String Parent="";
	private String ValueSegment="";
	private String Risk="";
	private String Order="";

	private String Sector;
	private String ProductDivision;
	private String BusinessGroup;
	private String Business;
	private String MainArticleGroup;
	private String ArticleGroup;

	
	public String getSector() {
		return Sector;
	}
	public void setSector(String sector) {
		Sector = sector;
	}
	public String getProductDivision() {
		return ProductDivision;
	}
	public void setProductDivision(String productDivision) {
		ProductDivision = productDivision;
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
	public String getMainArticleGroup() {
		return MainArticleGroup;
	}
	public void setMainArticleGroup(String mainArticleGroup) {
		MainArticleGroup = mainArticleGroup;
	}
	public String getArticleGroup() {
		return ArticleGroup;
	}
	public void setArticleGroup(String articleGroup) {
		ArticleGroup = articleGroup;
	}
	
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getLD() {
		return LD;
	}
	public void setLD(String lD) {
		LD = lD;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getParent() {
		return Parent;
	}
	public void setParent(String parent) {
		Parent = parent;
	}
	public String getValueSegment() {
		return ValueSegment;
	}
	public void setValueSegment(String valueSegment) {
		ValueSegment = valueSegment;
	}
	public String getRisk() {
		return Risk;
	}
	public void setRisk(String risk) {
		Risk = risk;
	}
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
		
	
}
