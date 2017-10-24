package com.lion.iot.Controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Service.ServiceInterface;

@Controller
public class HomeController {

	@Autowired
	ServiceInterface si;

	@RequestMapping(value = "/UserDeviceInformationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String userDeviceSubscribe(@RequestBody String subscribe) throws ParseException, MqttException {
	
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(subscribe);

		String topicname = (String) json.get("name");
		String b = (String) json.get("value");
		
		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;
		String broker = "tcp://m13.cloudmqtt.com:17073";
		String password = "nEy1r63gevm0";
		char[] accessKey = password.toCharArray();
		String appEUI = "raoccbbo";

		for(int i=0;i<=10;i++) 
		{
				persistence = new MemoryPersistence();
				client = new MqttClient(broker, appEUI, persistence);
				conn = new MqttConnectOptions();
				conn.setCleanSession(true);
				conn.setPassword(accessKey);
				conn.setUserName(appEUI);
				client.connect(conn);
				System.out.println("connect ho gaya");
				MqttMessage messagetext = new MqttMessage(b.toString().getBytes());
				client.publish(topicname, messagetext);	
				client.subscribe(topicname);
		}	
		/*si.insertdeviceInformation(fanValue, washingmachineValue, bulbValue, lightValue, airconditionarValue,tubelightValue,cflValue);*/
		return "ok";
	}
	
	@RequestMapping(value = "/RegistrationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String regisytration(@RequestBody String insert) throws ParseException {
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);

		String username = (String) json.get("name");
		String useremailId = (String) json.get("emailId");
		String userpassword = (String) json.get("password");
		String usermobileNo = (String) json.get("mobileNo");
		String userotp = (String) json.get("otp");

		si.registration(username, useremailId, userpassword, usermobileNo, userotp);
		return "ok";
	}

	@RequestMapping(value = "/LoginPageUrl", method = RequestMethod.POST)
	@ResponseBody
	public String LoginPage(@RequestBody String retrive) {
		UserDeviceInformationPojo obj = new UserDeviceInformationPojo();
		return "ok";
	}
	
	@RequestMapping(value = "/SecureLoginUrl", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestBody String insert) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);
		String name = (String) json.get("name");
		String password = (String) json.get("password");

		if (si.secureLogin(name, password)) 
		{
			return "ok";
		} 
		else 
		{
			return "Incorrect LoginId or Password";
		}
	}
	
	@RequestMapping(value = "/SecureLogoutUrl", method = RequestMethod.POST)
	@ResponseBody
	public String Logout(@RequestBody String insert) throws ParseException 
	{
		return "Logout";
	}
}
