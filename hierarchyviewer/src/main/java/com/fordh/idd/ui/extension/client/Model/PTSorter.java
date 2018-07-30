package com.fordh.idd.ui.extension.client.Model;

import java.util.Comparator;

public class PTSorter implements Comparator<ProductTreeRecord> {

	@Override
	public int compare(ProductTreeRecord arg0, ProductTreeRecord arg1) {
		return arg0.getOrder().compareTo(arg1.getOrder());
	}

}
