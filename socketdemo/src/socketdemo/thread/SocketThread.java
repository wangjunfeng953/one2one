package socketdemo.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SocketThread extends Thread{
	private InputStream in;
	public SocketThread(InputStream in) {
		// TODO Auto-generated constructor stub
		this.in = in;
	}
	public void run(){
		byte[] buff = new byte[1024];
		
		while(true){
			ByteArrayOutputStream bean = new ByteArrayOutputStream();
			while(true){
				int len = -1;
				try {
					len = in.read(buff);
					bean.write(buff, 0, len);
					if(len<1024){
						break;
					}
					System.out.println("收到消息： "+bean.toString("utf-8"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("连接中断");
				}
			}
		}
	}
}
