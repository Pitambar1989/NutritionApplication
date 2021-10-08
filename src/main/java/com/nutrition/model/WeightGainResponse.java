package com.nutrition.model;

import java.util.List;

public class WeightGainResponse {
	private String userName;
	private List<WeightGain> list;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<WeightGain> getList() {
		return list;
	}

	public void setList(List<WeightGain> list) {
		this.list = list;
	}

}
