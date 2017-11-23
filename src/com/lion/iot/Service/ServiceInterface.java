package com.lion.iot.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.lion.iot.Pojo.LoginNewPojo;
import com.lion.iot.Pojo.UserDeviceInformationPojo;

public interface ServiceInterface {

	void registration(String username,String usermobileNo, String useremailId, String userpassword);

	boolean secureLogin(String email, String password, HttpSession session);

	boolean insertdeviceInformation(String topic1, String message1);

	List<UserDeviceInformationPojo> recentdata();

	boolean secureLogin(String email, String password);

	boolean newRegistration(String name, String email);

	List<LoginNewPojo> getEntries();
}
