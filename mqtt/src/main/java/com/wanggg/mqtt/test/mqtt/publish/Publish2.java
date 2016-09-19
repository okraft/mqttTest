package com.wanggg.mqtt.test.mqtt.publish;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * 发布消息-使用non-block阻塞客户端
 * 
 * @author Wangguanguan
 * @version Date: 2016年9月8日 上午10:46:43 <br/>
 * @since
 */
public class Publish2 {

	public static final String broker = "tcp://10.200.17.194:1883";
	public static final String TOPIC = "toclient/124";
	public static final String TOPIC125 = "toclient/125";
	private static final String clientid = "test-192.168.221.111";

	private IMqttAsyncClient client;
	private String userName = "zhangsan";
	private String passWord = "123456";
	
	public Publish2(){
		try {
			client = new MqttAsyncClient(broker, clientid);
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
		/*String[] serverURIs = {"tcp://10.200.17.194:1883","tcp://10.200.17.194:2883"};
		options.setServerURIs(serverURIs);*/
		options.setCleanSession(false);
		options.setUserName(userName);
		options.setPassword(passWord.toCharArray());
		// 设置超时时间，默认30s
		options.setConnectionTimeout(10);
		// 设置会话keep alive心跳时间，默认 60 seconds
		options.setKeepAliveInterval(20);
		client.setCallback(new MQTTCallback());
		try {
			IMqttToken mqttToken = client.connect(options);
			mqttToken.waitForCompletion();
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
		try {
			client.publish(topic, message.getBytes(), 1, true);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void sendMessage(String topic,byte[] message){
		try {
			client.publish(topic, message, 1, true);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) throws InterruptedException, MqttException {
		Publish2 publish = new Publish2();
		publish.connect();
		System.out.println("开发发送------------》");
		for (int i = 1; i <= 10; i++) {
			Thread.sleep(2000);
			String s = "第"+ (i) +"天，北京温度25度";
			System.out.println(s);
			publish.sendMessage("wanggg1/2/2", s);
			/* 发送消息Emq最大为65536,64k，可以进行调整
			byte[] msg = new byte[65537];
			publish.sendMessage("/test/beijing/temperature1", msg);*/
			
		}
	}
	
}
