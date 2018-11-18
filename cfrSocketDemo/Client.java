package socketdemo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端类：Socket
 * 构造：public Socket(String host, int port)
 * 得到输入：public InputStream getInputStream()
 * @author cfr
 *
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost", 9999);
		Scanner in = new Scanner(client.getInputStream());
		if(in.hasNext()) {
			System.out.println(in.next());
		}
		in.close();
		client.close();
	}
}
