package com.fordh.idd.ui.extension.client.Model;

import java.util.Comparator;

public class CCSorter implements Comparator<CostCenterHierarchyRecord> {


	@Override
	public int compare(CostCenterHierarchyRecord arg0, CostCenterHierarchyRecord arg1) {
		// TODO Auto-generated method stub
		return arg0.getOrder().compareTo(arg1.getOrder());
	}
	
}
