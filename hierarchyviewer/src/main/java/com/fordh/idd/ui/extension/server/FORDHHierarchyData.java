package com.fordh.idd.ui.extension.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.FORDHHierarchy;
import com.fordh.idd.ui.extension.client.Model.FORDHHierarchyList;

public class FORDHHierarchyData{

	public static Logger logger = Logger.getLogger(FORDHHierarchyData.class.getName());
	private List<FORDHHierarchy>myfordhHierarchyList ;
	private FORDHHierarchyList fordhhierarchylist= new FORDHHierarchyList();
	
	public FORDHHierarchyList GetHeirarchyList() {
		
		logger.info("Inside server side::GetHeirarchyList" );
		ResultSet myrs ;
		
		FORDHHierarchyList fordhHierarchyList = new FORDHHierarchyList();
		myfordhHierarchyList =   new ArrayList<FORDHHierarchy>();
		
		String sQuery ="SELECT ID,TEXT,HTYPE FROM (";
		sQuery = sQuery  + " SELECT ID, TEXT ,'MRU' HTYPE FROM C_MAN_REP_UNIT_HIERARCHY WHERE HUB_STATE_IND =1 AND C_MAN_REP_UNIT_HIERARCHY.LIFE_CYCLE_PHASE='2' "; 
		sQuery = sQuery + " UNION SELECT ID,TEXT ,'ORU' HTYPE FROM C_ORGANIZATION_HIERARCHY WHERE HUB_STATE_IND =1 AND C_ORGANIZATION_HIERARCHY.LIFE_CYCLE_PHASE='2'";
		sQuery = sQuery + " UNION select ID,TEXT, 'CCH' HTYPE  from C_CCH_HIERARCHY WHERE HUB_STATE_IND =1 AND C_CCH_HIERARCHY.LIFE_CYCLE_PHASE='2'";
		sQuery = sQuery + " UNION SELECT ID, TEXT, 'GEO' HTYPE FROM C_COUNTRY_HIERARCHY  WHERE HUB_STATE_IND =1 AND C_COUNTRY_HIERARCHY.LIFE_CYCLE_PHASE='2'";
		sQuery = sQuery + " UNION SELECT ID, TEXT, 'PRODUCT_TREE' HTYPE FROM C_PRODUCT_TREE WHERE HUB_STATE_IND =1 AND C_PRODUCT_TREE.LIFE_CYCLE_PHASE='2'";
		sQuery = sQuery + " UNION SELECT 'BMC', 'BMC BUSINESS', 'BMC' HTYPE FROM SYSIBM.SYSDUMMY1 ";
		sQuery = sQuery + " UNION SELECT ID,TEXT, 'FSITEM' HTYPE FROM C_FS_ITEM_HIERARCHY  WHERE HUB_STATE_IND =1 AND C_FS_ITEM_HIERARCHY.LIFE_CYCLE_PHASE='2'";
		sQuery = sQuery + " UNION SELECT ID,TEXT, 'REPITEM' HTYPE FROM C_REP_ITEM_HIERARCHY  WHERE HUB_STATE_IND =1 AND C_REP_ITEM_HIERARCHY.LIFE_CYCLE_PHASE='2') ORDER BY HTYPE";
		
		logger.info("Dropdownlist Query::" + sQuery  );
		
		try {
				RunSQL mysql = new RunSQL();
				myrs = mysql.ExecuteQuery(sQuery);
					try{
						while (myrs.next())
						{
							FORDHHierarchy myHierarchy = new FORDHHierarchy();
							myHierarchy.setCode(myrs.getString(1));
							myHierarchy.setName(myrs.getString(2));
							myHierarchy.setType(myrs.getString(3));
							myfordhHierarchyList.add(myHierarchy);
							
					}
						logger.info(fordhHierarchyList.toString());
						fordhhierarchylist.setHeirarchyList(myfordhHierarchyList);		
						
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
				
				return fordhhierarchylist ;
	}

}
