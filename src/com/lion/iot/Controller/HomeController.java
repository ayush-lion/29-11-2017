package com.lion.iot.Controller;

import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lion.iot.Service.ServiceInterface;

@Controller
public class HomeController {
	
	@Autowired
	ServiceInterface si;

	@RequestMapping(value="AjaxUrl")
	public @ResponseBody String insert(String topic, String message){
		si.insert(topic,message);
		return "ok";
	}
	
	
	@RequestMapping(value="PublishUrl")
	public @ResponseBody String publish () throws MqttException, URISyntaxException{
		si.publish();
		return "ok";
	}
	
}
