package com.lion.iot.Controller;

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

import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Service.ServiceInterface;

@Controller
public class HomeController {

	@Autowired
	ServiceInterface si;

	@RequestMapping(value = "/SubscribeInformationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String userDeviceSubscribe(@RequestBody String subscribe) throws ParseException, MqttException {

		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;
		String broker = "tcp://m21.cloudmqtt.com:16336";
		String password = "4EkhqK7ma9Pa";
		char[] accessKey = password.toCharArray();
		String appEUI = "znvlpcqy";

		persistence = new MemoryPersistence();
		client = new MqttClient(broker, appEUI, persistence);
		conn = new MqttConnectOptions();
		conn.setCleanSession(true);
		conn.setPassword(accessKey);
		conn.setUserName(appEUI);
		client.connect(conn);
		System.out.println("device connected");

		client.subscribe("/name/harvey/#");
		System.out.println("subscribe has been sucessfully");

		client.setCallback(new MqttCallback() {

			public void messageArrived(String topic, MqttMessage message) throws Exception {
				String topic1 = topic;
				String message1 = message.toString();

				System.out.println("\nReceived a Message!" + "\n\t Topic:   " + topic1 + "\n\t Message: "
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

		if(username != null && useremailId != null && userpassword != null && usermobileNo != null) 
		{
		si.registration(username, useremailId, userpassword, usermobileNo, userotp);
		return "ok";
		}
		else
		{
		return "fill all fields";	
		}
	}

	@RequestMapping(value = "/LoginPageUrl", method = RequestMethod.POST)
	@ResponseBody
	public String LoginPage(@RequestBody String retrive) {
		
		return "/login";
	}

	@RequestMapping(value = "/SecureLoginUrl", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestBody String insert) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);
		String name = (String) json.get("name");
		String password = (String) json.get("password");

		if (si.secureLogin(name, password)) {
			return "login successFully";
		} 
		else 
		{
			return "Incorrect LoginId or Password";
		}
	}

	@RequestMapping(value = "/SecureLogoutUrl", method = RequestMethod.POST)
	@ResponseBody
	public String Logout(@RequestBody String insert) throws ParseException {
		return "/Logout";
	}

	@RequestMapping(value = "/PublishInformationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String userDevicePublish(@RequestBody String subscribe) throws ParseException, MqttException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(subscribe);

		String topicname = (String) json.get("name");
		String b = (String) json.get("value");

		topicname="/name/harvey/state/"+topicname;
		
		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;
		String broker = "tcp://m21.cloudmqtt.com:16336";
		String password = "4EkhqK7ma9Pa";
		char[] accessKey = password.toCharArray();
		String appEUI = "znvlpcqy";

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
		return "ok";
	}
}