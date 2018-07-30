package com.fordh.idd.ui.extension.client.service;

import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IMDMCustomServiceClient {

	void ViewMRUHeirarchy(String sHeirarchyName);
	void ViewORUHeirarchy(String sHeirarchyName);
	void ViewCCHeirarchy(String sHeirarchyName);
	void ViewGeoHeirarchy(String sHeirarchyName);
	void ViewProductTreeHeirarchy(String sHeirarchyName);
	void ViewBMCHeirarchy(String sHeirarchyName);
	void ViewFSItemHeirarchy(String sHeirarchyName);
	void ViewRepItemHeirarchy(String sHeirarchyName);
	
	
	void GetHeirarchyList();
}
