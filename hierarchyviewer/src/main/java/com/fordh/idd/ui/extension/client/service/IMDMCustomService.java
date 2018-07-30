package com.fordh.idd.ui.extension.client.service;

import com.fordh.idd.ui.extension.client.Model.BMCRecordList;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchyList;
import com.fordh.idd.ui.extension.client.Model.FsItemRecordList;
import com.fordh.idd.ui.extension.client.Model.GeoRecordList;
import com.fordh.idd.ui.extension.client.Model.LegalEntityList;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.ProductTreeList;
import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mdmcustomservice")
public interface IMDMCustomService extends RemoteService {

	FORDHHierarchyList GetHeirarchyList();
	
	MruHierarchyRecordList ViewMRUHeirarchy(String sHeirarchyName );
	OruHierarchyRecordList ViewORUHeirarchy(String sHeirarchyName);
	CostCenterHierarchyRecordList ViewCCHeirarchy(String sHeirarchyName);
	GeoRecordList ViewGeoHeirarchy(String sHeirarchyName);	
	ProductTreeList ViewProductTreeHeirarchy(String sHeirarchyName);
	BMCRecordList ViewBMCHeirarchy(String sHeirarchyName);
	FsItemRecordList ViewFSItemHeirarchy(String sHeirarchyName);
	RepItemRecordList ViewRepItemHeirarchy(String sHeirarchyName);
	
}
