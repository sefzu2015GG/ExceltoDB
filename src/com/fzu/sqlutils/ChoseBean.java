package com.fzu.sqlutils;

public class ChoseBean {
	public String getChosenum() {
		return chosenum;
	}
	public void setChosenum(String chosenum) {
		this.chosenum = chosenum;
	}
	public String getCoursenum() {
		return coursenum;
	}
	public void setCoursenum(String coursenum) {
		this.coursenum = coursenum;
	}
	public String getWeektime() {
		return weektime;
	}
	public void setWeektime(String weektime) {
		this.weektime = weektime;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ChoseBean [Chosenum=" + chosenum + ", coursenum=" + coursenum
				+ ", weektime=" + weektime + ", teachername=" + teachername
				+ ", remark=" + remark + "]";
	}
	private String chosenum;
	private String coursenum;
	private String weektime;
	private String teachername;
	private String remark;
}
