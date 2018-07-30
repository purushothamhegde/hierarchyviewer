package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class RepItemRecordList implements Serializable {

	
	private List <RepItemRecord> repitemlist;

	public List<RepItemRecord> getRepitemlist() {
		return repitemlist;
	}

	public void setRepitemlist(List<RepItemRecord> repitemlist) {
		this.repitemlist = repitemlist;
	}
	
}
