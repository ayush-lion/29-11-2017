package com.lion.iot.Service.Impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.LoginBean;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;
import com.lion.iot.Service.ServiceInterface;

public class ServiceImpl implements ServiceInterface {

	@Autowired
	DaoInterface di;

	@Override
	public void registration(String username,String usermobileNo, String useremailId, String userpassword) {
		System.out.println("aara h service p");
		di.registration(username, usermobileNo,useremailId, userpassword);
	}

	@Override
	public boolean secureLogin(String email, String password,HttpSession session) {
		List<UserRegistrationPojo> obj = di.secureLogin();
		
		if(obj.size()!=0) {
		for (UserRegistrationPojo login : obj) {
			if(login.getUseremailid().equals(email) && login.getUserpassword().equals(password)) {
				LoginBean user = new LoginBean();
				user.setUserid(login.getUserid());
				user.setUsername(login.getUsername());
				user.setUseremailid(login.getUseremailid());
				user.setUsermobileno(login.getUsermobileno());
				user.setUserpassword(login.getUserpassword());
				session.setAttribute("admin", user);
				return true;
				}
			}
		}
		return false;	
	}
	

	@Override
	public boolean secureLogin(String email, String password) {
		
		List<UserRegistrationPojo> obj = di.secureLogin();
		
		if(obj.size()!=0) {
		for (UserRegistrationPojo login : obj) {
			if(login.getUseremailid().equals(email) && login.getUserpassword().equals(password)) {
				LoginBean user = new LoginBean();
				user.setUserid(login.getUserid());
				user.setUsername(login.getUsername());
				user.setUseremailid(login.getUseremailid());
				user.setUsermobileno(login.getUsermobileno());
				user.setUserpassword(login.getUserpassword());
				return true;
				}
			}
		}
		return false;	
	}

	@Override
	public void insertdeviceInformation(String topic1, String message1) {
		di.insertdeviceInformation(topic1, message1);
	}

	@Override
	public List<UserDeviceInformationPojo> recentdata() {
	
		List<UserDeviceInformationPojo> device=	di.recentdata();
		return device;
	}
}