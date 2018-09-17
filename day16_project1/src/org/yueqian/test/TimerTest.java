package org.yueqian.test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args)
	{
	Timer timer = new Timer();
	MyTask secondTask = new MyTask(2);
	timer.schedule(secondTask, 1000, 3000);
	// 1秒后启动任务,以后每隔3秒执行一次线程
	}
}
class MyTask extends TimerTask{
	private int id;
	public MyTask(int id){
	
		this.id = id;
		}
	public void run(){
	//将每天的考勤记录读取，然后更新记录的状态
	System.out.println("线程" + id + ":正在执行");
	//System.gc();
	}
	
}