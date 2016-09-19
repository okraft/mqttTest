package com.wanggg.mqtt.test.mqtt;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 类功能描述(必填)<br/>
 * 
 * @author Wangguanguan
 * @version Date: 2016年8月30日 下午4:23:18 <br/>
 * @since
 */

public class Test3 {

	public static void main(String[] args) throws IOException {
		// 模拟服务器/客户端写入
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(arrayOutputStream);

		// 模拟服务器/客户端解析
		length2Bytes(dataOutputStream, 128);
	}

	/**
	 * int类型长度解析为1-4个字节
	 * 
	 * @param out
	 * @param length
	 * @throws IOException
	 */
	private static void length2Bytes(OutputStream out, int length) throws IOException {
		int val = length;
		do {
			int digit = val % 128;
			val = val / 128;
			if (val > 0)
				digit = digit | 0x80;

			out.write(digit);
		} while (val > 0);
	}

}
