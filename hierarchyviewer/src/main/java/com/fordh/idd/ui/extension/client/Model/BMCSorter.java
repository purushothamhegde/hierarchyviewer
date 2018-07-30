package com.fordh.idd.ui.extension.client.Model;

import java.util.Comparator;

public class BMCSorter implements Comparator<BMCRecord> {

	@Override
	public int compare(BMCRecord arg0, BMCRecord arg1) {
		return arg0.getOrder().compareTo(arg1.getOrder());
	}

}
