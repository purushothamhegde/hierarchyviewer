package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.OruHierarchyRecordList;

public class ORUHeirarchyData {

	public static Logger logger = Logger.getLogger(ORUHeirarchyData.class.getName());
	private OruHierarchyRecordList orurecordlist = new OruHierarchyRecordList();
	private List<OruHierarchyRecord> myORURecordList ;
	
	
	public OruHierarchyRecordList ViewORUHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewORUHeirarchy");
		String selArr[]= sHeirarchyName.split("-");
		
		ResultSet myrs ;
		myORURecordList = new ArrayList<OruHierarchyRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery ="SELECT HIERARCHY_LEVEL,LINE_NUMBER,PARENT_CODE,CODE,DESCRIPTION,LC ,TYPE FROM (";
		sQuery = sQuery + " SELECT HIERARCHY_CODE,REL_TYPE_CODE,LINE_NUMBER,HIERARCHY_LEVEL,C_ORG_REP_UNIT_GROUP.ID PARENT_CODE ,' ' CODE ,C_ORG_REP_UNIT_GROUP.TEXT_3 DESCRIPTION,Life_Cycle_Phase LC,'ORUGRP' TYPE ";
		sQuery = sQuery + " FROM C_ORU_GROUP_GROUPING,C_ORG_REP_UNIT_GROUP";
		sQuery = sQuery + " WHERE C_ORU_GROUP_GROUPING.HUB_STATE_IND=1 AND C_ORG_REP_UNIT_GROUP.HUB_STATE_IND=1 AND C_ORU_GROUP_GROUPING.PARENT_2=C_ORG_REP_UNIT_GROUP.ROWID_OBJECT AND HIERARCHY_CODE='" + selArr[1] +"'"; 
		sQuery = sQuery + " UNION";
		sQuery = sQuery + " SELECT HIERARCHY_CODE,REL_TYPE_CODE,LINE_NUMBER,HIERARCHY_LEVEL,ORU_GRP.ID PARENT_CODE,ORU.ID CODE,ORU.TEXT_3 DESCRIPTION, ORU.Life_Cycle_Phase LC,'ORU' TYPE ";
		sQuery = sQuery + " FROM C_ORG_REP_UNIT_GROUPING,C_ORG_REP_UNIT_GROUP ORU_GRP,C_ORG_REP_UNIT ORU ";
		sQuery = sQuery + " WHERE C_ORG_REP_UNIT_GROUPING.HUB_STATE_IND=1 AND ORU_GRP.HUB_STATE_IND=1 AND ORU.HUB_STATE_IND=1 AND C_ORG_REP_UNIT_GROUPING.PARENT_1=ORU_GRP.ROWID_OBJECT AND C_ORG_REP_UNIT_GROUPING.PARENT_2=ORU.ROWID_OBJECT AND ";
		sQuery = sQuery + " HIERARCHY_CODE ='" + selArr[1] +"'";
		sQuery = sQuery + " )ORDER BY LINE_NUMBER";
		
		logger.info("ORU Hierarchy Query::" + sQuery  );
				
		try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					OruHierarchyRecord orurecord = new OruHierarchyRecord();

					orurecord.setH_LEVEL(myrs.getString(1));
					orurecord.setLINE_NUMBER("" + myrs.getString(2));
					orurecord.setPARENT_ORU("" + StringUtils.leftPad("",Integer.parseInt(myrs.getString(1)),"*") + myrs.getString(3));
					orurecord.setCHILD_ORU("" + myrs.getString(4));
					orurecord.setMEDIUM_DESCRIPTION("" + myrs.getString(5));
					orurecord.setLC("" + myrs.getString(6));
					orurecord.setRECORD_TYPE("" + myrs.getString(7));
					myORURecordList.add(orurecord);
					
			}
				orurecordlist.setOruRecordList(myORURecordList);		
				
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
		orurecordlist.setHierarchyName(sHeirarchyName);
		return orurecordlist ;	
		}

	

	public String ExportORUHeirarchy(String sHeirarchyName) 
	{
		OruHierarchyRecordList orulist = new OruHierarchyRecordList();
		String content="Life Cycle,Line Number,Level,ORU Group Code,ORU Code,Medium Description" + "\n";
		orulist = ViewORUHeirarchy(sHeirarchyName);
		
		List <OruHierarchyRecord> myORUList = orulist.getOruRecordList();
		
		for (int i=0; i<= myORUList.size()-1; i++)
		{
			OruHierarchyRecord oru= myORUList.get(i);
			content=content +   oru.getLC().replace(",", ";") + "," ;
			content=content +   oru.getLINE_NUMBER().replace(",", ";") + "," ;
			content=content +   oru.getH_LEVEL().replace(",", ";") + "," ;
			content=content +   oru.getPARENT_ORU().replace(",", ";") + "," ;
			content=content +   oru.getCHILD_ORU().replace(",", ";") + "," ;
			content=content +   oru.getMEDIUM_DESCRIPTION().replace(",", ";") +"\n";
			
		}
		return content;
	}
	
}
