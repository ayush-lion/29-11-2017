package com.lion.iot.Service;

import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface ServiceInterface {

	void insert(String topic, String message);

	void publish();
}
