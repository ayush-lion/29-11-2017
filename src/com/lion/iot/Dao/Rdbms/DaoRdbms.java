package com.lion.iot.Dao.Rdbms;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;

public class DaoRdbms extends DBConnectionDao implements DaoInterface{

	
	@Override
	public void insertdeviceInformation(String fanValue, String washingmachineValue, String bulbValue, String lightValue,
			String airconditionarValue, String tubelightValue, String cflValue) {
		
		
		
	}   
	
	@Override
	public void registration(String username, String useremailId,String userpassword,String usermobileNo, String userotp) {
		
		UserRegistrationPojo obj = new UserRegistrationPojo();

		obj.setUsername(username);
		obj.setUseremailid(useremailId);
		obj.setUserpassword(userpassword);
		obj.setUsermobileno(usermobileNo);
		obj.setUserotp(userotp);
		
		hibernateTemplate.save(obj);
	}	
	
	@Override
	public void ins(String topic, String message) {
		UserDeviceInformationPojo obj = new UserDeviceInformationPojo();                                                      
		obj.setTopic(topic);
		obj.setMessage(message);
		hibernateTemplate.save(obj);
	}
	
	@Override
	public void save(String topic, String message) {
		UserDeviceInformationPojo obj=new UserDeviceInformationPojo();
		
		obj.setTopic(topic);
		obj.setMessage(message);
		hibernateTemplate.save(obj);		
	}                         
}
