package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.CCSorter;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.MruHierarchyRecordList;

public class CCHeirarchyData {

	public static Logger logger = Logger.getLogger(CCHeirarchyData.class.getName());
	private CostCenterHierarchyRecordList CCrecordlist = new CostCenterHierarchyRecordList();
	private List<CostCenterHierarchyRecord> myCCRecordList ;
	
	
	public CostCenterHierarchyRecordList ViewCCHeirarchy(String sHeirarchyName) {
		logger.info("Inside Method :ViewCCHeirarchy");
		String selArr[]= sHeirarchyName.split("-");
		ResultSet myrs ;
		myCCRecordList = new ArrayList<CostCenterHierarchyRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		sQuery =" SELECT CCH_CHILD.LIFE_CYCLE_PHASE,CCH_CHILD.ID NodeID, CCH_CHILD.ABBR NodeName,CCH_PARENT.ABBR ParentNodeName,CCH_REL.HIERARCHY_LEVEL Level, CCH_CHILD.GROUP_1 InfoObject,";
		sQuery = sQuery + " NVL(CCH_CHILD.LINKED_NODE,' ')LinkedNode,CCH_CHILD.TEXT_2 ShortDesc,CCH_CHILD.TEXT_3 MediumDesc,CCH_CHILD.LANGUAGE_1 Language, ";
		sQuery = sQuery + " NVL(CCH_CHILD.MRU,' ')MRU,NVL(CCH_CHILD.MAG,' ') MAG,NVL(CCH_CHILD.COMPANY_CODE,' ')COMPANY_CODE ,NVL(CCH_CHILD.ORU,' ') ORU ";
		sQuery = sQuery + " FROM "; 
		sQuery = sQuery + " C_COST_CENTER_HIERARCHY CCH_CHILD ,C_COS_CEN_HIE_GROUPING CCH_REL,C_COST_CENTER_HIERARCHY CCH_PARENT ";
		sQuery = sQuery + " WHERE CCH_CHILD.HUB_STATE_IND=1 AND CCH_REL.HUB_STATE_IND =1 AND CCH_PARENT.HUB_STATE_IND =1 AND ";
		sQuery = sQuery + " CCH_REL.PARENT_2=CCH_CHILD.ROWID_OBJECT AND CCH_REL.PARENT_1=CCH_PARENT.ROWID_OBJECT AND CCH_REL.HIERARCHY_CODE ='" + selArr[1] +"'";
		sQuery = sQuery + " ORDER BY to_number(Level)";

		logger.info("Cost Center Hierarchy Query::" + sQuery  );
				
		try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{
					CostCenterHierarchyRecord CCrecord = new CostCenterHierarchyRecord();

					CCrecord.setLIFE_CYCLE_PHASE(myrs.getString(1));
					CCrecord.setNODEID(myrs.getString(2));
					CCrecord.setNODENAME(myrs.getString(3));
					CCrecord.setPARENTNODENAME(myrs.getString(4));
					CCrecord.setLEVEL(myrs.getString(5));
					CCrecord.setINFOOBJECT(myrs.getString(6));
					CCrecord.setLINKEDNODE(myrs.getString(7));
					CCrecord.setSHORTDESC(myrs.getString(8));
					CCrecord.setMEDIUMDESC(myrs.getString(9));
					CCrecord.setLANGUAGE(myrs.getString(10));
					CCrecord.setMRU(myrs.getString(11));
					CCrecord.setMAG(myrs.getString(12));
					CCrecord.setCOMPANY_CODE(myrs.getString(13));
					CCrecord.setORU(myrs.getString(14));
					
					
					
					if (myrs.getString(5).equals("1")) {
						CCrecord.setL1("" + myrs.getString(3));
						
					}
					else if (myrs.getString(5).equals("2")) {
						CCrecord.setL2("" + myrs.getString(3));
					}
					else if (myrs.getString(5).equals("3")) {
						CCrecord.setL3("" + myrs.getString(3));
					}
					else if (myrs.getString(5).equals("4")) {
						CCrecord.setL4("" + myrs.getString(3));
					}
					else if (myrs.getString(5).equals("5")) {
						CCrecord.setL5("" + myrs.getString(3));
					}
					else if (myrs.getString(5).equals("6")) {
						CCrecord.setL6("" + myrs.getString(3));
					}
					
					CCrecord.setOrder(CCrecord.getPARENTNODENAME()+CCrecord.getNODENAME());
					
					myCCRecordList.add(CCrecord);
					
			}
				
				GenerateOrder((ArrayList<CostCenterHierarchyRecord>) myCCRecordList);
				
				Collections.sort(myCCRecordList, new CCSorter());
				
				CCrecordlist.setCCRecordList(myCCRecordList);		
				
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
		CCrecordlist.setHierarchyName(sHeirarchyName);
		return CCrecordlist ;	
		}

	
	private String GetOrder(ArrayList<CostCenterHierarchyRecord> mylist, String sParentNode){
		
		for (int i=0; i < mylist.size(); i++)
		{
			
			CostCenterHierarchyRecord ccRecord = mylist.get(i);
			if (ccRecord.getNODENAME().equals(sParentNode)){
				return ccRecord.getOrder();
			
				}
			}
		
		return null;
		
	}
	
	private void GenerateOrder(ArrayList<CostCenterHierarchyRecord> mylist){
		
		
		
		for (int i=0; i < mylist.size(); i++)
		{
			
			CostCenterHierarchyRecord ccRecord = mylist.get(i);
			
			if (Integer.parseInt(ccRecord.getLEVEL())>2) {
				
				String sParent=ccRecord.getPARENTNODENAME();
				
				String sOrder= GetOrder(mylist,sParent);
				
				ccRecord.setOrder(sOrder + ccRecord.getNODENAME());
			}
			
			
		}
		
		
		
		
		
	}

	public String ExportCCHeirarchy(String sHeirarchyName) 
	{
		CostCenterHierarchyRecordList cclist = new CostCenterHierarchyRecordList();
		
	//	String content="Life Cycle,Node Id,Node Name,Parent Node Name,L1,L2,L3,L4,L5,L6,Info Object,Linked Node,Short Description,Medium Descripion,Language,MRU,MAG,Company Code,ORU" + "\n";
		
		StringBuilder sb=new StringBuilder("Life Cycle,Node Id,Node Name,Parent Node Name,L1,L2,L3,L4,L5,L6,Info Object,Linked Node,Short Description,Medium Descripion,Language,MRU,MAG,Company Code,ORU" + "\n");  
		
		
		cclist = ViewCCHeirarchy(sHeirarchyName);
		
		List <CostCenterHierarchyRecord> myccList = cclist.getCCRecordList();
		
		for (int i=0; i<= myccList.size()-1; i++)
		{
			CostCenterHierarchyRecord cc= myccList.get(i);
			sb.append(cc.getLIFE_CYCLE_PHASE().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getNODEID().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getNODENAME().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getPARENTNODENAME().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getL1().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getL2().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getL3().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getL4().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getL5().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getL6().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getINFOOBJECT().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getLINKEDNODE().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getSHORTDESC().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getMEDIUMDESC().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getLANGUAGE().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getMRU().replace(",", ";")  );
			sb.append(",");
			sb.append(cc.getMAG().replace(",", ";") );
			sb.append(",");
			sb.append(cc.getCOMPANY_CODE().replace(",", ";"));
			sb.append(",");
			sb.append(cc.getORU().replace(",", ";") );
			sb.append("\n");
			
		}
		
		return sb.toString();
	}

	
}
