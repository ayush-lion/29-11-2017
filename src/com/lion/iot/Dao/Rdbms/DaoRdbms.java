package com.lion.iot.Dao.Rdbms;

import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.LoginNewPojo;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;
import com.lion.iot.Pojo.appliencePojo;

public class DaoRdbms extends DBConnectionDao implements DaoInterface {

	@Override
	public  void insertdeviceInformation(String topic1, String message1) {
	
	/*	int topi = topic1.lastIndexOf('/')+1;
		String abc  = topic1.substring(topi, topic1.length());*/
	
		UserDeviceInformationPojo obj = new UserDeviceInformationPojo();
		DetachedCriteria cre = DetachedCriteria.forClass(UserDeviceInformationPojo.class);
		java.util.List<UserDeviceInformationPojo> emp = hibernateTemplate.findByCriteria(cre.add(Restrictions.like("devicename", topic1, MatchMode.EXACT)));
		
		if (emp.size() == 0) {
			obj.setDevicename(topic1);
			obj.setState(message1);
			hibernateTemplate.save(obj);
		} 
		else 
		{
		for (UserDeviceInformationPojo us : emp) 
		{
			obj.setUserid(us.getUserid());
			obj.setDevicename(topic1);
			obj.setState(message1);
			hibernateTemplate.update(obj);
		}
     }
  }

	@Override
	public void registration(String username,String usermobileNo,String useremailId, String userpassword) {
		UserRegistrationPojo obj = new UserRegistrationPojo();
		obj.setUsername(username);
		obj.setUsermobileno(usermobileNo);
		obj.setUseremailid(useremailId);
		obj.setUserpassword(userpassword);
		hibernateTemplate.save(obj);
	}

	@Override
	public List<UserRegistrationPojo> secureLogin() {
		UserRegistrationPojo obj = new UserRegistrationPojo();
		DetachedCriteria cre = DetachedCriteria.forClass(UserRegistrationPojo.class);
		java.util.List<UserRegistrationPojo> emp = hibernateTemplate.findByCriteria(cre);
		return emp;
	}
	
	@Override
	public List<LoginNewPojo> newRegistration(String name, String email) {
			
		LoginNewPojo obj=new LoginNewPojo();
		
		DetachedCriteria cre = DetachedCriteria.forClass(LoginNewPojo.class);
		java.util.List<LoginNewPojo> emp = hibernateTemplate.findByCriteria(cre.add(Restrictions.like("email", email, MatchMode.EXACT)));
			
		if (emp.size() == 0) {
			obj.setName(name);
			obj.setEmail(email);
			hibernateTemplate.save(obj);
		} 
		else 
		{
		for (LoginNewPojo us : emp) 
		{
		System.out.println("already exist");
		break;
		}
	 }
	return emp;   
	}
	
	@Override
	public void remove(int id) {
	
		
	appliencePojo device = hibernateTemplate.get(appliencePojo.class, id);
	hibernateTemplate.delete(device);
	
	
	}
	
	@Override
	public List<UserDeviceInformationPojo> recentdata() {
		DetachedCriteria cre = DetachedCriteria.forClass(UserDeviceInformationPojo.class);
		java.util.List<UserDeviceInformationPojo> device = hibernateTemplate.findByCriteria(cre);
		return device;
	}

	@Override
	public List<LoginNewPojo> getEntries() {
		DetachedCriteria cre = DetachedCriteria.forClass(LoginNewPojo.class);
		List<LoginNewPojo> obj = hibernateTemplate.findByCriteria(cre);
		return obj;	
	}

	@Override
	public void addDevice(String device, String value) {
		// TODO Auto-generated method stub
		appliencePojo obj = new appliencePojo();
		obj.setDevice(device);
		obj.setState(value);
		hibernateTemplate.save(obj);
	}

	@Override
	public List<appliencePojo> getAll() {
		DetachedCriteria cre = DetachedCriteria.forClass(appliencePojo.class);
		List<appliencePojo> applienceList=hibernateTemplate.findByCriteria(cre);
		return applienceList;
	}
}
