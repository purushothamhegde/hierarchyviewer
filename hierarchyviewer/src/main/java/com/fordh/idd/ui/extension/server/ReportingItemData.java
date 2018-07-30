package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.FSSorter;
import com.fordh.idd.ui.extension.client.Model.FsItemRecord;
import com.fordh.idd.ui.extension.client.Model.FsItemRecordList;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.RepItemRecord;
import com.fordh.idd.ui.extension.client.Model.RepItemRecordList;
import com.fordh.idd.ui.extension.client.Model.RepItemSorter;

public class ReportingItemData {

	public static Logger logger = Logger.getLogger(ReportingItemData.class.getName());
	private RepItemRecordList repitemrecordlist = new RepItemRecordList();
	private List<RepItemRecord> myrepitemRecordList ;

	
public RepItemRecordList ViewRepItemHierarchy(String sHeirarchyName) {
		
		
		
		logger.info("Inside Method :ViewRepItemHierarchy");
		String selArr[]= sHeirarchyName.split("-");
		ResultSet myrs ;
		myrepitemRecordList = new ArrayList<RepItemRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery =" SELECT HIERARCHY_CODE,C_REPORTING_ITEM.TEXT,C_REPORTING_ITEM.PLANNING_ITEM,C_REPORTING_ITEM.ID, C_REPORTING_ITEM_PARENT.ID ,NVL(HIERARCHY_LEVEL,' '),LINE_NUMBER,NVL(HIERARCHY_PARENT,' ') ";
		sQuery = sQuery + " FROM C_REPORTING_ITEM,C_REP_ITEM_GROUPING C_REP_ITEM_GROUPING, C_REPORTING_ITEM C_REPORTING_ITEM_PARENT ";
		sQuery = sQuery + " WHERE C_REPORTING_ITEM.HUB_STATE_IND=1 AND C_REP_ITEM_GROUPING.HUB_STATE_IND=1 AND C_REPORTING_ITEM_PARENT.HUB_STATE_IND=1 AND  C_REPORTING_ITEM.ROWID_OBJECT=C_REP_ITEM_GROUPING.PARENT_2 AND C_REPORTING_ITEM_PARENT.ROWID_OBJECT=C_REP_ITEM_GROUPING.PARENT_1 AND ";
		sQuery = sQuery +  " C_REP_ITEM_GROUPING.HIERARCHY_CODE='"  + selArr[1]  + "' ORDER BY LINE_NUMBER"; 
				
		
		logger.info("RepItem Hierarchy Query::" + sQuery  );
	
	try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					RepItemRecord myrepitemrecord = new RepItemRecord();
					
					myrepitemrecord.setHierarchy_code(myrs.getString(1));
					myrepitemrecord.setDescription(myrs.getString(2));
					myrepitemrecord.setPlanning_Level(myrs.getString(3));
					myrepitemrecord.setRep_Item(myrs.getString(4));
					myrepitemrecord.setDisplayCode(myrs.getString(4));
					myrepitemrecord.setParent(myrs.getString(8));
					myrepitemrecord.setOrder(myrs.getString(5) + myrs.getString(4));
					myrepitemrecord.setLevel(myrs.getString(6));
					myrepitemrecord.setLineNumber(myrs.getString(7));
					myrepitemRecordList.add(myrepitemrecord);
					
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
		
		/* We dont need to sort as we get the Line Numbers from GDM now.
		logger.info("Before Generate Order");
		GenerateOrder((ArrayList<RepItemRecord>) myrepitemRecordList);
		
		logger.info("Before Sort");
		Collections.sort(myrepitemRecordList, new RepItemSorter());
		*/
		repitemrecordlist.setRepitemlist(myrepitemRecordList);
		return repitemrecordlist;	
	}




/* This method is no longer required as we get the Line Numbers from GDM
private void GenerateOrder(ArrayList<RepItemRecord> mylist){
	
	int templevel=0;
	
	logger.info("Number of Records in Reporting Item " + mylist.size()) ;	
	
	for (int i=0; i < mylist.size(); i++)
	{
		
		RepItemRecord repRecord = mylist.get(i);
		String sParent=repRecord.getParent();
		String sOrder=sParent + repRecord.getRep_Item();
		
		while(!sParent.equals(" ")) {
			templevel=templevel+1;
			sParent=GetParent(mylist, sParent);
			sOrder= sParent +sOrder;
			//logger.info("sOrder: " + sOrder) ;
			
		}
		
		//logger.info("Order for Id: " +fsRecord.getCode() +" is " + sOrder + " and level is " + templevel) ;	
		
		repRecord.setOrder(sOrder);

		repRecord.setLevel(Integer.toString(templevel));
		
		repRecord.setDisplayCode(repRecord.getRep_Item());
		
		templevel=0;
		sOrder="";
		
		logger.info("Compeleted setting Order for record no " + repRecord.getRep_Item()) ;
		
	}
}


private String GetParent(ArrayList<RepItemRecord> mylist, String sNode){
	
	for (int i=0; i < mylist.size(); i++)
	{
		
		RepItemRecord repRecord = mylist.get(i);
		if (repRecord.getRep_Item().equals(sNode)){
			return repRecord.getParent();
		
			}
		}
	
	return " ";
}

*/



public String ExportReportingItemHeirarchy(String sHeirarchyName) 
{
	RepItemRecordList repitemlist = new RepItemRecordList();
	
	StringBuilder sb=new StringBuilder("Hierarchy Code,Description,Planning Level,Reporting Item,Parent Item,Hierarchy Level"+ "\n");
			
	repitemlist = ViewRepItemHierarchy(sHeirarchyName);
	
	logger.info("Got the List from ViewRepItemHierarchy") ;
	
	List <RepItemRecord> myrepitemList = repitemlist.getRepitemlist();
	
	logger.info("Total Number of Records in the Hierarchy is ::  " + String.valueOf(myrepitemList.size()-1)) ;
	
	
	for (int i=0; i<= myrepitemList.size()-1; i++)
	{
		RepItemRecord rep= myrepitemList.get(i);
		
		sb.append(rep.getHierarchy_code().replace(",", ";"));
		sb.append("," );
		sb.append(rep.getDescription().replace(",", ";") );
		sb.append("," );
		sb.append(rep.getPlanning_Level().replace(",", ";"));
		sb.append("," );
		sb.append(rep.getDisplayCode().replace(",", ";"));
		sb.append("," );
		sb.append(rep.getParent().replace(",", ";"));
		sb.append("," );
		sb.append(rep.getLevel().replace(",", ";"));
		sb.append("\n" );
		
		logger.info("Completed Record Number ::" + String.valueOf(i)) ;
		
	}
	logger.info("Compeleted building string for Export") ;
	
	return sb.toString();
}
	
}
