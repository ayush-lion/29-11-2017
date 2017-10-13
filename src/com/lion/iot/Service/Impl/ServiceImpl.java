package com.lion.iot.Service.Impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;

import com.lion.iot.Dao.DaoInterface;
import com.lion.iot.Service.ServiceInterface;

public class ServiceImpl implements ServiceInterface {

	@Autowired
	DaoInterface di;

	@Override
	public void insert(String topic, String message) {
		di.ins(topic, message);
	}

	@Override
	public void publish() {
		String topicname = "/aayush/temprature";
		String b = "5.51%4.52C";
		MqttClient client;
		MemoryPersistence persistence;
		MqttConnectOptions conn;

		String broker = "tcp://m21.cloudmqtt.com:16336";
		String password = "4EkhqK7ma9Pa";
		char[] accessKey = password.toCharArray();
		String appEUI = "znvlpcqy";

		for (int i = 1; i <= 100; i++) {

			try {
				persistence = new MemoryPersistence();
				client = new MqttClient(broker, appEUI, persistence);
				conn = new MqttConnectOptions();
				conn.setCleanSession(true);
				conn.setPassword(accessKey);
				conn.setUserName(appEUI);
				client.connect(conn);

				if (client.isConnected()) {
					System.out.println("Connected..");
				} else {
					System.out.println("Unable to connect");
					System.exit(0);
				}

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				MqttMessage messagetext = new MqttMessage(b.toString().getBytes());
				client.publish(topicname, messagetext);
				
				//client.setCallback(callback);
				
				client.subscribe("#");
				
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}

}