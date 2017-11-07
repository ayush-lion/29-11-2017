package com.lion.iot.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lion.iot.Pojo.LoginDetail;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;
import com.lion.iot.Service.ServiceInterface;

@Controller
public class HomeController {

	@Autowired
	ServiceInterface si;

	@RequestMapping(value = "/Login")
	public String login() {
		return "login";
	}
	
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

				System.out.println("hello");

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
	
	
	@RequestMapping(value = "/RegistrationOuterUrl", method = RequestMethod.POST)
	@ResponseBody
	public String registrationOuter(@RequestBody String insert) throws ParseException {

		System.out.println("hello signup");

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(insert);

		String username = (String) json.get("name");
		String useremailId = (String) json.get("email");
		String usermobileNo = (String) json.get("mobile");
		String userpassword = (String) json.get("password");

		if (username != null && useremailId != null && userpassword != null && usermobileNo != null)
		{
			si.registration(username, useremailId, userpassword, usermobileNo);
			return "ok";
		} 
		else 
		{
			return "fill all fields";
		}
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
		System.out.println("publish ho gaya h bhai");
		return "ok";
	}
	
	@RequestMapping(value = "/LoginPageUrl", method = RequestMethod.POST)
	@ResponseBody
	public String LoginPage(@RequestBody String retrive) {

		return "ok";
	}

	@RequestMapping(value = "/secureLoginUrl", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginDetail login, HttpSession session, Model model) throws ParseException {
		System.out.println("hello login=====================" + login.getEmail());
		String target = "";
		String email = login.getEmail();
		String password = login.getPassword();
		if (email.length() != 0 && password.length() != 0) {
			if (si.secureLogin(email, password, session)) {
				System.out.println("successs");
				target = "details";

				List<UserDeviceInformationPojo> device = si.recentdata();
				for (UserDeviceInformationPojo devicedata : device) {
					System.out.println(devicedata.getDevicename());
					System.out.println(devicedata.getState());
				}
				model.addAttribute("device", device);

				return target;
			} else
				{
				target = "redirect:/Login";
				}
		}
		return "redirect:/Login";
	}
	
	@RequestMapping(value = "/SecureLogoutUrl", method = RequestMethod.POST)
	@ResponseBody
	public String Logout(@RequestBody String insert) throws ParseException {
		return "/Logout";
	}

	
	
	@RequestMapping(value = "/PublishInformationUrl", method = RequestMethod.POST)
	@ResponseBody
	public String userDevicePublish(String topicname, String b) throws ParseException, MqttException {
		
		System.out.println("hit hora h : "+topicname+"\t"+b);
		topicname = "/name/harvey/state/" + topicname;
		System.out.println("ye h value: "+topicname);
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
		System.out.println("publish ho gaya h bhai");
		return "ok";
	}
	
	@RequestMapping(value = "/RegistrationUrl")
	public String registration(@ModelAttribute UserRegistrationPojo user) {

		System.out.println("contrtoller");
		String name = user.getUsername();
		String email = user.getUseremailid();
		String password = user.getUserpassword();
		String mobile = user.getUsermobileno();
		String target = "";
		if (name != null && email != null && password != null && mobile != null) {
			si.registration(name, mobile,email,password);
			System.out.println("=============signup");
			target = "redirect:/Login";
		} else {
			return "fill all fields";
		}
		return target;
	}
	
	
	@RequestMapping(value = "/recentDevice", method = RequestMethod.POST)
	public String recent(Model map) {
		return "ok";
	}
}