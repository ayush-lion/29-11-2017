package  com.lion.iot.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.UserRegistrationPojo;
import com.lion.iot.Service.ServiceInterface;

public class ServiceImpl implements ServiceInterface {

	@Autowired
	DaoInterface di;

	@Override
	public void registration(String username, String useremailId,String userpassword, String usermobileNo, String userotp) {
		di.registration(username,useremailId,userpassword,usermobileNo,userotp);
	}
	
	@Override
	public boolean  secureLogin(String name, String password) {
		UserRegistrationPojo obj = new UserRegistrationPojo();
		if(obj != null) 
		{
			if(obj.getUserpassword().equals(password)) 
				return true;
			else
				return false;
		}
		return false;	
	}

	@Override
	public void insertdeviceInformation(String topic1, String message1) {
		di.insertdeviceInformation(topic1,message1);
	}	
}