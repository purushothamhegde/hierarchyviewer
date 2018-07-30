package com.fordh.idd.ui.extension.client.Model;

import java.util.Comparator;

public class FSSorter implements Comparator<FsItemRecord> {

	@Override
	public int compare(FsItemRecord arg0, FsItemRecord arg1) {
		// TODO Auto-generated method stub
		return arg0.getOrder().compareTo(arg1.getOrder());
		
	}

}
