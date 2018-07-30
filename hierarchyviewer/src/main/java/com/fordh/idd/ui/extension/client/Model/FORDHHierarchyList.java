package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class FORDHHierarchyList  implements Serializable {

	private List<FORDHHierarchy> HeirarchyList ;

	public List<FORDHHierarchy> getHeirarchyList() {
		return HeirarchyList;
	}

	public void setHeirarchyList(List<FORDHHierarchy> heirarchyList) {
		HeirarchyList = heirarchyList;
	}
	
}
