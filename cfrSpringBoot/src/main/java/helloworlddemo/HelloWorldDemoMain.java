package helloworlddemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class HelloWorldDemoMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		String str = "Hello*World!";
		InputStream input = new ByteArrayInputStream(str.getBytes());
		OutputStream output = new ByteArrayOutputStream();
		int temp =0;
//		while((temp = input.read())!=-1) {
//			System.out.println(Character.toUpperCase(temp));
//			output.write(Character.toUpperCase(temp));
//		}
		byte tmp = 1;
		output.write(tmp);
		String strRead = output.toString();
		System.out.println(strRead);
		input.close();
		output.close();
		
		
//		ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
//	      while( bOutput.size()!= 10 ) {
//	         // 获取用户输入
//	         bOutput.write(System.in.read()); 
//	      }
//	      byte b [] = bOutput.toByteArray();
//	      System.out.println("Print the content");
//	      for(int x= 0 ; x < b.length; x++) {
//	         // 打印字符
//	         System.out.print((char)b[x]  + "   "); 
//	      }
//	      System.out.println("   ");
//	      int c;
//	      ByteArrayInputStream bInput = new ByteArrayInputStream(b);
//	      System.out.println("Converting characters to Upper case " );
//	      for(int y = 0 ; y < 1; y++ ) {
//	         while(( c= bInput.read())!= -1) {
//	            System.out.println(Character.toUpperCase((char)c));
//	         }
//	         bInput.reset(); 
//	      }
	}
}
