package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class ProductTreeList implements Serializable{

	

	private List<ProductTreeRecord> productTreeRecordList ;

	private String HierarchyName ="";
	
	public String getHierarchyName() {
		return HierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		HierarchyName = hierarchyName;
	}

	public List<ProductTreeRecord> getProductTreeRecordList() {
		return productTreeRecordList;
	}

	public void setProductTreeRecordList(List<ProductTreeRecord> productTreeRecordList) {
		this.productTreeRecordList = productTreeRecordList;
	}
	
	
	
}
