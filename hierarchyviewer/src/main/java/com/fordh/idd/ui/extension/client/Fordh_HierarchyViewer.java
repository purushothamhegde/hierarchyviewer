package com.fordh.idd.ui.extension.client;

import java.util.List;

import com.fordh.idd.ui.extension.client.Model.BMCRecord;
import com.fordh.idd.ui.extension.client.Model.BMCRecordList;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchy;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchyList;
import com.fordh.idd.ui.extension.client.Model.FsItemRecord;
import com.fordh.idd.ui.extension.client.Model.FsItemRecordList;
import com.fordh.idd.ui.extension.client.Model.GeoRecord;
import com.fordh.idd.ui.extension.client.Model.GeoRecordList;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.ProductTreeList;
import com.fordh.idd.ui.extension.client.Model.ProductTreeRecord;
import com.fordh.idd.ui.extension.client.Model.RepItemRecord;
import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;
import com.fordh.idd.ui.extension.client.service.MDMCustomServiceClientImpl;
import com.fordh.idd.ui.extension.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Fordh_HierarchyViewer implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";


	
	
	private VerticalPanel vPanel = new VerticalPanel();
	private Label lblResult;
	private HTML myhtml ;
	private TextBox HierarchySelected;
	private FormPanel myForm;
	private Hidden html; 
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	private HorizontalPanel hPanel2 = new HorizontalPanel();
	private ListBox cmbhllist;

	private MDMCustomServiceClientImpl serviceImpl;
	


	
	public void onModuleLoad() {
		
		
		serviceImpl = new MDMCustomServiceClientImpl(GWT.getModuleBaseURL()+ "mdmcustomservice", this);
		
		this.vPanel.setWidth("100%");
		this.vPanel.setBorderWidth(1);
	
		this.cmbhllist= new ListBox();
		this.hPanel1.add(cmbhllist);
		cmbhllist.addChangeHandler(new cmbChangeHandler());
		
		serviceImpl.GetHeirarchyList();

		/*
		Button btnviewHeirarchy = new Button("Populate Hierarchy List!!");
		this.hPanel1.add(btnviewHeirarchy);
		btnviewHeirarchy.addClickHandler(new btnviewHeirarchyClickHandler());
*/
			
		
		this.vPanel.add(hPanel1);
		
		Button btnviewMRUHeirarchy = new Button("View Hierarchy!!");
		this.hPanel2.add(btnviewMRUHeirarchy);
		btnviewMRUHeirarchy.addClickHandler(new btnviewHeirarchyDataClickHandler());

		
		Button btnExportMRUHeirarchy = new Button("Export Hierarchy!!");
		btnExportMRUHeirarchy.addClickHandler(new btnExportHeirarchyClickHandler());
		
		this.hPanel2.add(btnExportMRUHeirarchy);
		
		
		this.vPanel.add(hPanel2);
		
		
		myForm= new FormPanel();
		myForm.setVisible(false);
		this.HierarchySelected=new TextBox();
		HierarchySelected.setName("txthierarchyname");
		myForm.add(HierarchySelected);
		this.vPanel.add(myForm);
		
		this.myhtml= new HTML();
		this.vPanel.add(myhtml);
		RootPanel.get().add(vPanel);

		
	}
	
	
	
	public void SetHierarchyOutput(FORDHHierarchyList myHierarchylist) {

		List <FORDHHierarchy> myList = myHierarchylist.getHeirarchyList();
		
		for (int i=0; i<= myList.size()-1; i++)
		{
			FORDHHierarchy myoption= myList.get(i);
			this.cmbhllist.addItem(myoption.getType() +"-"+ myoption.getCode() +"-" + myoption.getName() ,myoption.getType());
		}
	
	}
	
	public void SetMRUHierarchyOutput(MruHierarchyRecordList strResult) {
		this.HierarchySelected.setValue(strResult.getHierarchyName());
			
		List <MruHierarchyRecord> myMRUList = strResult.getMruRecordList();
		String content="<table id='results'><tr><th>Line Number</th><th>Level</th><th>RecordType</th><th>Code</th>";
		content=content + "<th>L1</th>"+ "<th>L2</th>"+ "<th>L3</th>"+ "<th>L4</th>"+ "<th>L5</th>"+ "<th>L6</th>"+ "<th>L7</th>"+ "<th>L8</th>"+ "<th>L9</th>"+ "<th>L10</th>"+ "<th>L11</th>"+ "<th>L12</th>"+ "<th>L13</th>"+ "<th>L14</th>"+ "<th>L15</th>";
		content=content +" <th>ShortDescription</th><th>Medium Description</th><th>Long Description </th>";
		content=content +" <th>LC</th><th>Product Detail Level</th><th>Planning Level</th><th>BMC Business</th><th>Business Model</th><th>Planning Relevant</th><th>PPLANRELN</th><th>PPLANRELS</th><th>Sector</th><th>Product Division</th></tr>";
		
		for (int i=0; i<= myMRUList.size()-1; i++)
		{
			MruHierarchyRecord mru= myMRUList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  mru.getLINE_NUMBER() + "</td>" ;
			content=content + "<td>" +  mru.getH_LEVEL() + "</td>" ;
			content=content + "<td>" +  mru.getRECORD_TYPE() + "</td>" ;
			content=content + "<td>" +  mru.getCODE() + "</td>" ;
			content=content + "<td>" +  mru.getL1() + "</td>" ;
			content=content + "<td>" +  mru.getL2() + "</td>" ;
			content=content + "<td>" +  mru.getL3() + "</td>" ;
			content=content + "<td>" +  mru.getL4() + "</td>" ;
			content=content + "<td>" +  mru.getL5() + "</td>" ;
			content=content + "<td>" +  mru.getL6() + "</td>" ;
			content=content + "<td>" +  mru.getL7() + "</td>" ;
			content=content + "<td>" +  mru.getL8() + "</td>" ;
			content=content + "<td>" +  mru.getL9() + "</td>" ;
			content=content + "<td>" +  mru.getL10() + "</td>" ;
			content=content + "<td>" +  mru.getL11() + "</td>" ;
			content=content + "<td>" +  mru.getL12() + "</td>" ;
			content=content + "<td>" +  mru.getL13() + "</td>" ;
			content=content + "<td>" +  mru.getL14() + "</td>" ;
			content=content + "<td>" +  mru.getL15() + "</td>" ;
			content=content + "<td>" +  mru.getSHORT_DESCRIPTION()+ "</td>" ;
			content=content + "<td>" +  mru.getMEDIUM_DESCRIPTION() + "</td>" ;
			content=content + "<td>" +  mru.getLONG_DESCRIPTION()+ "</td>" ;
			content=content + "<td>" +  mru.getLC() + "</td>" ;
			content=content + "<td>" +  mru.getPRODUCT_DETAIL_LEVEL() + "</td>" ;
			content=content + "<td>" +  mru.getPLANNING_LEVEL() + "</td>" ;
			content=content + "<td>" +  mru.getBMC_BUSINESS() + "</td>" ;
			content=content + "<td>" +  mru.getBUSINESS_MODEL() + "</td>" ;
			content=content + "<td>" +  mru.getPLANNING_RELEVANT() + "</td>" ;
			content=content + "<td>" +  mru.getPPLANRELN() + "</td>" ;
			content=content + "<td>" +  mru.getPPLANRELS() + "</td>" ;
			content=content + "<td>" +  mru.getSECTOR() + "</td>" ;
			content=content + "<td>" +  mru.getPRODUCT_DIVISION() + "</td>" ;
			
			content=content +"</tr>";
		}
		
		this.myhtml.setHTML(content);
		
	}

	
	public void SetCCHierarchyOutput(CostCenterHierarchyRecordList strResult) {
		this.HierarchySelected.setValue(strResult.getHierarchyName());
		List <CostCenterHierarchyRecord> myCCList = strResult.getCCRecordList();
		String content="<table id='results'><tr><th>Life Cycle</th> <th>Node Id</th><th>Node Name</th><th>Parent Node Name</th><th>Level</th><th>L1</th><th>L2</th><th>L3</th><th>L4</th><th>L5</th><th>L6</th>";
		content=content +"<th>Info Object</th><th>Linked Node</th><th>Short Description</th><th>Medium Description</th><th>Language</th><th>MRU</th><th>MAG</th><th>Company Code</th><th>ORU</th></tr>";
		
	
		for (int i=0; i<= myCCList.size()-1; i++)
		{
			CostCenterHierarchyRecord cc= myCCList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  cc.getLIFE_CYCLE_PHASE() + "</td>" ;
			content=content + "<td>" +  cc.getNODEID() + "</td>" ;
			content=content + "<td>" +  cc.getNODENAME() + "</td>" ;
			content=content + "<td>" +  cc.getPARENTNODENAME() + "</td>" ;
			content=content + "<td>" +  cc.getLEVEL() + "</td>" ;
			content=content + "<td>" +  cc.getL1() + "</td>" ;
			content=content + "<td>" +  cc.getL2() + "</td>" ;
			content=content + "<td>" +  cc.getL3() + "</td>" ;
			content=content + "<td>" +  cc.getL4() + "</td>" ;
			content=content + "<td>" +  cc.getL5() + "</td>" ;
			content=content + "<td>" +  cc.getL6() + "</td>" ;
			content=content + "<td>" +  cc.getINFOOBJECT() + "</td>" ;
			content=content + "<td>" +  cc.getLINKEDNODE() + "</td>" ;
			content=content + "<td>" +  cc.getSHORTDESC() + "</td>" ;
			content=content + "<td>" +  cc.getMEDIUMDESC() + "</td>" ;
			content=content + "<td>" +  cc.getLANGUAGE() + "</td>" ;
			content=content + "<td>" +  cc.getMRU() + "</td>" ;
			content=content + "<td>" +  cc.getMAG() + "</td>" ;
			content=content + "<td>" +  cc.getCOMPANY_CODE() + "</td>" ;
			content=content + "<td>" +  cc.getORU() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);
		
	}
	
	
	public void SetORUHierarchyOutput(OruHierarchyRecordList strResult) {
		this.HierarchySelected.setValue(strResult.getHierarchyName());
		List <OruHierarchyRecord> myORUList = strResult.getOruRecordList();
		String content="<table id='results'><tr><th>Life Cycle</th> <th>Line Number</th><th>Level</th><th>ORU Group Code</th><th>ORU Code</th><th>Medium Description</th></tr>";
		
		for (int i=0; i<= myORUList.size()-1; i++)
		{
			OruHierarchyRecord oru= myORUList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  oru.getLC() + "</td>" ;
			content=content + "<td>" +  oru.getLINE_NUMBER() + "</td>" ;
			content=content + "<td>" +  oru.getH_LEVEL() + "</td>" ;
			content=content + "<td>" +  oru.getPARENT_ORU() + "</td>" ;
			content=content + "<td>" +  oru.getCHILD_ORU() + "</td>" ;
			content=content + "<td>" +  oru.getMEDIUM_DESCRIPTION() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);
		
	}

	public void SetGeoHierarchyOutput(GeoRecordList strResult) {
		this.HierarchySelected.setValue(strResult.getHierarchyName());
		List <GeoRecord> myGeoList = strResult.getGeorecordlist();
		
		String content="<table id='results'><tr><th>Life Cycle</th> <th>Code</th><th>Level</th><th>L1</th><th>L2</th><th>L3</th><th>L4</th><th>L5</th><th>Full Name</th><th>Parent Details</th></tr>";
		
		for (int i=0; i<= myGeoList.size()-1; i++)
		{
			GeoRecord geo= myGeoList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  geo.getLC() + "</td>" ;
			content=content + "<td>" +  geo.getCODE() + "</td>" ;
			content=content + "<td>" +  geo.getHIERARCHY_LEVEL() + "</td>" ;
			content=content + "<td>" +  geo.getL1() + "</td>" ;
			content=content + "<td>" +  geo.getL2() + "</td>" ;
			content=content + "<td>" +  geo.getL3() + "</td>" ;
			content=content + "<td>" +  geo.getL4() + "</td>" ;
			content=content + "<td>" +  geo.getL5() + "</td>" ;
			content=content + "<td>" +  geo.getFullName() + "</td>" ;
			content=content + "<td>" +  geo.getParentDetails() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);		
		
	}

	public void SetPTHierarchyOutput(ProductTreeList strResult) {
		
		this.HierarchySelected.setValue(strResult.getHierarchyName());
		List <ProductTreeRecord> myPTList = strResult.getProductTreeRecordList();
		
		String content="<table id='results'><tr><th>Sector</th><th>Product Division</th><th>Business Group</th><th>Business</th><th>Main Article Group</th><th>Article Group</th><th>Description</th><th>Level</th><th>Value Segment</th><th>Risk</th></tr>";
		
		for (int i=0; i<= myPTList.size()-1; i++)
		{
			ProductTreeRecord pt= myPTList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  pt.getSector() + "</td>" ;
			content=content + "<td>" +  pt.getProductDivision() + "</td>" ;
			content=content + "<td>" +  pt.getBusinessGroup() + "</td>" ;
			content=content + "<td>" +  pt.getBusiness() + "</td>" ;
			content=content + "<td>" +  pt.getMainArticleGroup() + "</td>" ;
			content=content + "<td>" +  pt.getArticleGroup() + "</td>" ;
			content=content + "<td>" +  pt.getLD() + "</td>" ;
			content=content + "<td>" +  pt.getLevel() + "</td>" ;
			content=content + "<td>" +  pt.getValueSegment() + "</td>" ;
			content=content + "<td>" +  pt.getRisk() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);		
		
	}

	

