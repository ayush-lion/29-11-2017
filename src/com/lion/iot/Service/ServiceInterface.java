package com.lion.iot.Service;

import java.util.List;

import javax.servlet.http.HttpSession;
import com.lion.iot.Pojo.LoginNewPojo;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.appliencePojo;

public interface ServiceInterface {

	void registration(String username,String usermobileNo, String useremailId, String userpassword);

	boolean secureLogin(String email, String password, HttpSession session);

	void insertdeviceInformation(String topic1, String message1);

	List<UserDeviceInformationPojo> recentdata();

	boolean secureLogin(String email, String password);

	boolean newRegistration(String name, String email);

	List<LoginNewPojo> getEntries();

	void addDevice(String device, String value);

	void remove(int id);

	List<appliencePojo> getAll();
}
