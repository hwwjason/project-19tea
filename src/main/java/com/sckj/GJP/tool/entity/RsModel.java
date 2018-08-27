package com.sckj.GJP.tool.entity;

import java.util.Map;

public class RsModel {


	private String errormessage;
	private String errorcode;
	private String requestid;
	private String iserror;
	private Map response;

	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public String getIserror() {
		return iserror;
	}
	public void setIserror(String iserror) {
		this.iserror = iserror;
	}
	public Map getResponse() {
		return response;
	}
	public void setResponse(Map response) {
		this.response = response;
	}
}
