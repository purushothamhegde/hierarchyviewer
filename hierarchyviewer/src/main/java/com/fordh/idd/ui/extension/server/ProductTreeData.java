package com.fordh.idd.ui.extension.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecord;
import com.fordh.idd.ui.extension.client.Model.CostCenterHierarchyRecordList;
import com.fordh.idd.ui.extension.client.Model.PTSorter;
import com.fordh.idd.ui.extension.client.Model.ProductTreeList;
import com.fordh.idd.ui.extension.client.Model.ProductTreeRecord;

public class ProductTreeData {

	public static Logger logger = Logger.getLogger(ProductTreeData.class.getName());
	
	private ProductTreeList myProductTreeRecordList = new ProductTreeList();
	private List<ProductTreeRecord> myProductTreeRecordlist ;

	
	public ProductTreeList ViewProductTreeHeirarchy(String sHeirarchyName) {
		
		logger.info("Inside Method :ViewProductTreeHeirarchy");
		String selArr[]= sHeirarchyName.split("-");
		ResultSet myrs ;
		myProductTreeRecordlist = new ArrayList<ProductTreeRecord>();
		String sQuery ="";
		// May need to add the effective dates as well.
		
		
		sQuery =" SELECT IS_CURRENT_YEAR,IS_NEXT_YEAR,VALID_FROM FROM C_PRODUCT_TREE WHERE ID='" + selArr[1] +"'" ;

		logger.info("Find if the PT is current or AOP " + sQuery  );
		RunSQL mysql1 = new RunSQL();
		try {
			myrs = mysql1.ExecuteQuery(sQuery);
			
			try {
				while (myrs.next())
				{
					if (myrs.getString(1).equals("Y"))
					{
						sQuery =" SELECT ID,LD,LEVEL,PARENT,VALUE_SEGMENT,RISK FROM (";
						sQuery = sQuery + " SELECT SC.ID ,SC.TEXT LD,1 LEVEL,''PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_SECTOR SC WHERE SC.HUB_STATE_IND=1 AND SC.VALID_FROM <=SYSDATE AND SC.HUB_STATE_IND=1 UNION ";
						sQuery = sQuery + " SELECT PD.ID,PD.TEXT LD,2 LEVEL,SC.ID PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_PRODUCT_DIVISION PD,C_SECTOR SC WHERE SC.ROWID_OBJECT =PD.GRP AND PD.HUB_STATE_IND=1 AND PD.VALID_FROM <=SYSDATE AND PD.HUB_STATE_IND=1 UNION ";
						sQuery = sQuery + " SELECT BG.ID,BG.TEXT LD,3 LEVEL,PD.ID PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_BUSINESS_GROUP BG ,C_PRODUCT_DIVISION PD WHERE BG.HUB_STATE_IND=1 AND BG.GRP=PD.ROWID_OBJECT AND BG.VALID_FROM <=SYSDATE AND BG.HUB_STATE_IND=1 UNION "; 
						sQuery = sQuery + " SELECT BU.ID,BU.TEXT LD,4 LEVEL,BG.ID PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_BUSINESS BU ,C_BUSINESS_GROUP BG WHERE BU.GRP=BG.ROWID_OBJECT AND BU.HUB_STATE_IND=1   AND BU.VALID_FROM <=SYSDATE  AND BU.HUB_STATE_IND=1 UNION ";
						sQuery = sQuery + " SELECT MAG.ID ,MAG.TEXT LD,5 LEVEL,BU.ID PARENT, NVL(MAG.VALUE_SEGMENT,' '),'' RISK  FROM C_MAIN_ARTICLE_GROUP MAG ,C_BUSINESS BU WHERE MAG.GRP=BU.ROWID_OBJECT AND MAG.HUB_STATE_IND=1 AND MAG.VALID_FROM <=SYSDATE AND MAG.HUB_STATE_IND=1  UNION ";
						sQuery = sQuery + " SELECT AG.ID,AG.TEXT,6 LD,MAG.ID PARENT, ''VALUE_SEGMENT, NVL(AG.RISK,' ') RISK  FROM C_ARTICLE_GROUP AG ,C_MAIN_ARTICLE_GROUP MAG WHERE AG.GRP=MAG.ROWID_OBJECT AND AG.HUB_STATE_IND=1 AND AG.VALID_FROM <=SYSDATE AND AG.HUB_STATE_IND=1 ";
						sQuery = sQuery + " ) ORDER BY LEVEL";
					}
					else if(myrs.getString(2).equals("Y")) 
					{
						sQuery =" SELECT ID,LD,LEVEL,PARENT,VALUE_SEGMENT,RISK FROM (";
						sQuery = sQuery + " SELECT SC.ID ,SC.TEXT LD,1 LEVEL,''PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_SECTOR SC WHERE SC.HUB_STATE_IND=1 UNION ";
						sQuery = sQuery + " SELECT PD.ID,PD.TEXT LD,2 LEVEL,SC.ID PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_PRODUCT_DIVISION PD,C_SECTOR SC WHERE SC.ROWID_OBJECT =PD.GRP AND PD.HUB_STATE_IND=1 UNION ";
						sQuery = sQuery + " SELECT BG.ID,BG.TEXT LD,3 LEVEL,PD.ID PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_BUSINESS_GROUP_XREF BG ,C_PRODUCT_DIVISION_XREF PD WHERE BG.HUB_STATE_IND=1 AND BG.GRP=PD.ROWID_XREF AND BG.PERIOD_START_DATE =(SELECT MAX(PERIOD_START_DATE) FROM  C_BUSINESS_GROUP_XREF IN_BG WHERE BG.ID=IN_BG.ID ) UNION "; 
						sQuery = sQuery + " SELECT BU.ID,BU.TEXT LD,4 LEVEL,BG.ID PARENT,'' VALUE_SEGMENT,'' RISK  FROM C_BUSINESS_XREF BU ,C_BUSINESS_GROUP_XREF BG WHERE BU.GRP=BG.ROWID_XREF AND BU.HUB_STATE_IND=1 AND BU.PERIOD_START_DATE =(SELECT MAX(PERIOD_START_DATE) FROM  C_BUSINESS_XREF IN_BU WHERE BU.ID=IN_BU.ID )  UNION ";
						sQuery = sQuery + " SELECT MAG.ID ,MAG.TEXT LD,5 LEVEL,BU.ID PARENT, NVL(MAG.VALUE_SEGMENT,' '),'' RISK  FROM C_MAIN_ARTICLE_GROUP_XREF MAG ,C_BUSINESS_XREF BU WHERE MAG.GRP=BU.ROWID_XREF AND MAG.HUB_STATE_IND=1  AND MAG.PERIOD_START_DATE =(SELECT MAX(PERIOD_START_DATE) FROM  C_MAIN_ARTICLE_GROUP_XREF IN_MAG WHERE MAG.ID=IN_MAG.ID )   UNION ";
						sQuery = sQuery + " SELECT AG_XREF.ID,AG_XREF.TEXT,6 LD,MAG.ID PARENT,''VALUE_SEGMENT, NVL(AG_XREF.RISK,' ') RISK  FROM C_ARTICLE_GROUP_XREF AG_XREF ,C_MAIN_ARTICLE_GROUP_XREF  MAG WHERE AG_XREF.GRP=MAG.ROWID_XREF AND AG_XREF.HUB_STATE_IND=1 AND AG_XREF.PERIOD_START_DATE =(SELECT MAX(PERIOD_START_DATE) FROM  C_ARTICLE_GROUP_XREF IN_AG WHERE AG_XREF.ID=IN_AG.ID ) ";
						sQuery = sQuery + " ) ORDER BY LEVEL";
					}

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		logger.info("Product Tree Hierarchy Query::" + sQuery  );
				
		try {
			
			RunSQL mysql = new RunSQL();
		    myrs = mysql.ExecuteQuery(sQuery);
		    
		    
			try{
				while (myrs.next())
				{

					ProductTreeRecord ProductTreerecord = new ProductTreeRecord();
					
					ProductTreerecord.setId(myrs.getString(1));
					ProductTreerecord.setLD(myrs.getString(2));
					ProductTreerecord.setLevel(myrs.getString(3));
					ProductTreerecord.setParent(myrs.getString(4));
					ProductTreerecord.setValueSegment(myrs.getString(5));
					ProductTreerecord.setRisk(myrs.getString(6));
					
					if (myrs.getString(3).equals("1")) {
						ProductTreerecord.setSector(myrs.getString(1));
						ProductTreerecord.setProductDivision(" ");
						ProductTreerecord.setBusinessGroup(" ");
						ProductTreerecord.setBusiness(" ");
						ProductTreerecord.setMainArticleGroup(" ");
						ProductTreerecord.setArticleGroup(" ");
					}
						
					else if(myrs.getString(3).equals("2")){
						ProductTreerecord.setSector(" ");
						ProductTreerecord.setProductDivision(myrs.getString(1));
						ProductTreerecord.setBusinessGroup(" ");
						ProductTreerecord.setBusiness(" ");
						ProductTreerecord.setMainArticleGroup(" ");
						ProductTreerecord.setArticleGroup(" ");
					}	
					else if(myrs.getString(3).equals("3")){
						ProductTreerecord.setSector(" ");
						ProductTreerecord.setProductDivision(" ");
						ProductTreerecord.setBusinessGroup(myrs.getString(1));
						ProductTreerecord.setBusiness(" ");
						ProductTreerecord.setMainArticleGroup(" ");
						ProductTreerecord.setArticleGroup(" ");
					}	
					else if(myrs.getString(3).equals("4")){
						ProductTreerecord.setSector(" ");
						ProductTreerecord.setProductDivision(" ");
						ProductTreerecord.setBusinessGroup(" ");
						ProductTreerecord.setBusiness(myrs.getString(1));
						ProductTreerecord.setMainArticleGroup(" ");
						ProductTreerecord.setArticleGroup(" ");
					}	
					else if(myrs.getString(3).equals("5")){
						ProductTreerecord.setSector(" ");
						ProductTreerecord.setProductDivision(" ");
						ProductTreerecord.setBusinessGroup(" ");
						ProductTreerecord.setBusiness(" ");
						ProductTreerecord.setMainArticleGroup(myrs.getString(1));
						ProductTreerecord.setArticleGroup(" ");
					}	
					else if(myrs.getString(3).equals("6")){
						ProductTreerecord.setSector(" ");
						ProductTreerecord.setProductDivision(" ");
						ProductTreerecord.setBusinessGroup(" ");
						ProductTreerecord.setBusiness(" ");
						ProductTreerecord.setMainArticleGroup(" ");
						ProductTreerecord.setArticleGroup(myrs.getString(1));
					}	
					ProductTreerecord.setOrder(ProductTreerecord.getParent() + ProductTreerecord.getId());
					myProductTreeRecordlist.add(ProductTreerecord);
					
				}
				
				GenerateOrder((ArrayList<ProductTreeRecord>) myProductTreeRecordlist);
				Collections.sort(myProductTreeRecordlist, new PTSorter());
				myProductTreeRecordList.setProductTreeRecordList(myProductTreeRecordlist);		
				
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
		myProductTreeRecordList.setHierarchyName(sHeirarchyName);
		return myProductTreeRecordList ;	

		
		
	}
	
	
	
private void GenerateOrder(ArrayList<ProductTreeRecord> mylist){
		
		
		
		for (int i=0; i < mylist.size(); i++)
		{
			
			ProductTreeRecord ptRecord = mylist.get(i);
			
			if (Integer.parseInt(ptRecord.getLevel())>1) {
				
				String sParent=ptRecord.getParent();
				
				String sOrder= GetOrder(mylist,sParent,ptRecord.getLevel());
				
				ptRecord.setOrder(sOrder + ptRecord.getId());
			}
			
			
		}
		
		
	}
	

private String GetOrder(ArrayList<ProductTreeRecord> mylist, String sParentNode, String level){
	
	for (int i=0; i < mylist.size(); i++)
	{
		
		ProductTreeRecord ptRecord = mylist.get(i);
		if (ptRecord.getId().equals(sParentNode) && Integer.parseInt(ptRecord.getLevel())== Integer.parseInt(level)-1 ){
			return ptRecord.getOrder();
		
			}
		}
	
	return null;
	
}


//ViewProductTreeHeirarchy

public String ExportProductTreeHeirarchy(String sHeirarchyName) 
{
	ProductTreeList ptlist = new ProductTreeList();
	
	String content="Sector,Product Division,Business Group,Business,Main Article Group,Article Group,Description,Value Segment,Risk" + "\n";
	

	ptlist = ViewProductTreeHeirarchy(sHeirarchyName);
	
	List <ProductTreeRecord> myptList = ptlist.getProductTreeRecordList();
	
	for (int i=0; i<= myptList.size()-1; i++)
	{
		ProductTreeRecord pt= myptList.get(i);
		content=content +   pt.getSector().replace(",", ";") + "," ;
		content=content +   pt.getProductDivision().replace(",", ";") + "," ;
		content=content +   pt.getBusinessGroup().replace(",", ";") + "," ;
		content=content +   pt.getBusiness().replace(",", ";") + "," ;
		content=content +   pt.getMainArticleGroup().replace(",", ";") + "," ;
		content=content +   pt.getArticleGroup().replace(",", ";") + "," ;
		content=content +   pt.getLD().replace(",", ";") + "," ;
		content=content +   pt.getValueSegment().replace(",", ";") + "," ;
		content=content +   pt.getRisk().replace(",", ";") + "\n" ;
	}
	return content;
}



}


