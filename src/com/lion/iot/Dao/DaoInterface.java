package com.lion.iot.Dao;

public interface DaoInterface {

	void ins(String topic, String message);
	void save(String topic, String message);
	
	void registration(String username, String useremailId, String usermobileNo, String userotp, String userpassword);
	void insertdeviceInformation(String fan, String washingmachine, String bulb, String light, String airconditionar, String tubelight, String cfl);
}
