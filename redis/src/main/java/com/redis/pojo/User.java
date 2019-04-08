package com.redis.pojo;


public class User {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName ==null ? null:userName.trim();
	}
	
}
