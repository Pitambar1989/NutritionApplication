package com.nutrition.model;

import java.util.Date;

import com.sun.istack.NotNull;

public class WeightLossRequest {
	@NotNull
	private Date fromDate;
	@NotNull
	private Date toDate;
	@NotNull
	private String userName;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
