package models;

import java.io.Serializable;

public class User implements Serializable{

	//プロパティ
	private int userId;
	private String userName;
	private String loginPw;

	//ゲッター・セッター
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

}
