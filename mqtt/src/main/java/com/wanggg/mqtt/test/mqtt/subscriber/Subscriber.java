package com.wanggg.mqtt.test.mqtt.subscriber;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 订阅消息
 * 
 * @author Wangguanguan
 * @version Date: 2016年9月8日 下午1:50:25 <br/>
 * @since
 */

public class Subscriber {

	public static final String broker = "tcp://10.200.18.87:2883";
	private static final String clientid = "client128";
	private MqttClient client;
	private MqttConnectOptions options;
	private String userName = "zhangsan";
	private String passWord = "123456";

	public Subscriber() {
		try {
			client = new MqttClient(broker, clientid, new MemoryPersistence());
			connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		// MQTT的连接设置
		options = new MqttConnectOptions();
		// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
		options.setCleanSession(false);
		// 设置连接的用户名
		options.setUserName(userName);
		// 设置连接的密码
		options.setPassword(passWord.toCharArray());
		// 设置超时时间，默认30s
		options.setConnectionTimeout(10);
		// 设置会话keep alive心跳时间，默认 60 seconds
		options.setKeepAliveInterval(20);
		client.setCallback(new MQTTCallback());
		try {
			client.connect(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void subscriber(String[] topics,int[] qos){
		try {
			client.subscribe(topics, qos);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Subscriber subscriber = new Subscriber();
		String[] topics = {"/test/beijing/temperature"};
		int[]  qos = {2};
		subscriber.subscriber(topics, qos);
	}

}
