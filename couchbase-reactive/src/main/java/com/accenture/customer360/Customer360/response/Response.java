package com.accenture.customer360.Customer360.response;

public class Response {

	private boolean daoResponseStatus;
	private String resCode;
	private String resMsg;

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public boolean isDaoResponseStatus() {
		return daoResponseStatus;
	}

	public void setDaoResponseStatus(boolean daoResponseStatus) {
		this.daoResponseStatus = daoResponseStatus;
	}

	@Override
	public String toString() {
		return "Response [daoResponseStatus=" + daoResponseStatus + ", resCode=" + resCode + ", resMsg=" + resMsg + "]";
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

}
