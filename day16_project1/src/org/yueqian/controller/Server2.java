package org.yueqian.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

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
						}else {
							switch (line.trim()) {
							case "1":
								//1.编写上班打卡的代码
								ps.println("上班打卡");
								break;
							case "2":
								//2.编写下班打卡的代码
								ps.println("下班打卡");
								break;
							case "3":
								//3.编写查看员工考勤信息的代码
								ps.println("以下是考勤的信息");
								break;
							case "4":
								//4.直接输出退出系统
								ps.println("退出系统");
								break;
							default:
								//5.输入非法数据
								ps.println("没有这个选项！");
								break;
							}
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
