package com.wanggg.mqtt.test.mqtt.publish;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 发布消息-使用block阻塞客户端
 * 
 * @author Wangguanguan
 * @version Date: 2016年9月8日 上午10:46:43 <br/>
 * @since
 */
public class Publish {

	public static final String broker = "tcp://10.200.17.194:1883";
	public static final String TOPIC = "toclient/124";
	public static final String TOPIC125 = "toclient/125";
	private static final String clientid = "test-192.168.221.111";

	private IMqttClient client;
	private String userName = "zhangsan";
	private String passWord = "123456";
	
	public Publish(){
		try {
			client = new MqttClient(broker, clientid);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接Emqttd代理服务器
	 * @author Wangguanguan
	 * Date:2016年9月8日上午10:56:16
	 */
	public void connect(){
		MqttConnectOptions options = new MqttConnectOptions();
		String[] serverURIs = {"tcp://10.200.17.194:1883","tcp://10.200.17.194:2883"};
		options.setServerURIs(serverURIs);
		options.setCleanSession(false);
		options.setUserName(userName);
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
	
	public void disconnect(){
		try {
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String topic,String message){
		sendMessage(topic, message.getBytes());
	}
	
	public void sendMessage(String topic,byte[] payload){
		try {
			MqttMessage message = new MqttMessage(payload);
			message.setQos(1);
			message.setRetained(true);
			MyMqttPublish pubMsg = new MyMqttPublish("testBridge1/2", message);
			System.out.println("HeaderLength = " + pubMsg.getHeaderLength() );		//固定头部和可变头部的总长度
			System.out.println("VariableHeaderLength = " + (pubMsg.getHeaderLength() -2) );		//可变头部长度方式1：固定头部和可变头部的总长度 -2
			System.out.println("VariableHeaderLength = " + pubMsg.getVariableHeader().length);	//可变头部长度方式2
			System.out.println("PayloadLength = " + pubMsg.getPayloadLength());		//有效载何长度
			System.out.println("PayloadLength = " + payload.length);		//有效载何长度
			client.publish(topic, message);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		Publish publish = new Publish();
		publish.connect();
		for (int i = 1; i <= 10; i++) {
			String s = "第"+ (i) +"天，北京温度25度";
			/* 发送空消息
			 if(i==10){
				s = "第"+ (i) +"天";
				publish.sendMessage("testBridge1/2", "");
				break;
			}*/
			publish.sendMessage("testBridge1/2", s);
		}
		
		/*for (int i = 1; i <= 100; i++) {
			try {
				Thread.sleep(2000);
				String s = "第"+ (i) +"天，北京温度25度";
				System.out.println(s);
				publish.sendMessage("testBridge1/2", s);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 发送消息Emq最大为65536,64k，可以进行调整
			byte[] msg = new byte[65537];
			publish.sendMessage("/test/beijing/temperature1", msg);
			
		}*/
	}
	
}
