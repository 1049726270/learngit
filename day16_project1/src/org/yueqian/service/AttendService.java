package org.yueqian.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.yueqian.dao.AttendDao;
import org.yueqian.model.Attend;
import org.yueqian.model.User;

/**
 * @author Administrator
 *	attend表业务相关的代码
 */
public class AttendService {
	
	AttendDao attendDao = new AttendDao();

	public LinkedHashMap<String, Integer> selectByMonth(User user,String attendDate){
		
		//把统计的数据用集合装起来。
		LinkedHashMap<String, Integer> count = new LinkedHashMap<>();
		
		//统计出勤的天数
		Integer attendanceDayCount = attendDao.select(user, 1, attendDate);
		
		//统计休息的天数
		Integer restDayCount = attendDao.select(user, 2, attendDate);
		
		//统计迟到的天数
		Integer latedDayCount = attendDao.select(user, 3, attendDate);
		
		//统计早退的天数
		Integer leftedDayCount = attendDao.select(user, 4, attendDate);
		
		//统计缺卡的天数
		Integer missDayCount = attendDao.select(user, 5, attendDate);
		
		//统计旷工的天数
		Integer absenteeismDayCount = attendDao.select(user, 6, attendDate);
		
		//统计加班的天数
		Integer overtimeDayCount = attendDao.select(user, 7, attendDate);
		 
		count.put("attendanceDayCount", attendanceDayCount);
		count.put("restDayCount", restDayCount);
		count.put("latedDayCount", latedDayCount);
		count.put("leftedDayCount", leftedDayCount);
		count.put("missDayCount", missDayCount);
		count.put("absenteeismDayCount", absenteeismDayCount);
		count.put("overtimeDayCount", overtimeDayCount);
		
		return count;
	}

	/**
	 * @param user
	 * @return
	 * 插入上班考勤打卡的业务
	 */
	public String insertAttendMorning(User user) {
		//
		Attend attend = new Attend(null, user.getId(), new Date(), new Date(), null, null);
		Attend attendDB = attendDao.selectByAttendDate(attend);
		if (attendDB != null && attendDB.getAttendMorning() != null) {
			return "你早上已经打卡了！";
		}else{
			
			String attendMorningMsg = attendDao.insertAttendMorning(attend);
			return attendMorningMsg;
		}
		
	}

	/**
	 * @param user
	 * @return
	 * 下班考勤打卡的业务
	 */
	public String insertAttendEvening(User user) {
		Attend attend = new Attend(null, user.getId(), new Date(), null, new Date(), null);
		Attend attendDB = attendDao.selectByAttendDate(attend);
		if (attendDB == null) {
			String attendEveningMsg = attendDao.insertAttendEvening(attend);
			return attendEveningMsg;
		}else{
			attend.setId(attendDB.getId());
			String attendEveningMsg = attendDao.updateAttendEvening(attend);
			return attendEveningMsg;
		}
		
	}

	/**
	 * 设置正常上班打卡记录的状态为1
	 */
	public void updateAttendanceAttendStatus() {
		//1.通过Dao层查询出当天所有符合要求的记录
		//1.1获取当天的时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String attendDate = simpleDateFormat.format(date);
		
		//1.2当天9点上班打卡的timestamp
		Timestamp standAttendMorning = Timestamp.valueOf(attendDate + " 09:00:00");
		//1.3当天17点下班打卡的timestamp
		Timestamp standAttendEvening = Timestamp.valueOf(attendDate + " 17:00:00");
		
		//1.4写一个通用的获取当天所有考勤记录的Dao层方法。
		ArrayList<Attend> attendsDB = attendDao.selectAllAttendByAttendDate(attendDate);
		
		
		
		ArrayList<Integer> attendanceDayIDs = new ArrayList<>();
		if (attendsDB.size() != 0) {
			//1.5定义一个集合收集符合正常出勤的记录id
			for (Attend attendDB : attendsDB) {
				Date attendMorning = attendDB.getAttendMorning();
				Date attendEvening = attendDB.getAttendEvening();
				//1.6筛选出符合正常上班出勤的attendId;
				if (attendMorning != null && attendEvening != null) {
					//对比时间前进行时间的转换
					Timestamp attendMorningTimestamp = new Timestamp(attendMorning.getTime());
					
					Timestamp attendEveningTimestamp = new Timestamp(attendEvening.getTime());
					if (attendMorningTimestamp.before(standAttendMorning) && attendEveningTimestamp.after(standAttendEvening)) {
						attendanceDayIDs.add(attendDB.getId());
					}
					
				}
				
				
			}
			
		}
		//2.将符合要求的记录状态设定为1
		Integer attendanceDayStatus = 1;
		
		attendDao.updateAttendanceAttendStatus(attendanceDayIDs,attendanceDayStatus);
		
	}
}
