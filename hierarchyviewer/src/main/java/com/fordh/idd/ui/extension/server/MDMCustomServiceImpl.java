package com.fordh.idd.ui.extension.server;

import com.fordh.idd.ui.extension.client.Model.BMCRecordList;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchy;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchyList;
import com.fordh.idd.ui.extension.client.Model.FsItemRecordList;
import com.fordh.idd.ui.extension.client.Model.GeoRecordList;
import com.fordh.idd.ui.extension.client.Model.LegalEntity;
import com.fordh.idd.ui.extension.client.Model.LegalEntityList;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.ProductTreeList;
import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;
import com.fordh.idd.ui.extension.client.service.IMDMCustomService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.fordh.idd.ui.extension.shared.FieldVerifier;

import javax.xml.soap.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MDMCustomServiceImpl extends RemoteServiceServlet implements IMDMCustomService {
	public static Logger logger = Logger.getLogger(MDMCustomServiceImpl.class.getName());
	public static String fileToRead = "WEB-INF/IDDUIExtension.properties";
		

	private List<MruHierarchyRecord> myMRURecordList ;
	private List<FORDHHierarchy>myfordhHierarchyList ;
	
	
	
	private MruHierarchyRecordList mrurecordlist = new MruHierarchyRecordList();
	private FORDHHierarchyList fordhhierarchylist= new FORDHHierarchyList();
	
	

	

	@Override
	public MruHierarchyRecordList ViewMRUHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewMRUHeirarchy");
		MRUHeirarchyData myHierarchyData = new MRUHeirarchyData();
		return myHierarchyData.ViewMRUHeirarchy(sHeirarchyName) ;	
		}


	@Override
	public FORDHHierarchyList GetHeirarchyList() {
		logger.info("Inside Method :GetHeirarchyList");
		FORDHHierarchyData myFORDHHierarchyData = new FORDHHierarchyData();
		return myFORDHHierarchyData.GetHeirarchyList() ;
	}



	@Override
	public OruHierarchyRecordList ViewORUHeirarchy(String sHeirarchyName) {
		// TODO Auto-generated method stub
		logger.info("Inside Method :ViewORUHeirarchy");
		ORUHeirarchyData myHierarchyData = new ORUHeirarchyData();
		return myHierarchyData.ViewORUHeirarchy(sHeirarchyName) ;	
	}



	@Override
	public CostCenterHierarchyRecordList ViewCCHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewCCHeirarchy");
		CCHeirarchyData myHierarchyData = new CCHeirarchyData();
		return myHierarchyData.ViewCCHeirarchy(sHeirarchyName) ;	
	}

	@Override
	public GeoRecordList ViewGeoHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewGeoHeirarchy");
		GeoHierarchyData myHierarchyData = new GeoHierarchyData();
		return myHierarchyData.ViewGeoHeirarchy(sHeirarchyName) ;	
	}



	@Override
	public ProductTreeList ViewProductTreeHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewProductTreeHeirarchy");
		ProductTreeData myHierarchyData = new ProductTreeData();
		return myHierarchyData.ViewProductTreeHeirarchy(sHeirarchyName) ;	
	}



	@Override
	public BMCRecordList ViewBMCHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewBMCHeirarchy");
		BMCData myBMCData = new BMCData();
		return  myBMCData.ViewBMCHeirarchy(sHeirarchyName);
		
	}


	@Override
	public FsItemRecordList ViewFSItemHeirarchy(String sHeirarchyName) {
		// TODO Auto-generated method stub
		logger.info("Inside Method :ViewFSItemHeirarchy");
		FsItemData myFsItemData= new FsItemData();
		return myFsItemData.ViewFsItemHierarchy(sHeirarchyName);
	}


	@Override
	public RepItemRecordList ViewRepItemHeirarchy(String sHeirarchyName) {
		// TODO Auto-generated method stub

		logger.info("Inside Method :ViewRepItemHeirarchy");
		ReportingItemData myRepItemData= new ReportingItemData();
		return myRepItemData.ViewRepItemHierarchy(sHeirarchyName);
	}


		
}
