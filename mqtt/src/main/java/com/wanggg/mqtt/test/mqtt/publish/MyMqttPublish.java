package com.wanggg.mqtt.test.mqtt.publish;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;

/**
 * 继承MqttPublish类，获取可变头部长度
 * 
 * @author Wangguanguan 
 * @version 	
 * @since 
 */

public class MyMqttPublish extends MqttPublish{
	
	public MyMqttPublish(String name, MqttMessage message) {
		super(name, message);
	}

	/**
	 * @param info
	 * @param data
	 * @throws MqttException
	 * @throws IOException
	 */
	public MyMqttPublish(byte info, byte[] data) throws MqttException, IOException {
		super(info, data);
	}

	
	public byte[] getVariableHeader() throws MqttException {
		return super.getVariableHeader();
	}
	
}
