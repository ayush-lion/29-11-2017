package com.lion.iot.Dao.Rdbms;


import org.springframework.orm.hibernate3.HibernateTemplate;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Pojo.IOTPojo;

public class DaoRdbms extends DBConnectionDao implements DaoInterface{

	@Override
	public void ins(String topic, String message) {
		IOTPojo obj=new IOTPojo();
		obj.setTopic(topic);
		obj.setMessage(message);
		hibernateTemplate.save(obj);	
	}
}
