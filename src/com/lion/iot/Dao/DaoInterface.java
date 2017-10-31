package com.lion.iot.Dao;

import java.util.List;

import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;

public interface DaoInterface {
	
	void registration(String username, String useremailId, String usermobileNo, String userpassword);
	void insertdeviceInformation(String topic1, String message1);
	List<UserRegistrationPojo> secureLogin();
	List<UserDeviceInformationPojo> recentdata();
}
