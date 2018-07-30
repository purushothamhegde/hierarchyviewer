package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class GeoRecordList implements Serializable {

	private List<GeoRecord> georecordlist ;

	private String HierarchyName ="";
	
	public String getHierarchyName() {
		return HierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		HierarchyName = hierarchyName;
	}

	public List<GeoRecord> getGeorecordlist() {
		return georecordlist;
	}

	public void setGeorecordlist(List<GeoRecord> georecordlist) {
		this.georecordlist = georecordlist;
	}

}
