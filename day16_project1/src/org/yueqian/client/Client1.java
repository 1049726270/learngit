package org.yueqian.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Socket socket = new Socket("127.0.0.1", 12345);			//创建客户端，指定ip地址和端口号
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));		//将字节流包装成了字符流
		PrintStream ps = new PrintStream(socket.getOutputStream());					//PrintStream中有写出换行的方法
		
		while(true){
			System.out.println("请输入用户名：");
			String username = sc.nextLine();
			System.out.println("请输入密码：");
			String password = sc.nextLine();
			
			String loginString = "login-" + username + "-" +password;
			ps.println(loginString);					//向服务器发送信息
			System.out.println(br.readLine());				//打印服务器传来的信息
			socket.close();
			break;
		}
		
		
	}
}
