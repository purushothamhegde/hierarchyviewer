package com.fordh.idd.ui.extension.client.Model;

import java.util.Comparator;

/* This class is no longer used as the sorting is now done using Line Numbers directly.
 * 
 */
public class RepItemSorter implements Comparator<RepItemRecord>  {

	@Override
	public int compare(RepItemRecord arg0, RepItemRecord arg1) {
		// TODO Auto-generated method stub
		return arg0.getOrder().compareTo(arg1.getOrder());
		
	}

}