public void SetRepItemHierarchyOutput(RepItemRecordList repitemlist) {
		
		List<RepItemRecord> myRepList= repitemlist.getRepitemlist();
		
		String content="<table id='results'><tr><th>Hierarchy Code</th><th>Description</th><th>Planning</th><th>Level</th><th>Rep.Item</th><th>Hierarchy Parent</th></tr>";
		
		for (int i=0; i<= myRepList.size()-1; i++)
		{
			RepItemRecord repitem= myRepList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  repitem.getHierarchy_code() + "</td>" ;
			content=content + "<td>" +  repitem.getDescription() + "</td>" ;
			content=content + "<td>" +  repitem.getPlanning_Level() + "</td>" ;
			content=content + "<td>" +  repitem.getLevel() + "</td>" ;
			content=content + "<td>" +  repitem.getDisplayCode() + "</td>" ;
			content=content + "<td>" +  repitem.getParent() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);		
	}


	public void SetFsItemHierarchyOutput(FsItemRecordList fsitemlist) {
		
		List<FsItemRecord> myFSList= fsitemlist.getFsItemList();
		
		String content="<table id='results'><tr><th>Short Description</th><th>LC</th><th>BP</th><th>Level</th><th>Item</th><th>Long Description</th></tr>";
		
		for (int i=0; i<= myFSList.size()-1; i++)
		{
			FsItemRecord fsitem= myFSList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  fsitem.getMediumDescription() + "</td>" ;
			content=content + "<td>" +  fsitem.getLifeCycle() + "</td>" ;
			content=content + "<td>" +  fsitem.getBlockedforPosting() + "</td>" ;
			content=content + "<td>" +  fsitem.getLevel() + "</td>" ;
			content=content + "<td>" +  fsitem.getDisplayCode()+ "</td>" ;
			content=content + "<td>" +  fsitem.getLongDescription() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);		
	}

	public void SetBMCHierarchyOutput(BMCRecordList strResult) {
		
		this.HierarchySelected.setValue(strResult.getHierarchyName());
		List <BMCRecord> myBMCList = strResult.getBmcRecordList();
		
		String content="<table id='results'><tr><th>Sector</th><th>Business Group</th><th>Business </th><th>Description</th><th>Global</th><th>Hidden</th><th>Level</th></tr>";
		
		for (int i=0; i<= myBMCList.size()-1; i++)
		{
			BMCRecord bmc= myBMCList.get(i);
			content=content +"<tr>";
			content=content + "<td>" +  bmc.getSector() + "</td>" ;
			content=content + "<td>" +  bmc.getBusinessGroup() + "</td>" ;
			content=content + "<td>" +  bmc.getBusiness() + "</td>" ;
			content=content + "<td>" +  bmc.getDescription() + "</td>" ;
			content=content + "<td>" +  bmc.getGlobal() + "</td>" ;
			content=content + "<td>" +  bmc.getHidden() + "</td>" ;
			content=content + "<td>" +  bmc.getLevel() + "</td>" ;
			content=content +"</tr>";
		}

		this.myhtml.setHTML(content);		
		
	}

	/*
	private class btnviewHeirarchyClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			serviceImpl.GetHeirarchyList();
		}
		
	}
*/
	private class btnExportHeirarchyClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			
			
			if (HierarchySelected.getText().equals("")) {
				HierarchySelected.setText(cmbhllist.getSelectedItemText());
			}
			//html.setValue(cmbhllist.getSelectedItemText());
			myForm.setAction(GWT.getModuleBaseURL() + "excel");
			// formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
			myForm.setMethod(FormPanel.METHOD_POST);
			myForm.submit();
		}
	}

	
	private class btnviewHeirarchyDataClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			String sOptionSelected = cmbhllist.getSelectedItemText();
			HierarchySelected.setText(sOptionSelected);
			
			String selArr[]= sOptionSelected.split("-");
						
			if (selArr[0].equals("MRU")){
				serviceImpl.ViewMRUHeirarchy(sOptionSelected);
			}
			else if (selArr[0].equals("ORU")){
				serviceImpl.ViewORUHeirarchy(sOptionSelected);
			}	
			else if (selArr[0].equals("CCH")){
				serviceImpl.ViewCCHeirarchy(sOptionSelected);
			}
			else if (selArr[0].equals("GEO")){
				serviceImpl.ViewGeoHeirarchy(sOptionSelected);
			}
			else if (selArr[0].equals("PRODUCT_TREE")){
				serviceImpl.ViewProductTreeHeirarchy(sOptionSelected);
			}
			else if (selArr[0].equals("BMC")){
				serviceImpl.ViewBMCHeirarchy(sOptionSelected);
			}
			else if (selArr[0].equals("FSITEM")){
				serviceImpl.ViewFSItemHeirarchy(sOptionSelected);
			}
			
			else if (selArr[0].equals("REPITEM")){
				serviceImpl.ViewRepItemHeirarchy(sOptionSelected);
			}
			
			
		}
		
	}

	private class cmbChangeHandler implements ChangeHandler{

		@Override
		public void onChange(ChangeEvent event) {
			// TODO Auto-generated method stub
			HierarchySelected.setText(cmbhllist.getSelectedItemText());
			
		}
		
	}

	public void openviewHeirarchieswithMRUData(MruHierarchyRecordList mrulist) {
		SetMRUHierarchyOutput(mrulist);
	}
	
	
	public void openviewHeirarchieswithORUData(OruHierarchyRecordList orulist) {
		SetORUHierarchyOutput(orulist);
	}
	
	public void openviewHeirarchieswithCCData(CostCenterHierarchyRecordList cclist) {
		SetCCHierarchyOutput(cclist);
	}
	
	public void openviewHeirarchieswithGeoData(GeoRecordList geolist) {
		SetGeoHierarchyOutput(geolist);
	}
	
	
	public void openviewHeirarchieswithPTData(ProductTreeList ptlist) {
		SetPTHierarchyOutput(ptlist);
	}
	
	public void openviewHeirarchieswithBMCData(BMCRecordList bmclist) {
		SetBMCHierarchyOutput(bmclist);
	}
	
	public void openviewHeirarchieswithFsItemData(FsItemRecordList fsitemlist) {
		SetFsItemHierarchyOutput(fsitemlist);
	}
	

	public void openviewHeirarchieswithRepItemData(RepItemRecordList repitemlist) {
		SetRepItemHierarchyOutput(repitemlist);
	}

	public void popuateHierarchydropdowns(FORDHHierarchyList Hierarchylist) {
		SetHierarchyOutput(Hierarchylist);
		}
	
	
	}
