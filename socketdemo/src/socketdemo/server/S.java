package socketdemo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class S {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(6000);
		while(true){
			Socket client = ss.accept();//×èÈû
			InputStream i = client.getInputStream();
			OutputStream o = client.getOutputStream();
		}
	}
}
