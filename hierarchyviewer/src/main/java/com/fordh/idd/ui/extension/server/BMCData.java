package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.BMCRecord;
import com.fordh.idd.ui.extension.client.Model.BMCRecordList;
import com.fordh.idd.ui.extension.client.Model.BMCSorter;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.PTSorter;
import com.fordh.idd.ui.extension.client.Model.ProductTreeRecord;



public class BMCData {

	public static Logger logger = Logger.getLogger(BMCData.class.getName());
	private BMCRecordList Bmcrecordlist = new BMCRecordList();
	private List<BMCRecord> myBMCRecordList ;

	
	public String ExportBMCHeirarchy(String sHeirarchyName) {
		
		BMCRecordList bmclist = new BMCRecordList() ;
		
		String content="Sector,Business Group,Business,Description,Global,Hidden" + "\n";
		
	
		bmclist = ViewBMCHeirarchy(sHeirarchyName);
		
		List <BMCRecord> mybmcList = bmclist.getBmcRecordList();
		
		for (int i=0; i<= mybmcList.size()-1; i++)
		{
			BMCRecord bmc= mybmcList.get(i);
			content=content +   bmc.getSector().replace(",", ";") + "," ;
			content=content +   bmc.getBusinessGroup().replace(",", ";") + "," ;
			content=content +   bmc.getBusiness().replace(",", ";") + "," ;
			content=content +   bmc.getDescription().replace(",", ";") + "," ;
			content=content +   bmc.getGlobal().replace(",", ";") + "," ;
			content=content +   bmc.getHidden().replace(",", ";") + "\n" ;
			
		}
		return content;
	}
	
	public BMCRecordList ViewBMCHeirarchy(String sHeirarchyName) {
		
		
		
		logger.info("Inside Method :ViewBMCHeirarchy");
		String selArr[]= sHeirarchyName.split("-");
		ResultSet myrs ;
		myBMCRecordList = new ArrayList<BMCRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery =" SELECT ID,NVL(LD,' ')LD,LEVEL,PARENT,GLOBAL, HIDDEN  FROM(";
		sQuery = sQuery + " SELECT ID,TEXT LD,1 LEVEL,' ' PARENT,' ' GLOBAL,' ' HIDDEN FROM C_BMC_SECTOR WHERE C_BMC_SECTOR.HUB_STATE_IND=1 UNION ";
		sQuery = sQuery + " SELECT BG.ID, BG.TEXT LD, 2 LEVEL, SECTOR.ID PARENT,' ' GLOBAL,' ' HIDDEN FROM C_BMC_BUSINESS_GROUP BG,C_BMC_SECTOR SECTOR WHERE BG.GRP=SECTOR.ROWID_OBJECT AND BG.HUB_STATE_IND=1 UNION ";
		sQuery = sQuery + " SELECT BU.ID, BU.TEXT LD, 3 LEVEL, BG.ID PARENT ,NVL(BU.GLOBAL_1,' ') GLOBAL,NVL(BU.HIDDEN,' ') HIDDEN FROM C_BMC_BUSINESS BU, C_BMC_BUSINESS_GROUP BG WHERE BU.GRP=BG.ROWID_OBJECT AND BU.HUB_STATE_IND=1) ORDER BY LEVEL"; 
		
		logger.info("BMC Hierarchy Query::" + sQuery  );
	
	try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					BMCRecord Bmcrecord = new BMCRecord();
					if (myrs.getString(3).equals("1")) {
						Bmcrecord.setSector(myrs.getString(1));
						Bmcrecord.setBusinessGroup(" ");
						Bmcrecord.setBusiness(" ");
					}
					else if (myrs.getString(3).equals("2")) {
						Bmcrecord.setSector(" ");
						Bmcrecord.setBusinessGroup(myrs.getString(1));
						Bmcrecord.setBusiness(" ");
					}
					else if (myrs.getString(3).equals("3")) {
						Bmcrecord.setSector(" ");
						Bmcrecord.setBusinessGroup(" ");
						Bmcrecord.setBusiness(myrs.getString(1));
					}
					Bmcrecord.setId(myrs.getString(1));
					
					Bmcrecord.setLevel(myrs.getString(3));
					Bmcrecord.setParent(myrs.getString(4));
					Bmcrecord.setDescription(myrs.getString(2));

					Bmcrecord.setGlobal(myrs.getString(5));
					Bmcrecord.setHidden(myrs.getString(6));
					
					Bmcrecord.setOrder(Bmcrecord.getParent() + Bmcrecord.getId());
					myBMCRecordList.add(Bmcrecord);
					
				}
				
				GenerateOrder((ArrayList<BMCRecord>) myBMCRecordList);
				
				Collections.sort(myBMCRecordList, new BMCSorter());
				
				Bmcrecordlist.setBmcRecordList(myBMCRecordList);		
				
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
		logger.info("Completed the BMC Server Side::" );
		Bmcrecordlist.setHierarchyName(sHeirarchyName);
		return Bmcrecordlist ;	
	}
	

private void GenerateOrder(ArrayList<BMCRecord> mylist){
		for (int i=0; i < mylist.size(); i++)
		{
			
			BMCRecord bmcRecord = mylist.get(i);
			
			if (Integer.parseInt(bmcRecord.getLevel())>1) {
				
				String sParent=bmcRecord.getParent();
				
				String sOrder= GetOrder(mylist,sParent,bmcRecord.getLevel());
				
				bmcRecord.setOrder(sOrder + bmcRecord.getId());
			}
			
			
		}
		
		
	}
	

private String GetOrder(ArrayList<BMCRecord> mylist, String sParentNode, String level){
	
	for (int i=0; i < mylist.size(); i++)
	{
		
		BMCRecord bmcRecord = mylist.get(i);
		if (bmcRecord.getId().equals(sParentNode) && Integer.parseInt(bmcRecord.getLevel())== Integer.parseInt(level)-1 ){
			return bmcRecord.getOrder();
		
			}
		}
	
	return null;
	
}
}


