package socketdemo.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class WriteThread extends Thread{
	private OutputStream out;
	private Scanner scanner = new Scanner(System.in);
	public WriteThread(OutputStream out) {
		// TODO Auto-generated constructor stub
		this.out = out;
	}
	public void run(){
		byte[] buff = new byte[1024];
		
		while(true){
				try {
					System.out.println("qing请输入消息");
					String msg = scanner.next();
					out.write(msg.getBytes("utf-8"));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("连接中断");
				}
			}
	}
}
