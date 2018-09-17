package org.yueqian.model;

import java.util.Date;

/**
 * @author Administrator
 *	attend表的javabean
 */
public class Attend {

	private Integer id;
	
	private Integer userId;
	
	private Date attendDate;
	
	private Date attendMorning;
	
	private Date attendEvening;
	
	private Integer attendStatus;

	public Attend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attend(Integer id, Integer userId, Date attendDate, Date attendMorning, Date attendEvening,
			Integer attendStatus) {
		super();
		this.id = id;
		this.userId = userId;
		this.attendDate = attendDate;
		this.attendMorning = attendMorning;
		this.attendEvening = attendEvening;
		this.attendStatus = attendStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAttendDate() {
		return attendDate;
	}

	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}

	public Date getAttendMorning() {
		return attendMorning;
	}

	public void setAttendMorning(Date attendMorning) {
		this.attendMorning = attendMorning;
	}

	public Date getAttendEvening() {
		return attendEvening;
	}

	public void setAttendEvening(Date attendEvening) {
		this.attendEvening = attendEvening;
	}

	public Integer getAttendStatus() {
		return attendStatus;
	}

	public void setAttendStatus(Integer attendStatus) {
		this.attendStatus = attendStatus;
	}

	@Override
	public String toString() {
		return "Attend [id=" + id + ", userId=" + userId + ", attendDate=" + attendDate + ", attendMorning="
				+ attendMorning + ", attendEvening=" + attendEvening + ", attendStatus=" + attendStatus + "]";
	}

}
