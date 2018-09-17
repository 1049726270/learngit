package org.yueqian.test;

import java.util.Timer;
import java.util.TimerTask;

import org.yueqian.service.AttendService;

public class AttendTimerTest {
	public static AttendService attendService = new AttendService();

	public static void main(String[] args){
		Timer timer = new Timer();
		//1.新建一个定时任务对象，在创建的时候通过有参构造将，attendService业务层对象赋值给UpdateAttendanceStatusTask的成员变量
		UpdateAttendanceStatusTask updateAttendanceStatusTask = new UpdateAttendanceStatusTask(2,attendService);
		timer.schedule(updateAttendanceStatusTask, 1000, 5000);
		// 1秒后启动任务,以后每隔3秒执行一次线程
		
		//2.写一个迟到的定时任务,并且执行
		
	}
}

/**
 * @author Administrator
 *	更新正常出勤状态的定时任务
 */
class UpdateAttendanceStatusTask extends TimerTask{
	private int id;
	private AttendService attendService;
	public UpdateAttendanceStatusTask(int id, AttendService attendService){
		this.id = id;
		this.attendService = attendService;
		}
	public void run(){
		System.out.println("线程" + id + ":正在执行");
		//将每天的考勤记录读取，然后更新记录的状态
		attendService.updateAttendanceAttendStatus();
	
	}
	
}