package com.lion.iot.Dao.Rdbms;

import org.hibernate.criterion.DetachedCriteria;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;

public class DaoRdbms extends DBConnectionDao implements DaoInterface{
  
	@Override
	public void insertdeviceInformation(String topic1, String message1) {
		UserDeviceInformationPojo obj = new UserDeviceInformationPojo();
		
		
		DetachedCriteria cre=DetachedCriteria.forClass(UserDeviceInformationPojo.class);
		java.util.List<UserDeviceInformationPojo> emp=hibernateTemplate.findByCriteria(cre);
		
		if(emp.size()==0) 
			{
			obj.setDevicename(topic1);
			obj.setState(message1);
			hibernateTemplate.save(obj);
			}
		else
			{
				for (UserDeviceInformationPojo us : emp) 
				{
					if(us.getDevicename().equals(topic1)) 
					{
						obj.setDevicename(topic1);
						obj.setState(message1);
						System.out.println("ye h value latest :" +message1);
						hibernateTemplate.update(obj);
					}	
				}		
			}
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
}
