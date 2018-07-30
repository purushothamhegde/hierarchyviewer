package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class CostCenterHierarchyRecordList implements Serializable{

	private List<CostCenterHierarchyRecord> CCRecordList ;

	private String HierarchyName ="";
	
	public String getHierarchyName() {
		return HierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		HierarchyName = hierarchyName;
	}

	public List<CostCenterHierarchyRecord> getCCRecordList() {
		return CCRecordList;
	}

	public void setCCRecordList(List<CostCenterHierarchyRecord> cCRecordList) {
		CCRecordList = cCRecordList;
	}

	
	
	
	
}
