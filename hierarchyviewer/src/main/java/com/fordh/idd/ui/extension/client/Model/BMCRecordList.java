package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class BMCRecordList implements Serializable{

	private  List<BMCRecord> BmcRecordList ;

	public List<BMCRecord> getBmcRecordList() {
		return BmcRecordList;
	}

	public void setBmcRecordList(List<BMCRecord> bmcRecordList) {
		BmcRecordList = bmcRecordList;
	}

	private String HierarchyName ="";
	
	public String getHierarchyName() {
		return HierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		HierarchyName = hierarchyName;
	}

	
}
