package demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SocketServer extends Thread {
	private static final Log logger = LogFactory.getLog(SocketServer.class);
	private ServerSocket serverSocket = null;
	BufferedReader br = null; //输入流
	BufferedWriter bw = null;

	private int port;

	public void setPort(int port) {
		this.port = port;
	}

	public SocketServer() {

	}

	public void init() throws IOException {
		try {
			if (serverSocket == null || serverSocket.isClosed()) {
				logger.info("本地Socket服务器准备启动！监听端口为：" + port);
				serverSocket = new ServerSocket(port);
				logger.info("本地Socket服务器已启动！监听端口为：" + port);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("本地Socket服务器启动失败：" + e);
			throw e;
		}
	}
//==============================================================方法一===========================================================================
	private void sendMessagePak(Socket socket){
		logger.info("开始发送响应信息到客户端");
		String resPak = "00001853<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<root><head><ReqsysId>T50</ReqsysId><RspsysId></RspsysId><ServiceNo></ServiceNo><SceneNo></SceneNo><TradeCode></TradeCode>" +
				"<ServiceVer>1.0.0</ServiceVer><SceneVer>01</SceneVer><GloSeqNo></GloSeqNo><GlobaTraceNo></GlobaTraceNo><ChannelCode>GTZ</ChannelCode>" +
				"<OrgNum>T5000</OrgNum><TelrNum>888888</TelrNum><ZoneNo>null</ZoneNo><MyBank>000</MyBank><AuthOrgNum></AuthOrgNum>" +
				"<AuthTelrNum></AuthTelrNum><TermiId></TermiId><ReqDate>20190703</ReqDate><Reqtime>0041454</Reqtime><ReqSerno></ReqSerno></head></root>";
		logger.info(resPak);
		if (socket == null) {
			//socket = new Socket(server, port);
		}
		BufferedOutputStream buff = null;
		try {
			buff = new BufferedOutputStream(socket.getOutputStream());
			buff.write(PacketUtils.getBytes(resPak));
			buff.flush();
			logger.info("发送响应信息结束!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 字节流读取报文消息
	 * @param socket
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String receivePak(Socket socket) throws IOException, InterruptedException {
		logger.info("开始字节流读取客户端消息！");
		long initTime = new Date().getTime();
		byte[] pac = null;
		if (socket == null) {
			return "";
		}
		BufferedInputStream receiveBuf = new BufferedInputStream(socket.getInputStream());
		while (true) {
			byte[] lenBytes = new byte[4];
			receiveBuf.read(lenBytes);
			int len = PacketUtils.bytesToint(lenBytes);
			if (len < 4) {
				Thread.sleep(500);
				continue;
			} else {
				pac = new byte[len];
				receiveBuf.read(pac);
				logger.info("接收到的客户端消息："+new String(pac));
				return new String(pac);

			}
		}
	}

//==============================================================方法二===========================================================================


	private void sendMessageString(Socket socket){
		logger.info("开始发送响应信息到客户端");
//		String resPak = "00000681<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//				"<root><head><ReqsysId>T50</ReqsysId><RspsysId></RspsysId><ServiceNo></ServiceNo><SceneNo></SceneNo><TradeCode></TradeCode>" +
//				"<ServiceVer>1.0.0</ServiceVer><SceneVer>01</SceneVer><GloSeqNo></GloSeqNo><GlobaTraceNo></GlobaTraceNo><ChannelCode>GTZ</ChannelCode>" +
//				"<OrgNum>T5000</OrgNum><TelrNum>888888</TelrNum><ZoneNo>null</ZoneNo><MyBank>000</MyBank><AuthOrgNum></AuthOrgNum>" +
//				"<AuthTelrNum></AuthTelrNum><TermiId></TermiId><ReqDate>20190703</ReqDate><Reqtime>1753645</Reqtime><ReqSerno></ReqSerno>" +
//				"<RspSeq></RspSeq><RspDate></RspDate><RspTime></RspTime><ErrorTyp>N</ErrorTyp><ErrorCode></ErrorCode><ErrorMsg>00000000</ErrorMsg>" +
//				"<Mac></Mac></head></root>";

		String resPak = "00000757<?xml version='1.0' encoding='UTF-8'?><root><head><ReqsysId>T50</ReqsysId><RspsysId>S66</RspsysId><ServiceNo>P0830016</ServiceNo><SceneNo>05</SceneNo><TradeCode></TradeCode><ServiceVer>01</ServiceVer><SceneVer>01</SceneVer><GloSeqNo>GT502019071000001023947</GloSeqNo><ChannelCode>GTZ</ChannelCode><OrgNum>T5000</OrgNum><TelrNum>888888</TelrNum><ZoneNo>null</ZoneNo><MyBank>000</MyBank><AuthOrgNum></AuthOrgNum><AuthTelrNum></AuthTelrNum><TermiId></TermiId><ReqDate>20190710</ReqDate><Reqtime>1023947</Reqtime><ReqSerno></ReqSerno><RspSeq></RspSeq><RspDate></RspDate><RspTime></RspTime><ErrorTyp>N</ErrorTyp><ErrorCode>AESBGW3551</ErrorCode><ErrorMsg>请求方系统标识不匹配（与ESB C应用配置的系统标识）</ErrorMsg><Mac></Mac></head></root>";

		logger.info(resPak);
		if (socket == null) {
			//socket = new Socket(server, port);
			System.out.println("socket 不存在");
		}
		try {
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
			bw.write(resPak);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String receiveMessageString(Socket socket) throws IOException {
		logger.info("开始读取客户端消息！");
		String message = "";
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		//获取报文长度
		char[] pakLen = new char[8];
		br.read(pakLen,0,8);
		int length = Integer.parseInt(new String(pakLen));
		logger.info("客户端消息长度："+length);
		char[] pak = new char[length];
		br.read(pak,0,length);
		logger.info("客户端报文：\n"+new String(pak));
		message = new String(pak);
//		String msg = null;
//		while ((msg = br.readLine()) != null) {
//			System.out.println("read:"+msg);
//			message += msg;
//		}
		//br.close();
		return message;
	}

//==============================================================方法三===========================================================================
	private String receiveMessage(Socket socket) throws IOException {
		String message = "";
		InputStream isr = socket.getInputStream();
		ByteArrayOutputStream out =new ByteArrayOutputStream();
		byte[] bytes = new byte[256];
		int n=0;
		while((n=isr.read(bytes))!=-1){
			out.write(bytes,0,n);
		}
		byte[] byteArray = out.toByteArray();
		message = new String(byteArray,"utf-8");
		return message;
	}

	private void sendMessage(Socket socket) throws IOException {
		logger.info("开始发送响应信息到客户端");
		String returnMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<service><SYS_HEAD><ServiceCode></ServiceCode><ServiceScene></ServiceScene><ConsumerId>300003</ConsumerId><TargetId></TargetId><ChannelTyp>15</ChannelTyp><OrgConsumerId>CT</OrgConsumerId><ConsumerSeqNo></ConsumerSeqNo><SplrSeqNo></SplrSeqNo><OrgConsumerSeqNo>035627</OrgConsumerSeqNo><TranDate>20200204</TranDate><TranTime>112723</TranTime><ReturnStatus></ReturnStatus><Ret><sdo><ReturnCode>170002</ReturnCode><ReturnMsg>服务识别失败！</ReturnMsg></sdo></Ret><TerminalCode></TerminalCode><OrgTerminalCode></OrgTerminalCode><ConsumerSvrId></ConsumerSvrId><OrgConsumerSvrId></OrgConsumerSvrId><DestSvrId></DestSvrId><UserLang></UserLang><FilFlg>0</FilFlg><FilPath></FilPath></SYS_HEAD><APP_HEAD><TellerNo>000054</TellerNo><BranchId>101001</BranchId><TellerPassword></TellerPassword><TellerLevel></TellerLevel><TellerType></TellerType><ApprFlag></ApprFlag><AuthFlag></AuthFlag></APP_HEAD></service>";
		byte[] bytes = returnMsg.getBytes("utf-8");
		int length = bytes.length;
		String length_format = String.format("%08d",length);
		String msg = length_format+returnMsg;
		logger.info(msg);
		OutputStream out = socket.getOutputStream();
//		BufferedOutputStream bos = new BufferedOutputStream(out);
		out.write(msg.getBytes("utf-8"));
		logger.info("开始响应信息结束!");
		out.flush();
		socket.shutdownOutput(); //结束输出
//		bos.close();
	}




	public void start() {
		Socket socket = null;
		try {
			init();
			logger.info("等待交易端发送指令。。。。。。");
			while (true) {
				socket = serverSocket.accept();
				if(socket!=null){
					logger.info("接收到的客户端ip："+socket.getInetAddress()+"端口:"+socket.getPort());
				}
//				try {
//					//模拟读取超时read timeOut
//					TimeUnit.SECONDS.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//接收
				String msg = receiveMessageString(socket);
//				String msg = receivePak(socket);
//				String msg = receiveMessage(socket);
				logger.info("接收到的报文："+msg);
				if (!"".equals(msg)) {
					try {
						TimeUnit.SECONDS.sleep(1); //模拟处理过程
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//回复
					sendMessageString(socket);
//					sendMessagePak(socket);
//					sendMessage(socket);

				} else {
					logger.info("消息格式错误！");
				}
				if (serverSocket == null || serverSocket.isClosed()) {
					init();
				}
			}
		} catch (IOException e) {
			logger.info("socket通讯IO异常！" + e);
			e.printStackTrace();
		}  finally {
			try {
				if (socket!=null)  socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
