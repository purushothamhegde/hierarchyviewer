package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class FsItemRecordList implements Serializable{

	@Override
	public String toString() {
		return "FsItemRecordList [FsItemList=" + FsItemList + "]";
	}

	private List<FsItemRecord> FsItemList ;
	
	public List<FsItemRecord> getFsItemList() {
		return FsItemList;
	}

	public void setFsItemList(List<FsItemRecord> fsItemList) {
		FsItemList = fsItemList;
	}

	
}
