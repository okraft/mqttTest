package com.wanggg.mqtt.test.mqtt.publish;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 监听消息回调类
 * 
 * @author Wangguanguan
 * @version Date: 2016年9月1日 下午5:46:47 <br/>
 * @since
 */

public class MQTTCallback implements MqttCallback {
	
	/* (non-Javadoc)
	 * 与MQTT代理服务器断开连接时调用
	 */
	public void connectionLost(Throwable cause) {
		System.out.println("MQTT服务器连接断开,请重新连接.....");
	}

	/* (non-Javadoc)
	 * 从MQTT服务器接收到消息时调用
	 */
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("消息接收成功，准备处理....");
		System.out.println("topic : " + topic + " , message " + new String(message.getPayload()) + " Qos: " + message.getQos());
	}

	/* (non-Javadoc)
	 * 消息传递到服务器
	 */
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("消息已成功发送到MQTT服务器");
	}

}
