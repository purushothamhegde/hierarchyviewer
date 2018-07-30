package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.GeoRecord;
import com.fordh.idd.ui.extension.client.Model.GeoRecordList;

public class GeoHierarchyData {

	public static Logger logger = Logger.getLogger(GeoHierarchyData.class.getName());
	private GeoRecordList georecordlist = new GeoRecordList();
	private List<GeoRecord> myGeoRecordList ;
	
	
	public GeoRecordList ViewGeoHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewGeoHeirarchy");
		String selArr[]= sHeirarchyName.split("-");
		ResultSet myrs ;
		myGeoRecordList= new ArrayList<GeoRecord>();
		
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery =" SELECT LC,CODE,HIERARCHY_LEVEL,FullName,ParentDetails FROM (";
		sQuery = sQuery + " SELECT HIERARCHY_CODE,REL_TYPE_CODE,C_COUNTRY.LIFE_CYCLE_PHASE LC, COU_HIERARCHY_NODE_LEVEL HIERARCHY_LEVEL, C_COUNTRY.ID CODE,C_COUNTRY.TEXT FullName,C_COUNTRY_GROUP.TEXT  ParentDetails ";
		sQuery = sQuery + " FROM C_COUNTRY_GROUPING,C_COUNTRY,C_COUNTRY_GROUP";
		sQuery = sQuery + " WHERE C_COUNTRY_GROUPING.HUB_STATE_IND=1 AND C_COUNTRY.HUB_STATE_IND =1 AND C_COUNTRY_GROUP.HUB_STATE_IND =1 AND "; 
		sQuery = sQuery + " C_COUNTRY_GROUPING.PARENT_1 = C_COUNTRY_GROUP.ROWID_OBJECT AND C_COUNTRY_GROUPING.PARENT_2 = C_COUNTRY.ROWID_OBJECT AND C_COUNTRY_GROUPING.HIERARCHY_CODE='" + selArr[1] +"'";
		sQuery = sQuery + " UNION ";
		sQuery = sQuery + " SELECT HIERARCHY_CODE,REL_TYPE_CODE, CHILD.LIFE_CYCLE_PHASE LC, HIERARCHY_LEVEL, CHILD.ID CODE,CHILD.TEXT FullName,PARENT.TEXT  ParentDetails";
		sQuery = sQuery + " FROM C_CNTRY_GRP_GRPNG,C_COUNTRY_GROUP PARENT,C_COUNTRY_GROUP CHILD";
		sQuery = sQuery + " WHERE C_CNTRY_GRP_GRPNG.HUB_STATE_IND =1 AND PARENT.HUB_STATE_IND =1 AND CHILD.HUB_STATE_IND=1 AND ";
		sQuery = sQuery + " C_CNTRY_GRP_GRPNG.PARENT_1 = PARENT.ROWID_OBJECT AND C_CNTRY_GRP_GRPNG.PARENT_2 = CHILD.ROWID_OBJECT AND C_CNTRY_GRP_GRPNG.HIERARCHY_CODE='" + selArr[1] +"')";
		sQuery = sQuery + "  ORDER BY HIERARCHY_LEVEL";

		logger.info("Geo Hierarchy Query::" + sQuery  );
				
		try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					GeoRecord georecord = new GeoRecord();

					georecord.setLC(myrs.getString(1));
					georecord.setCODE(myrs.getString(2));
					georecord.setHIERARCHY_LEVEL(myrs.getString(3));
					georecord.setFullName(myrs.getString(4));
					georecord.setParentDetails(myrs.getString(5));
					
										
					if (myrs.getString(3).equals("1")) {
						georecord.setL1("" + myrs.getString(2));
						
					}
					else if (myrs.getString(3).equals("2")) {
						georecord.setL2("" + myrs.getString(2));
					}
					else if (myrs.getString(3).equals("3")) {
						georecord.setL3("" + myrs.getString(2));
					}
					else if (myrs.getString(3).equals("4")) {
						georecord.setL4("" + myrs.getString(2));
					}
					else if (myrs.getString(3).equals("5")) {
						georecord.setL5("" + myrs.getString(2));
					}
					
					
					myGeoRecordList.add(georecord);
					
			}
				georecordlist.setGeorecordlist(myGeoRecordList);		
				
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
		georecordlist.setHierarchyName(sHeirarchyName);
		return georecordlist ;	
		}


	
	public String ExportGeoHeirarchy(String sHeirarchyName) 
	{
		GeoRecordList geolist = new GeoRecordList();
		
		String content="Life Cycle,Code,Level,L1,L2,L3,L4,L5,Full Name,Parent Details" + "\n";
		
		
		geolist = ViewGeoHeirarchy(sHeirarchyName);
		
		List <GeoRecord> mygeoList = geolist.getGeorecordlist();
		
		for (int i=0; i<= mygeoList.size()-1; i++)
		{
			GeoRecord geo= mygeoList.get(i);
			content=content +   geo.getLC() + "," ;
			content=content +   geo.getHIERARCHY_LEVEL() + "," ;
			content=content +   geo.getL1() + "," ;
			content=content +   geo.getL2() + "," ;
			content=content +   geo.getL3() + "," ;
			content=content +   geo.getL4() + "," ;
			content=content +   geo.getL5() + "," ;
			content=content +   geo.getFullName() + "," ;
			content=content +   geo.getParentDetails() + "\n" ;
						
		}
		return content;
	}
}
