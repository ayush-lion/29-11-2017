package com.lion.iot.Pojo;

import javax.persistence.Column;

public class LoginBean {
	private Integer userid;
	private String username;
	private String useremailid;
	private String userpassword;
	private String  usermobileno;
	private String userotp;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremailid() {
		return useremailid;
	}
	public void setUseremailid(String useremailid) {
		this.useremailid = useremailid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsermobileno() {
		return usermobileno;
	}
	public void setUsermobileno(String usermobileno) {
		this.usermobileno = usermobileno;
	}
	public String getUserotp() {
		return userotp;
	}
	public void setUserotp(String userotp) {
		this.userotp = userotp;
	}
}
