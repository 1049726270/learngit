package org.yueqian.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.yueqian.service.AttendService;

/**
 * @author Administrator
 *	对测试类继续项目应用的提升
 */
public class AttendProjectTimerTest {
	//时间间隔(一天)
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	public static AttendService attendService = new AttendService();

	public static void main(String[] args){
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 1); //凌晨1点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date=calendar.getTime(); //第一次执行定时任务的时间
		//如果第一次执行定时任务的时间 小于当前的时间
		//此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = addDay(date, 1);
		}

		
		Timer timer = new Timer();
		//1.新建一个定时任务对象，在创建的时候通过有参构造将，attendService业务层对象赋值给UpdateAttendanceStatusTask的成员变量
		UpdateAttendanceStatusTask updateAttendanceStatusTask = new UpdateAttendanceStatusTask(2,attendService);
		timer.schedule(updateAttendanceStatusTask, date, PERIOD_DAY);
		// 1秒后启动任务,以后每隔3秒执行一次线程
		
		//2.写一个迟到的定时任务,并且执行
		
	}
	// 增加或减少天数
		public static Date addDay(Date date, int num) {
			Calendar startDT = Calendar.getInstance();
			startDT.setTime(date);
			startDT.add(Calendar.DAY_OF_MONTH, num);
			return startDT.getTime();
		}

}

/**
 * @author Administrator
 *	更新正常出勤状态的定时任务
 *//*
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
	
}*/