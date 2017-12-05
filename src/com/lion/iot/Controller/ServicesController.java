package com.lion.iot.Controller;

import java.util.List;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.google.gson.Gson;
import com.lion.iot.Pojo.LoginNewPojo;
import com.lion.iot.Service.ServiceInterface;

@Controller
@EnableWebMvc

public class ServicesController {

	@Autowired
	ServiceInterface si;
	
	@RequestMapping(value = "/SubscribeInformationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String userDeviceSubscribe(@RequestBody String subscribe) throws ParseException, MqttException {

		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;
		
		String broker = "tcp://m14.cloudmqtt.com:11688";
		String password = "-8VuRqMCmUaG";
		char[] accessKey = password.toCharArray();
		String appEUI = "bhunimiy";
		
		persistence = new MemoryPersistence();
		client = new MqttClient(broker, appEUI, persistence);
		conn = new MqttConnectOptions();
		conn.setCleanSession(true);
		conn.setPassword(accessKey);
		conn.setUserName(appEUI);
		client.connect(conn);
		System.out.println("device connected");
		System.out.println("subscribe has been sucessfully");

		client.setCallback(new MqttCallback() {
			
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				String topic1 = topic;
				String message1 = message.toString();
				
				System.out.println("\n Received a Message!" + "\n\t Topic:   " + topic1 + "\n\t Message: "
				+ new String(message.getPayload()));
				
			   si.insertdeviceInformation(topic1, message1);
			}
			public void connectionLost(Throwable cause) {
				System.out.println("Connection to Solace broker lost!" + cause.getMessage());
			}
			
			public void deliveryComplete(IMqttDeliveryToken token) {
				System.out.println("delivery Complete");
			}
		});
		client.subscribe("/device/control/#");
		return "ok";
	}
	
	@RequestMapping(value = "/SecureOuterLoginUrl", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestBody String insert) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);
		String email = (String) json.get("email");
		String password = (String) json.get("password");
		System.out.println("hit hora h");
		
		if (si.secureLogin(email, password)) 
		{
			return "ok";
		} 
		else 
		{
			return "Incorrect LoginId or Password";
		}
	}
	
	@RequestMapping(value = "/addapplience", method = RequestMethod.POST)
	@ResponseBody String addapplience(@RequestBody String device) throws ParseException {
	JSONParser parser = new JSONParser();
	JSONObject json = (JSONObject) parser.parse(device);	
	
	String appliencename=(String) json.get("name");
	String appliencestate=(String) json.get("state");
	
	si.insertdevice(appliencename, appliencestate);	
	return "ok";
	}
	
	@RequestMapping(value = "/RegistrationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String registrationOuter(@RequestBody String insert) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);
		String name = (String) json.get("name");
		String email = (String) json.get("email");
		String password = (String) json.get("password");
		String mobileNo = (String) json.get("mobile");

		if (name != null && mobileNo != null && email != null && password != null)
		{
			si.registration(name, mobileNo,email, password);
			return "ok";
		} 
		else 
		{
			return "fill all fields";
		}
	}
	
	@RequestMapping(value = "/RegistrationNewOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String registrationNewOuter(@RequestBody String insert) throws Exception {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);
		
		String name = (String) json.get("name");
		String email = (String) json.get("email");
		
		if (name != null && email != null)
		{
			if(si.newRegistration(name,email))
			{
				return "success !";	
			}
			else 
			{
				return "email id already exist !";
			}
		}
		else 
		{
		return "fill all fields";
		}
	}
		
	@RequestMapping(value = "/getAllEntries" , method = RequestMethod.GET)
	@ResponseBody
	public String getEntries() 
	{
		List<LoginNewPojo> obj=si.getEntries();
		String json = new Gson().toJson(obj);
		System.out.println(obj);
		return json;
	}

	@RequestMapping(value = "/PublishInformationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String userDevicePublish(@RequestBody String subscribe) throws ParseException, MqttException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(subscribe);

		String topicname = (String) json.get("name");
		String b = (String) json.get("value");

		topicname = "/name/harvey/state/" + topicname;																																																																																			

		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;
		String broker = "tcp://m14.cloudmqtt.com:11688";
		String password = "-8VuRqMCmUaG";
		char[] accessKey = password.toCharArray();
		String appEUI = "bhunimiy";

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
		System.out.println("publish ho gaya h bhai");
		return "ok";
	}	
}
