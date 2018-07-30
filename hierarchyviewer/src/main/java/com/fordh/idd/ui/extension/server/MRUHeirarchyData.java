package com.fordh.idd.ui.extension.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;

public class MRUHeirarchyData {

	public static Logger logger = Logger.getLogger(MRUHeirarchyData.class.getName());
	private MruHierarchyRecordList mrurecordlist = new MruHierarchyRecordList();
	private List<MruHierarchyRecord> myMRURecordList ;
	
	
	public MruHierarchyRecordList ViewMRUHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewMRUHeirarchy");
		String selArr[]= sHeirarchyName.split("-");
		
		ResultSet myrs ;
		myMRURecordList = new ArrayList<MruHierarchyRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery ="SELECT COALESCE(HIERARCHY_LEVEL,' ') H_LEVEL,COALESCE(LINE_NUMBER,' ') LINE_NUMBER,PARENT_1 PARENT_MRU,PARENT_2 CHILD_MRU,CHILD_GROUP.ID CODE,COALESCE(CHILD_GROUP.TEXT, ' ') Long_Description,COALESCE(CHILD_GROUP.TEXT_2, ' ') Short_Description,COALESCE(CHILD_GROUP.TEXT_3 ,' ') Medium_Description,'MRUGRP' RECORD_TYPE, COALESCE(CHILD_GROUP.LIFE_CYCLE_PHASE, ' ') LC,COALESCE(CHILD_GROUP.PRODUCT_DETAIL_LEVEL,' ') Product_Detail_Level,";
		sQuery = sQuery + " COALESCE(CHILD_GROUP.PLANNING_LEVEL,' ') Planning_Level,COALESCE(CHILD_GROUP.GRP,' ') BMC_Business,COALESCE(CHILD_GROUP.BUSINESS_MODEL,' ') Business_Model,COALESCE(CHILD_GROUP.PLANNING_RELEVANT ,' ') Planning_Relevant,COALESCE(CHILD_GROUP.PPLANRELN,' ') PPLANRELN,COALESCE(CHILD_GROUP.PPLANRELS,' ') PPLANRELS,";
		sQuery = sQuery + " COALESCE(CHILD_GROUP.CATEGORY ,' ')SECTOR,' ' PRODUCT_DIVISION ";
		sQuery = sQuery + " FROM "; 
		sQuery = sQuery + " C_MAN_REP_UNIT_GRP_GRPNG ,C_MAN_REPORTING_UNIT_GRP CHILD_GROUP ";
		sQuery = sQuery + " WHERE ";
		sQuery = sQuery + " C_MAN_REP_UNIT_GRP_GRPNG.HUB_STATE_IND =1 AND CHILD_GROUP.HUB_STATE_IND=1 AND CHILD_GROUP.ROWID_OBJECT = C_MAN_REP_UNIT_GRP_GRPNG.PARENT_2 AND";
		sQuery = sQuery + " C_MAN_REP_UNIT_GRP_GRPNG.HIERARCHY_CODE='" + selArr[1] + "'";
		//sQuery = sQuery + " AND LINE_NUMBER <28100";
		sQuery = sQuery + " UNION ";
		sQuery = sQuery + " SELECT COALESCE(HIERARCHY_LEVEL,' ') H_LEVEL,COALESCE(LINE_NUMBER,' ') LINE_NUMBER,PARENT_1 PARENT_MRU,PARENT_2 CHILD_MRU,CHILD_GROUP.ID CODE,COALESCE(CHILD_GROUP.TEXT,' ') Long_Description,COALESCE(CHILD_GROUP.TEXT_2,' ') Short_Description,COALESCE(CHILD_GROUP.TEXT_3,' ') Medium_Description,'MRU' RECORD_TYPE,";
		sQuery = sQuery + " COALESCE(CHILD_GROUP.LIFE_CYCLE_PHASE,' ') LC,COALESCE(CHILD_GROUP.PRODUCT_DETAIL_LEVEL,' ') PRODUCT_DETAIL_LEVEL,COALESCE(CHILD_GROUP.PLANNING_LEVEL,' ') PLANNING_LEVEL,COALESCE(CHILD_GROUP.GRP,' ') BMC_Business,COALESCE(CHILD_GROUP.BUSINESS_MODEL ,' ')BUSINESS_MODEL,COALESCE(CHILD_GROUP.PLANNING_RELEVANT,' ') PLANNING_RELEVANT,";
		sQuery = sQuery + " COALESCE(CHILD_GROUP.PPLANRELN,' ') PPLANRELN,COALESCE(CHILD_GROUP.PPLANRELS,' ') PPLANRELS,COALESCE(CHILD_GROUP.PRODUCT_DIVISION_VERSION,' ') SECTOR,COALESCE(CHILD_GROUP.CATEGORY,' ') PRODUCT_DIVISION";
		sQuery = sQuery + " FROM ";
		sQuery = sQuery + " C_MAN_REP_UNIT_GROUPING ,C_MAN_REPORTING_UNIT CHILD_GROUP";
		sQuery = sQuery + " WHERE ";
		sQuery = sQuery + " C_MAN_REP_UNIT_GROUPING.HUB_STATE_IND =1 AND CHILD_GROUP.HUB_STATE_IND=1 AND CHILD_GROUP.ROWID_OBJECT = C_MAN_REP_UNIT_GROUPING.PARENT_2 AND ";
		sQuery = sQuery + " C_MAN_REP_UNIT_GROUPING.HIERARCHY_CODE='" + selArr[1] + "'";
		
