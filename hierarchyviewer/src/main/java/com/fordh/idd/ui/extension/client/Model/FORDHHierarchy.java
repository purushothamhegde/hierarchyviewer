package com.fordh.idd.ui.extension.client.Model;

import java.io.Serializable;

public class FORDHHierarchy  implements Serializable  {

	private String	Code	;
	private String	Name	;
	private String	Type;
	

	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

}
