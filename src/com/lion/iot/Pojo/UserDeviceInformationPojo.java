package com.lion.iot.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Entity
@Table(name = "iotdata")
public class UserDeviceInformationPojo {
	@Id
	@GeneratedValue
	
	@Column(name="id")
	private Integer id;

	@Column(name="topic")
	private String topic;
	
	@Column(name="message")
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}