		//sQuery = sQuery + " AND LINE_NUMBER <28100";
		
		sQuery = sQuery + " ORDER BY LINE_NUMBER";

		logger.info("MRU Hierarchy Query::" + sQuery  );
				
		try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					MruHierarchyRecord mrurecord = new MruHierarchyRecord();

					mrurecord.setH_LEVEL(myrs.getString(1));
					mrurecord.setLINE_NUMBER("" + myrs.getString(2));
					mrurecord.setPARENT_MRU("" + myrs.getString(3));
					mrurecord.setCHILD_MRU("" + myrs.getString(4));
					mrurecord.setCODE("" + myrs.getString(5));
					mrurecord.setLONG_DESCRIPTION("" + myrs.getString(6));
					mrurecord.setSHORT_DESCRIPTION("" + myrs.getString(7));
					mrurecord.setMEDIUM_DESCRIPTION("" + myrs.getString(8));
					mrurecord.setRECORD_TYPE("" + myrs.getString(9));
					mrurecord.setLC("" + myrs.getString(10));
					mrurecord.setPRODUCT_DETAIL_LEVEL("" + myrs.getString(11));
					mrurecord.setPLANNING_LEVEL("" + myrs.getString(12));
					mrurecord.setBMC_BUSINESS("" + myrs.getString(13));
					mrurecord.setBUSINESS_MODEL("" + myrs.getString(14));
					mrurecord.setPLANNING_RELEVANT("" + myrs.getString(15));
					mrurecord.setPPLANRELN("" + myrs.getString(16));
					mrurecord.setPPLANRELS("" + myrs.getString(17));
					mrurecord.setSECTOR("" + myrs.getString(18));
					mrurecord.setPRODUCT_DIVISION("" + myrs.getString(19));
					
