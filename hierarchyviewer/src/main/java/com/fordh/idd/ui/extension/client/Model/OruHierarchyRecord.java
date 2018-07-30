package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class OruHierarchyRecord implements Serializable {

	
	public String getH_LEVEL() {
		return H_LEVEL;
	}
	public void setH_LEVEL(String h_LEVEL) {
		H_LEVEL = h_LEVEL;
	}
	public String getLINE_NUMBER() {
		return LINE_NUMBER;
	}
	public void setLINE_NUMBER(String lINE_NUMBER) {
		LINE_NUMBER = lINE_NUMBER;
	}
	public String getPARENT_ORU() {
		return PARENT_ORU;
	}
	public void setPARENT_ORU(String pARENT_ORU) {
		PARENT_ORU = pARENT_ORU;
	}
	public String getCHILD_ORU() {
		return CHILD_ORU;
	}
	public void setCHILD_ORU(String cHILD_ORU) {
		CHILD_ORU = cHILD_ORU;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getMEDIUM_DESCRIPTION() {
		return MEDIUM_DESCRIPTION;
	}
	public void setMEDIUM_DESCRIPTION(String mEDIUM_DESCRIPTION) {
		MEDIUM_DESCRIPTION = mEDIUM_DESCRIPTION;
	}
	public String getLC() {
		return LC;
	}
	public void setLC(String lC) {
		LC = lC;
	}
	public String getRECORD_TYPE() {
		return RECORD_TYPE;
	}
	public void setRECORD_TYPE(String rECORD_TYPE) {
		RECORD_TYPE = rECORD_TYPE;
	}
	private String	H_LEVEL	="";
	private String	LINE_NUMBER	="";
	private String	PARENT_ORU	="";
	private String	CHILD_ORU	="";
	private String	CODE	="";
	private String	MEDIUM_DESCRIPTION	="";
	private String	LC	="";
	private String	RECORD_TYPE	="";
	
	
}
