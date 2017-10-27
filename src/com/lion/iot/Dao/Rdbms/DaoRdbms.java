package com.lion.iot.Dao.Rdbms;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;

public class DaoRdbms extends DBConnectionDao implements DaoInterface {

	@Override
	public void insertdeviceInformation(String topic1, String message1) {
		int topi = topic1.lastIndexOf('/')+1;
		String abc  = topic1.substring(topi, topic1.length());
		
		UserDeviceInformationPojo obj = new UserDeviceInformationPojo();
		DetachedCriteria cre = DetachedCriteria.forClass(UserDeviceInformationPojo.class);
		java.util.List<UserDeviceInformationPojo> emp = hibernateTemplate.findByCriteria(cre.add(Restrictions.like("devicename", abc, MatchMode.EXACT)));

		if (emp.size() == 0) {
			obj.setDevicename(abc);
			obj.setState(message1);
			hibernateTemplate.save(obj);
		} else {
			for (UserDeviceInformationPojo us : emp) {
				obj.setId(us.getId());
				obj.setDevicename(abc);
				obj.setState(message1);
				hibernateTemplate.update(obj);
			}
		}
	}

	@Override
	public void registration(String username, String useremailId, String userpassword, String usermobileNo,
			String userotp) {
		UserRegistrationPojo obj = new UserRegistrationPojo();
		obj.setUsername(username);
		obj.setUseremailid(useremailId);
		obj.setUserpassword(userpassword);
		obj.setUsermobileno(usermobileNo);
		obj.setUserotp(userotp);
		hibernateTemplate.save(obj);
	}

	@Override
	public List<UserRegistrationPojo> secureLogin() {
		UserRegistrationPojo obj = new UserRegistrationPojo();
		DetachedCriteria cre = DetachedCriteria.forClass(UserRegistrationPojo.class);
		java.util.List<UserRegistrationPojo> emp = hibernateTemplate.findByCriteria(cre);
		
		return emp;
	}
}