					if (myrs.getString(1).equals("1")) {
						mrurecord.setL1("" + myrs.getString(5));
						
					}
					else if (myrs.getString(1).equals("2")) {
						mrurecord.setL2("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("3")) {
						mrurecord.setL3("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("4")) {
						mrurecord.setL4("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("5")) {
						mrurecord.setL5("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("6")) {
						mrurecord.setL6("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("7")) {
						mrurecord.setL7("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("8")) {
						mrurecord.setL8("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("9")) {
						mrurecord.setL9("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("10")) {
						mrurecord.setL10("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("11")) {
						mrurecord.setL11("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("12")) {
						mrurecord.setL12("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("13")) {
						mrurecord.setL13("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("14")) {
						mrurecord.setL14("" + myrs.getString(5));
					}
					else if (myrs.getString(1).equals("15")) {
						mrurecord.setL15("" + myrs.getString(5));
					}
					
					myMRURecordList.add(mrurecord);
					
			}
				mrurecordlist.setMruRecordList(myMRURecordList);		
				
			}
			finally {
		
				myrs.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mrurecordlist.setHierarchyName(sHeirarchyName);
		return mrurecordlist ;	
		}


	public String ExportMRUHeirarchy(String sHeirarchyName) 
	{
		MruHierarchyRecordList mrulist = new MruHierarchyRecordList();
		
		String content="Line Number,Level,RecordType,Code,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,Short Description, Medium Description,Long Description, Life Cycle, Product Detail Level,Planning Level, BMC Business,Business Model,Planning Relevant,PPLANRELN,PPLANRELS,Sector,Product Division" + "\n";
		
		mrulist = ViewMRUHeirarchy(sHeirarchyName);
		
		List <MruHierarchyRecord> myMRUList = mrulist.getMruRecordList();
		
		for (int i=0; i<= myMRUList.size()-1; i++)
		{
			MruHierarchyRecord mru= myMRUList.get(i);
			content=content +   mru.getLINE_NUMBER().replace(",", ";") + "," ;
			content=content +   mru.getH_LEVEL().replace(",", ";") + "," ;
			content=content +   mru.getRECORD_TYPE().replace(",", ";") + "," ;
			content=content +   mru.getCODE().replace(",", ";") + "," ;
			content=content +   mru.getL1().replace(",", ";") + "," ;
			content=content +   mru.getL2().replace(",", ";") + "," ;
			content=content +   mru.getL3().replace(",", ";") + "," ;
			content=content +   mru.getL4().replace(",", ";") + "," ;
			content=content +   mru.getL5().replace(",", ";") + "," ;
			content=content +   mru.getL6().replace(",", ";") + "," ;
			content=content +   mru.getL7().replace(",", ";") + "," ;
			content=content +   mru.getL8().replace(",", ";") + "," ;
			content=content +   mru.getL9().replace(",", ";") + "," ;
			content=content +   mru.getL10().replace(",", ";") + "," ;
			content=content +   mru.getL11().replace(",", ";") + "," ;
			content=content +   mru.getL12().replace(",", ";") + "," ;
			content=content +   mru.getL13().replace(",", ";") + "," ;
			content=content +   mru.getL14().replace(",", ";") + "," ;
			content=content +   mru.getL15().replace(",", ";") + "," ;
			content=content +   mru.getSHORT_DESCRIPTION().replace(",", ";")+ "," ;
			content=content +   mru.getMEDIUM_DESCRIPTION().replace(",", ";") + "," ;
			content=content +   mru.getLONG_DESCRIPTION().replace(",", ";")+ "," ;
			content=content +   mru.getLC().replace(",", ";") + "," ;
			content=content +   mru.getPRODUCT_DETAIL_LEVEL().replace(",", ";") + "," ;
			content=content +   mru.getPLANNING_LEVEL().replace(",", ";") + "," ;
			content=content +   mru.getBMC_BUSINESS().replace(",", ";") + "," ;
			content=content +   mru.getBUSINESS_MODEL().replace(",", ";") + "," ;
			content=content +   mru.getPLANNING_RELEVANT().replace(",", ";") + "," ;
			content=content +   mru.getPPLANRELN().replace(",", ";") + "," ;
			content=content +   mru.getPPLANRELS().replace(",", ";") + "," ;
			content=content +   mru.getSECTOR().replace(",", ";") + "," ;
			content=content +   mru.getPRODUCT_DIVISION().replace(",", ";") + "\n" ;
		}
		return content;
	}
	
	
}
