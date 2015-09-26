package com.fzu.sqlutils;

import java.util.HashMap;
import java.util.Map;

public class CourseBean {

	private Map<String, String> keyDate = new HashMap<>();

	private String[] key = { "grade", "major", "peoplenum", "coursename",
			"type", "credit", "period", "testperiod", "fuckcomputerperiod",
			"weektime", "teachername", "remark", };

	public void SetByKey(int j, String context) {
		keyDate.put(key[j], context);
	}

	public String SetgetKey(int j) {

		return keyDate.get(key[j]);
	}

	@Override
	public String toString() {
		String res = "CourseBean [";
		for (int i = 0; i < key.length; i++) {
			res += " "+keyDate.get(key[i]);
		}
		return res + "]";
	}

	private String grade;
	private String major;
	private String peoplenum;
	private String coursename;
	private String type;
	private String credit;
	private String period;
	private String testperiod;
	private String fuckcomputerperiod;
	private String weektime;
	private String teachername;
	private String remark;

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

	public String getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(String peoplenum) {
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

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTestperiod() {
		return testperiod;
	}

	public void setTestperiod(String testperiod) {
		this.testperiod = testperiod;
	}

	public String getFuckcomputerperiod() {
		return fuckcomputerperiod;
	}

	public void setFuckcomputerperiod(String fuckcomputerperiod) {
		this.fuckcomputerperiod = fuckcomputerperiod;
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

}
