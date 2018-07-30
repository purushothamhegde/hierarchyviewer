package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.BMCRecord;
import com.fordh.idd.ui.extension.client.Model.BMCRecordList;
import com.fordh.idd.ui.extension.client.Model.BMCSorter;
import com.fordh.idd.ui.extension.client.Model.CCSorter;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.FSSorter;
import com.fordh.idd.ui.extension.client.Model.FsItemRecord;
import com.fordh.idd.ui.extension.client.Model.FsItemRecordList;
import com.fordh.idd.ui.extension.client.Model.RepItemRecord;
import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;

import java_cup.shift_action;

public class FsItemData {

	public static Logger logger = Logger.getLogger(FsItemData.class.getName());
	private FsItemRecordList fsitemrecordlist = new FsItemRecordList();
	private List<FsItemRecord> myfstemRecordList ;

	
	
	
public FsItemRecordList ViewFsItemHierarchy(String sHeirarchyName) {
		
		
		
		logger.info("Inside Method :ViewFsItemHierarchy");
		String selArr[]= sHeirarchyName.split("-");
		ResultSet myrs ;
		myfstemRecordList = new ArrayList<FsItemRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery =" SELECT FSITEM.TEXT_2 MEDIUM_DESC, FSITEM.LIFE_CYCLE_PHASE, FSITEM.BLOCKED_FOR_POSTING BP,FSITEMGRP.HIERARCHY_LEVEL,FSITEM.ID, FSITEM.TEXT LONG_DESC, FSITEM_PARENT.ID  ";
		sQuery = sQuery + " FROM C_FIN_STATEMENT_ITEM FSITEM,C_FS_ITEM_HIERARCHY FSITEMHIERARCHY,C_FS_ITEM_GROUPING FSITEMGRP,C_FIN_STATEMENT_ITEM FSITEM_PARENT ";
		sQuery = sQuery + " WHERE FSITEM.HUB_STATE_IND=1 AND  FSITEMHIERARCHY.HUB_STATE_IND=1 AND FSITEMGRP.HUB_STATE_IND=1 AND FSITEM_PARENT.HUB_STATE_IND=1 AND FSITEM_PARENT.ROWID_OBJECT = FSITEMGRP.PARENT_1 AND FSITEM.ROWID_OBJECT = FSITEMGRP.PARENT_2 AND FSITEMGRP.HIERARCHY_CODE= FSITEMHIERARCHY.ID AND ";
		sQuery = sQuery + " FSITEMHIERARCHY.ID ='"  + selArr[1]  + "'  ORDER BY FSITEM.ID"; 
		
		logger.info("FsItem Hierarchy Query::" + sQuery  );
	
	try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					FsItemRecord myfsitemrecord = new FsItemRecord();
					
						myfsitemrecord.setMediumDescription(myrs.getString(1));
						myfsitemrecord.setLifeCycle(myrs.getString(2));
						myfsitemrecord.setBlockedforPosting(myrs.getString(3));
						myfsitemrecord.setLevel(myrs.getString(4));
						myfsitemrecord.setCode(myrs.getString(5));
						myfsitemrecord.setLongDescription(myrs.getString(6));
						myfsitemrecord.setParent(myrs.getString(7));
						myfsitemrecord.setOrder( myrs.getString(5));
						myfsitemrecord.setMyLevel(1);
						myfstemRecordList.add(myfsitemrecord);
					
				}
				
				
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
		
//	logger.info("Before Generate Order");
		GenerateOrder((ArrayList<FsItemRecord>) myfstemRecordList);
		
//		logger.info("Before Sort");
		Collections.sort(myfstemRecordList, new FSSorter());
		fsitemrecordlist.setFsItemList(myfstemRecordList);
		
	//	logger.info("Final Data " + fsitemrecordlist.toString());
		return fsitemrecordlist ;	
	}



private String GetParent(ArrayList<FsItemRecord> mylist, String sNode){
	
	for (int i=0; i < mylist.size(); i++)
	{
		
		FsItemRecord fsRecord = mylist.get(i);
		if (fsRecord.getCode().equals(sNode)){
			return fsRecord.getParent();
		
			}
		}
	
	return " ";
}



private void GenerateOrder(ArrayList<FsItemRecord> mylist){
	
	int templevel=0;
	
	logger.info("Number of Records in FSItem " + mylist.size()) ;	
	
	for (int i=0; i < mylist.size(); i++)
	{
		
		FsItemRecord fsRecord = mylist.get(i);
		String sParent=fsRecord.getParent();
		String sOrder=sParent + fsRecord.getCode();
		
		while(!sParent.equals(" ")) {
			templevel=templevel+1;
			sParent=GetParent(mylist, sParent);
			sOrder= sParent +sOrder;
			//logger.info("sOrder: " + sOrder) ;
			
		}
		
		//logger.info("Order for Id: " +fsRecord.getCode() +" is " + sOrder + " and level is " + templevel) ;	
		
		fsRecord.setOrder(sOrder);
		fsRecord.setMyLevel(templevel);
		fsRecord.setLevel(Integer.toString(templevel));
		
		fsRecord.setDisplayCode(fsRecord.getCode());
		
		templevel=0;
		sOrder="";
		
	//	logger.info("Compeleted setting Order for record no " + fsRecord.getCode()) ;
		
	}
}

public String ExportFSItemHeirarchy(String sHeirarchyName) 
{
	FsItemRecordList fsitemlist = new FsItemRecordList();
	
	String content="Description,LifeCycle,Blocked for Posting,Level,FS Item Code,Long Description,Parent FS Item " + "\n";
	
	fsitemlist = ViewFsItemHierarchy(sHeirarchyName);
	
	List <FsItemRecord> myfsitemList = fsitemlist.getFsItemList();
	
	for (int i=0; i<= myfsitemList.size()-1; i++)
	{
		FsItemRecord fsi= myfsitemList.get(i);
		content=content +   fsi.getMediumDescription().replace(",",";") + "," ;
		content=content +   fsi.getLifeCycle().replace(",", ";") + "," ;
		content=content +   fsi.getBlockedforPosting().replace(",", ";") + "," ;
		content=content +   fsi.getMyLevel()+ "," ;
		content=content +   fsi.getDisplayCode().replace(",",";") + "," ;
		content=content +   fsi.getLongDescription().replace(",",";") + "," ;
		content=content +   fsi.getParent().replace(",", ";") + "\n" ;
	}
	return content;
}
	
}
