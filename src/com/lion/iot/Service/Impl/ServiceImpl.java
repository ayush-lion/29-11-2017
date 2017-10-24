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
	public void insertdeviceInformation(String fanValue, String washingmachineValue, String bulbValue, String lightValue,
		String airconditionarValue, String tubelightValue, String cflValue) {
		di.insertdeviceInformation(fanValue,washingmachineValue,bulbValue,lightValue,airconditionarValue,tubelightValue,cflValue);
	}
	
/*	@Override
	public void publish() {
		String topicname = "fan";
		String b = "1";
		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;

		String broker = "tcp://m21.cloudmqtt.com:16336";
		String password = "4EkhqK7ma9Pa";
		char[] accessKey = password.toCharArray();
		String appEUI = "znvlpcqy";

		for(int i=0;i<=10;i++) 
		{
			try {
				persistence = new MemoryPersistence();
				client = new MqttClient(broker, appEUI, persistence);
				conn = new MqttConnectOptions();
				conn.setCleanSession(true);
				conn.setPassword(accessKey);
				conn.setUserName(appEUI);
				client.connect(conn);
				MqttMessage messagetext = new MqttMessage(b.toString().getBytes());
				client.publish(topicname, messagetext);				
			   } catch (Exception x)
			{
			x.printStackTrace();
			}
	}*/
}