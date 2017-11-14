package com.lion.iot.Pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "userregistration")

public class UserRegistrationPojo implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="userid")
	private Integer userid;

	@Column(name="username")
	private String username;
	
	@Column(name="useremailid")
	private String useremailid;
	
	@Column(name="userpassword")
	private String userpassword;
	
	@Column(name="usermobileno")
	private String  usermobileno;
	
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
}
