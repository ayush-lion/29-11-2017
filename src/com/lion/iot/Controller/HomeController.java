package com.lion.iot.Controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.lion.iot.Pojo.LoginDetail;
import com.lion.iot.Pojo.UserDeviceInformationPojo;
import com.lion.iot.Pojo.UserRegistrationPojo;
import com.lion.iot.Pojo.appliencePojo;
import com.lion.iot.Service.ServiceInterface;

@Controller
@EnableWebMvc

public class HomeController {

	@Autowired
	ServiceInterface si;

	@RequestMapping(value = "/AjaxUrl", method = RequestMethod.POST)
	@ResponseBody
	public String jsSubscribtion(String topic,String message) {
		System.out.println("hit to hua");
		
		System.out.println(topic);
		System.out.println(message);
		return "ok";
	}
	
	@RequestMapping(value = "/Login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/LoginPageUrl", method = RequestMethod.POST)
	@ResponseBody
	public String LoginPage(@RequestBody String retrive) {
		return "ok";
	}
	
	@RequestMapping(value = "/removedevice")
	public String remove(int id) {
		si.remove(id);
		return "/userPanel";
	}
	
	@RequestMapping(value = "/removeDevice", method = RequestMethod.POST)
	@ResponseBody
	public String remove(Integer id) {	
	
	si.remove(id);	
	return"ok";	
	}

	@RequestMapping(value = "/makeProfile", method = RequestMethod.POST)
	@ResponseBody
	public String addDevice(String device , String value) {
		
		System.out.println("controller make profile");
		si.addDevice(device, value);
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
				target = "userPanel";
				
				List<UserDeviceInformationPojo> device = si.recentdata();
				for (UserDeviceInformationPojo devicedata : device) {
					System.out.println(devicedata.getDevicename());
					System.out.println(devicedata.getState());
				}
				List<appliencePojo> devices=si.getAll();
				model.addAttribute("devices", devices);
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
	
	@RequestMapping(value = "/Publish", method = RequestMethod.POST)
	@ResponseBody
	public String userDevicePublish(String value) throws ParseException, MqttException {
		
		System.out.println("service hit to huii");
		String topicname = "/device/control/";
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
		MqttMessage messagetext = new MqttMessage(value.toString().getBytes());
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
			System.out.println("signup");
			target = "redirect:/Login";
		} 
		else 
		{
			return "fill all fields";
		}
		return target;
	}
	
	@RequestMapping(value = "/recentDevice", method = RequestMethod.POST)
	public String recent(Model map) {
		return "ok";
	}
}