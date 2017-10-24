package com.lion.iot.Service;

public interface ServiceInterface {
	
	void registration(String username, String useremailId,String userpassword,String usermobileNo, String userotp);
	boolean secureLogin(String name, String password);
	void insertdeviceInformation(String fanValue, String washingmachineValue, String bulbValue, String lightValue, String airconditionarValue,
			String tubelightValue, String cflValue);
}
