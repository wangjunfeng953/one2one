package socketdemo.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import socketdemo.thread.SocketThread;
import socketdemo.thread.WriteThread;

public class C {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket c = new Socket("127.0.0.1",6000);
		InputStream in = c.getInputStream();
		OutputStream o = c.getOutputStream();
		new WriteThread(o).start();
		new SocketThread(in).start();
	}
}
