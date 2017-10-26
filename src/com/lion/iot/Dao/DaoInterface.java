package com.lion.iot.Dao;

public interface DaoInterface {
	
	void registration(String username, String useremailId, String usermobileNo, String userotp, String userpassword);
	void insertdeviceInformation(String topic1, String message1);
}
