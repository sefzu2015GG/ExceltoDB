package com.fzu.sqlutils;

import java.util.HashMap;
import java.util.Map;

public class CourseBean {

	

	@Override
	public String toString() {
		return "CourseBean [coursenum=" + coursenum + ", grade=" + grade
				+ ", major=" + major + ", peoplenum=" + peoplenum
				+ ", coursename=" + coursename + ", type=" + type + ", credit="
				+ credit + ", period=" + period + ", testperiod=" + testperiod
				+ ", fuckcomputerperiod=" + fuckcomputerperiod + "]";
	}

	public String getCoursenum() {
		return coursenum;
	}
	public void setCoursenum(String coursenum) {
		this.coursenum = coursenum;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(int peoplenum) {
		this.peoplenum = peoplenum;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getTestperiod() {
		return testperiod;
	}
	public void setTestperiod(int testperiod) {
		this.testperiod = testperiod;
	}
	public int getFuckcomputerperiod() {
		return fuckcomputerperiod;
	}
	public void setFuckcomputerperiod(int fuckcomputerperiod) {
		this.fuckcomputerperiod = fuckcomputerperiod;
	}

	private String coursenum;
	private String grade;
	private String major;
	private int peoplenum;
	private String coursename;
	private String type;
	private float credit;
	private int period;
	private int testperiod;
	private int fuckcomputerperiod;
	
	public String getWeektime() {
		return weektime;
	}

	public void setWeektime(String weektime) {
		this.weektime = weektime;
	}

	public void addWeektime(String weektime) {
		if(this.weektime==null||this.weektime==""){
			this.weektime=weektime;
		}else{
			this.weektime+=";";
			this.weektime+=weektime;
		}
	}
	
	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public void addTeachername(String teachername) {
		if(this.teachername==null||this.teachername==""){
			this.teachername=teachername;
		}else{
			this.teachername+=";";
			this.teachername+=teachername;
		}
	}
	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void addRemark(String remark) {
		if(this.remark==null||this.remark==""){
			this.remark=remark;
		}else{
			this.remark+=";";
			this.remark+=remark;
		}
	}

	private String weektime;
	private String teachername;
	private String remark;
	
}
