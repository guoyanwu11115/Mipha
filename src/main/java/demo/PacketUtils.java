package demo;


import java.util.Calendar;

/**
 * Classname: PacketUtils.java Description: 报文工具类 Create Date: 2009-7-3
 * 
 * @author: wangwei
 * 
 */
public class PacketUtils {

	/**
	 * Description 描述方法的功能 组成报文头
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
//	public static StringBuffer buildPacketHead(PacketFieldUtil packetField, String code, String id) {
//		StringBuffer buf = new StringBuffer();
//		buf.append(packetField.getVersionNO());// 版本号 （nString,1);
//		buf.append(packetField.getToEncrypt());// 密押标识(nString,1)
//		buf.append(packetField.getCommCode());// 通讯代码(nString,6)
//		buf.append(packetField.getCommType());// 通讯类型(nString,1)
//		buf.append(packetField.getReceiverId());// 接收方标识(String,4)
//		buf.append(packetField.getSenderId());// 发起方标识(String,4)
//		buf.append(rightSpace(id, 22));// 发起方流水(String,22)
//		buf.append(rightSpace(getDate(), 8));// 发起方日期(date,8)
//		buf.append(rightSpace(getTime(), 6));// 发起方时间(time,6)
//		buf.append(code);// 交易请求(String,6)
//		buf.append(rightSpace("", 4));// 业务系统(String,4)
//		buf.append(rightSpace("", 2));// 错误标识(nString,2)"01"代表成功，"02"代表失败
//		buf.append(rightSpace("", 7));// 错误代码(String,7)
//		buf.append(rightSpace("", 4));// 保留位(String,4)
//		return buf;
//	}

	/**
	 * Description 描述方法的功能 获得时间
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static String getTime() {
		StringBuffer bf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		bf.append(numberToString(cal.get(Calendar.HOUR_OF_DAY), 2));
		bf.append(numberToString(cal.get(Calendar.MINUTE), 2));
		bf.append(numberToString(cal.get(Calendar.SECOND), 2));
		return bf.toString();
	}

	/**
	 * Description 描述方法的功能 获得日期
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static String getDate() {
		StringBuffer bf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		bf.append(numberToString(year, 4));
		bf.append(numberToString(cal.get(Calendar.MONTH) + 1, 2));
		bf.append(numberToString(cal.get(Calendar.DAY_OF_MONTH), 2));
		return bf.toString();
	}

	/**
	 * 根据日期，时间,随机数生成流水号
	 * 
	 * @return
	 */
//	public static String getId() {
//		return getDate() + getTime() + MD5Utils.getRandom();
//	}

	/**
	 * Description shot转换byte[]
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static byte[] shortToBytes(short s) {
		byte[] buf = new byte[4];
		for (int pos = 3; pos >= 0; pos--) {
			buf[pos] = (byte) (s & 0xff);
			s >>= 8;
		}
		return buf;
	}

	/**
	 * Description byte[] 转换 short
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static short bytesToShorts(byte[] b) {
		short s = b[0];
		short temp = b[1];
		temp &= 0x00ff;
		s <<= 8;
		s |= temp;
		return s;
	}

	/**
	 * Description 将字符串转为数字
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static Long StringToNumber(String src) {
		return Long.valueOf(src);
	}

	/**
	 * Description <br>
	 * 描述方法的功能 数字字符转字符串，前补零
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
//	public static String numberToString(String src, int size) {
//		long l = 0;
//		if (!StringUtil.isEmpty(src)) {
//			l = Long.parseLong(src.trim());
//		}
//		return numberToString(l, size);
//	}

	/**
	 * Description <br>
	 * 描述方法的功能 数字转字符串，前补零
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static String numberToString(long l, int size) {
		Long temp = Long.valueOf(l);
		return String.format("%0" + size + "d", new Object[] { temp });
	}

	/**
	 * Description 描述方法的功能 右补空格
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static String rightSpace(String s, int len) {
		if (s == null) {
			s = "";
		}
		final byte[] bs = s.getBytes();
		if (bs.length > len) {
			return new String(bs, 0, len);
		}
		final String str = String.format("%-" + len + "s", new Object[] { s });
		byte[] bytes = str.getBytes();
		return new String(bytes, 0, len);
	}

	/**
	 * Description 描述方法的功能 byte数组转换int
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	public static int bytesToint(byte[] b) {

		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}
	/**
	 * @author YanZS
	 * @date 20130821
	 * @param b
	 * @return
	 */
	public static int bytesTointDayoff(byte[] b) {

		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 2; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	/**
	 * Description 描述方法的功能 int 转换byte[]
	 * 
	 * @param
	 * @param
	 * @return
	 * @see
	 * 
	 */
	private static byte[] intTobytes(int pNum) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (pNum >>> (24 - i * 8));
		}
		return b;
	}

	/**
	 * 返回报文字节数组
	 * 
	 * 
	 */
	public static byte[] getBytes(String content) {
		int len = content.getBytes().length; 							   // 报文长度
		byte[] contentBuf = content.getBytes(); 						   // 报文数组
		byte[] lengthBuf = intTobytes(len);								   // 报文长度数组
		byte[] packetBuf = new byte[contentBuf.length + lengthBuf.length]; // 加上报文长度的报文数组
		System.arraycopy(lengthBuf, 0, packetBuf, 0, lengthBuf.length);
		System.arraycopy(contentBuf, 0, packetBuf, lengthBuf.length, contentBuf.length);
		return packetBuf;
	}

	/**
	 * 从报文数组截取字符串
	 * 
	 */
	public static String getSubString(byte[] buf, int begin, int length) {
		return new String(buf, begin, length).trim();
	}

	public static void main(String[] args) {
		
		/*
		 * SocketManger sm = new SocketManger(); 
		 * try {
		 * 	 sm.sendData("aadfdsa", "edd2312321232");
		 * 	 sm.receive(); 
		 * } catch (Exception e) {
		 * 		e.printStackTrace(); 
		 * }
		 */
	}
}
