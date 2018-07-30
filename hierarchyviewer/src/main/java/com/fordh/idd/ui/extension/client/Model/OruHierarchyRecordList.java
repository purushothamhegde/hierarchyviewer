package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class OruHierarchyRecordList implements Serializable{

	private List<OruHierarchyRecord> oruRecordList ;

	private String HierarchyName ="";
	
	public String getHierarchyName() {
		return HierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		HierarchyName = hierarchyName;
	}

	public List<OruHierarchyRecord> getOruRecordList() {
		return oruRecordList;
	}

	public void setOruRecordList(List<OruHierarchyRecord> oruRecordList) {
		this.oruRecordList = oruRecordList;
	}
	
	
	
	
}
