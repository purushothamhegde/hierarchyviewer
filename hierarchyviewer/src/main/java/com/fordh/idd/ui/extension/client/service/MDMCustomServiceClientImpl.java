package com.fordh.idd.ui.extension.client.service;

import com.fordh.idd.ui.extension.client.Fordh_HierarchyViewer;
import com.fordh.idd.ui.extension.client.Model.BMCRecordList;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchyList;
import com.fordh.idd.ui.extension.client.Model.FsItemRecordList;
import com.fordh.idd.ui.extension.client.Model.GeoRecordList;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.ProductTreeList;
import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class MDMCustomServiceClientImpl implements IMDMCustomServiceClient {
	private IMDMCustomServiceAsync service;
	
	private Fordh_HierarchyViewer maingui;
	
	public  MDMCustomServiceClientImpl(String url,Fordh_HierarchyViewer uicomp) {
		System.out.println("Posting to the following url " + url);
		this.service= GWT.create(IMDMCustomService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		this.maingui=uicomp;
	}
	

	

	@Override
	public void ViewMRUHeirarchy(String sHeirarchyName) {
		this.service.ViewMRUHeirarchy(sHeirarchyName, new DefaultCallback());
	}
	
	

	@Override
	public void GetHeirarchyList() {
		this.service.GetHeirarchyList(new DefaultCallback());
	}

	@Override
	public void ViewORUHeirarchy(String sHeirarchyName) {
		this.service.ViewORUHeirarchy(sHeirarchyName, new DefaultCallback());
		
	}

	@Override
	public void ViewCCHeirarchy(String sHeirarchyName) {
		this.service.ViewCCHeirarchy(sHeirarchyName, new DefaultCallback());
		
	}

	@Override
	public void ViewGeoHeirarchy(String sHeirarchyName) {
		this.service.ViewGeoHeirarchy(sHeirarchyName, new DefaultCallback());
		
	}

	@Override
	public void ViewProductTreeHeirarchy(String sHeirarchyName) {
		// TODO Auto-generated method stub
		this.service.ViewProductTreeHeirarchy(sHeirarchyName, new DefaultCallback());
	}

	@Override
	public void ViewBMCHeirarchy(String sHeirarchyName) {
		
		this.service.ViewBMCHeirarchy(sHeirarchyName, new DefaultCallback());
		
	}


	@Override
	public void ViewFSItemHeirarchy(String sHeirarchyName) {
		// TODO Auto-generated method stub
		this.service.ViewFSItemHeirarchy(sHeirarchyName, new DefaultCallback());
		
	}

	@Override
	public void ViewRepItemHeirarchy(String sHeirarchyName) {
		this.service.ViewRepItemHeirarchy(sHeirarchyName, new DefaultCallback());
		
	}

	
	
	private class DefaultCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error!!!");
			
		}

		@Override
		public void onSuccess(Object result) {
			
			if (result instanceof MruHierarchyRecordList) {
				MruHierarchyRecordList mylist= (MruHierarchyRecordList)result;
				maingui.openviewHeirarchieswithMRUData(mylist);
				
			}
			
			else if (result instanceof FORDHHierarchyList) {
				FORDHHierarchyList mylist=  (FORDHHierarchyList)result;
				maingui.popuateHierarchydropdowns(mylist);
				
			}
			
			else if (result instanceof OruHierarchyRecordList) {
				OruHierarchyRecordList mylist= (OruHierarchyRecordList)result;
				maingui.openviewHeirarchieswithORUData(mylist);
				
			}
			
			else if (result instanceof CostCenterHierarchyRecordList) {
				CostCenterHierarchyRecordList mylist= (CostCenterHierarchyRecordList)result;
				maingui.openviewHeirarchieswithCCData(mylist);
				
			}
			
			else if (result instanceof GeoRecordList) {
				GeoRecordList mylist= (GeoRecordList)result;
				maingui.openviewHeirarchieswithGeoData(mylist);
				
			}
			
			else if (result instanceof ProductTreeList) {
				ProductTreeList mylist= (ProductTreeList)result;
				maingui.openviewHeirarchieswithPTData(mylist);
				
			}
			
			else if (result instanceof BMCRecordList) {
				BMCRecordList mylist= (BMCRecordList)result;
				maingui.openviewHeirarchieswithBMCData(mylist);
				
			}		
			
			else if (result instanceof FsItemRecordList) {
				
				FsItemRecordList mylist =(FsItemRecordList)result;
				maingui.openviewHeirarchieswithFsItemData(mylist);
				
			}
			else if (result instanceof RepItemRecordList) {
				
				RepItemRecordList mylist =(RepItemRecordList)result;
				maingui.openviewHeirarchieswithRepItemData(mylist);
				
			}
			
		}
		
		
	}







}
