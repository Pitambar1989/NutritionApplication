package com.nutrition.model;

import java.io.Serializable;
import java.util.List;

public class WeightLossResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private List<WeightLoss> list;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<WeightLoss> getList() {
		return list;
	}

	public void setList(List<WeightLoss> list) {
		this.list = list;
	}

}
