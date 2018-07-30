package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class MruHierarchyRecordList implements Serializable {
	
	private List<MruHierarchyRecord> mruRecordList ;
	private String HierarchyName ="";
	
	public String getHierarchyName() {
		return HierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		HierarchyName = hierarchyName;
	}

	public List<MruHierarchyRecord> getMruRecordList() {
		return mruRecordList;
	}

	public void setMruRecordList(List<MruHierarchyRecord> mruRecordList) {
		this.mruRecordList = mruRecordList;
	}

	

}
