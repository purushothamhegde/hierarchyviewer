package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class CostCenterHierarchyRecord implements Serializable {
	private String LIFE_CYCLE_PHASE;	
	private String NODEID	;
	private String NODENAME	;
	private String PARENTNODENAME;	
	private String LEVEL	;
	private String INFOOBJECT;	
	private String LINKEDNODE;	
	private String SHORTDESC	;
	private String MEDIUMDESC	;
	private String LANGUAGE	;
	private String MRU	;
	private String MAG;
	private String L1="";
	private String L2="";
	private String L3="";
	private String L4="";
	private String L5="";
	private String L6="";
	private String Order="";
	private String COMPANY_CODE="";
	private String  ORU="";
	
	
	
	public String getCOMPANY_CODE() {
		return COMPANY_CODE;
	}
	public void setCOMPANY_CODE(String cOMPANY_CODE) {
		COMPANY_CODE = cOMPANY_CODE;
	}
	public String getORU() {
		return ORU;
	}
	public void setORU(String oRU) {
		ORU = oRU;
	}
	
	
	
	public String getOrder() {
		return Order;
	}
	public void setOrder(String order) {
		Order = order;
	}
	public String getL1() {
		return L1;
	}
	public void setL1(String l1) {
		L1 = l1;
	}
	public String getL2() {
		return L2;
	}
	public void setL2(String l2) {
		L2 = l2;
	}
	public String getL3() {
		return L3;
	}
	public void setL3(String l3) {
		L3 = l3;
	}
	public String getL4() {
		return L4;
	}
	public void setL4(String l4) {
		L4 = l4;
	}
	public String getL5() {
		return L5;
	}
	public void setL5(String l5) {
		L5 = l5;
	}
	public String getL6() {
		return L6;
	}
	public void setL6(String l6) {
		L6 = l6;
	}
	
	public String getLIFE_CYCLE_PHASE() {
		return LIFE_CYCLE_PHASE;
	}
	public void setLIFE_CYCLE_PHASE(String lIFE_CYCLE_PHASE) {
		LIFE_CYCLE_PHASE = lIFE_CYCLE_PHASE;
	}
	public String getNODEID() {
		return NODEID;
	}
	public void setNODEID(String nODEID) {
		NODEID = nODEID;
	}
	public String getNODENAME() {
		return NODENAME;
	}
	public void setNODENAME(String nODENAME) {
		NODENAME = nODENAME;
	}
	public String getPARENTNODENAME() {
		return PARENTNODENAME;
	}
	public void setPARENTNODENAME(String pARENTNODENAME) {
		PARENTNODENAME = pARENTNODENAME;
	}
	public String getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(String lEVEL) {
		LEVEL = lEVEL;
	}
	public String getINFOOBJECT() {
		return INFOOBJECT;
	}
	public void setINFOOBJECT(String iNFOOBJECT) {
		INFOOBJECT = iNFOOBJECT;
	}
	public String getLINKEDNODE() {
		return LINKEDNODE;
	}
	public void setLINKEDNODE(String lINKEDNODE) {
		LINKEDNODE = lINKEDNODE;
	}
	public String getSHORTDESC() {
		return SHORTDESC;
	}
	public void setSHORTDESC(String sHORTDESC) {
		SHORTDESC = sHORTDESC;
	}
	public String getMEDIUMDESC() {
		return MEDIUMDESC;
	}
	public void setMEDIUMDESC(String mEDIUMDESC) {
		MEDIUMDESC = mEDIUMDESC;
	}
	public String getLANGUAGE() {
		return LANGUAGE;
	}
	public void setLANGUAGE(String lANGUAGE) {
		LANGUAGE = lANGUAGE;
	}
	public String getMRU() {
		return MRU;
	}
	public void setMRU(String mRU) {
		MRU = mRU;
	}
	public String getMAG() {
		return MAG;
	}
	public void setMAG(String mAG) {
		MAG = mAG;
	}

}
