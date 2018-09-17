package org.yueqian.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

	public static void main(String[] args)throws Exception {
		ServerSocket server = new ServerSocket(12345);
		while(true){
			Socket socket = server.accept();					//接受客户端的请求
			new Thread(){

				@Override
				public void run() {
					try {
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));		//将字节流包装成了字符流
						PrintStream ps = new PrintStream(socket.getOutputStream());					//PrintStream中有写出换行的方法
						
						String line = br.readLine();																//读取客户端传来的信息
						System.out.println(line);
						if (line.contains("login")) {
							//进行登录的验证
							ps.println("登录成功");													//向客户端发送信息
						}
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}.start();
	}
}
}
