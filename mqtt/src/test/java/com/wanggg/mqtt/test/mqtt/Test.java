package com.wanggg.mqtt.test.mqtt;

/**
 * 类功能描述(必填)<br/>
 * 
 * @author Wangguanguan
 * @version Date: 2016年8月30日 下午3:19:47 <br/>
 * @since
 */

public class Test {

	public static void main(String[] args) {
		byte publishFixHeader = 50;// 00110010

		doGetBit(publishFixHeader);
		int ori = 224;// 1110000,DISCONNECT ,Message Type (14)
		byte flag = (byte) ori; // 有符号byte
		doGetBit(flag);
		doGetBit_v2(ori);
		
		
		byte maxByte=0x7f;
        System.out.println("最大byte：0x7f  真值："+maxByte);
	}

	public static void doGetBit(byte flags) {
		boolean retain = (flags & 1) > 0;
		int qosLevel = (flags & 0x06) >> 1;
		boolean dupFlag = (flags & 8) > 0;
		int messageType = (flags >> 4) & 0x0f;

		System.out.format("Message type:%d, DUP flag:%s, QoS level:%d, RETAIN:%s\n", messageType, dupFlag, qosLevel,
				retain);
	}

	public static void doGetBit_v2(int flags) {
		boolean retain = (flags & 1) > 0;
		int qosLevel = (flags & 0x06) >> 1;
		boolean dupFlag = (flags & 8) > 0;
		int messageType = flags >> 4;

		System.out.format("Message type:%d, DUP flag:%s, QoS level:%d, RETAIN:%s\n", messageType, dupFlag, qosLevel,
				retain);
	}

}
