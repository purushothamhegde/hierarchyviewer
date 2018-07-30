package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;
import java.util.List;

public class LegalEntityList implements Serializable {

	private List<LegalEntity> legalEntityList ;

	public List<LegalEntity> getLegalEntityList() {
		return legalEntityList;
	}

	public void setLegalEntityList(List<LegalEntity> legalEntityList) {
		this.legalEntityList = legalEntityList;
	}
	
	
}
