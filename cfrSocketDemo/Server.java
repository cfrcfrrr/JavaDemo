package socketdemo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器类：ServerSocket
 * 构造：public ServerSocket(int port) 设置监听端口
 * 接收客户端连接：public Socket accept()
 * 取得客户端输出：public OutputStream getOutputStream()
 * @author cfr
 */
public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9999);
		System.out.println("等待客户端连接");
		Socket client = server.accept();
		PrintStream out = new PrintStream(client.getOutputStream());
		out.println("hello");
		out.close();
		server.close();
	}
}
