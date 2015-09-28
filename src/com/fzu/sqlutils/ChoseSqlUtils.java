package com.fzu.sqlutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoseSqlUtils {
	public static void main(String[] arg0) {
		CourseSqlUtils courseSqlUtils = new CourseSqlUtils("test");
	}

	private String tableName = "";

	public ChoseSqlUtils(String tableName) {
		this.tableName = tableName;
		CreateTable();
	}

	public void CreateTable() {
		String sql = "create table if not exists " + tableName + "_choseInfo ("
				+ "chosenum varchar(32),"
				+ "coursenum varchar(16) foreign key references " + tableName
				+ "_courseInfo(coursenum)," + "weektime varchar(10),"
				+ "teachername varchar(32)," + "remark varchar(255)"
				+ "primary key(chosenum)" + ");";
		SqlHelper.escuteUpdate(sql, null);
	}

	public int insert(List<ChoseBean> data) {
		String sql = "insert into " + tableName
				+ "_choseInfo (chosenum,coursenum,weektime,teachername,remark)"
				+ "values(?,?,?,?,?)";
		Object params[][] = new Object[data.size()][5];
		for (int i = 0; i < data.size(); i++) {
			ChoseBean bean = data.get(i);
			params[i][0] = bean.getChosenum();
			params[i][1] = bean.getChosenum();
			params[i][2] = bean.getWeektime();
			params[i][3] = bean.getTeachername();
			params[i][4] = bean.getRemark();
		}
		SqlHelper.excuteUpdateMultiParams(sql, params);
		return -1;
	}

	public int update(List<ChoseBean> data) {
		String sql = "update from" + tableName
				+ "_choseInfo set weektime=?,teachername=?,remark=? where "
				+ "chosenum = ?";
		Object params[][] = new Object[data.size()][4];
		for (int i = 0; i < data.size(); i++) {
			ChoseBean bean = data.get(i);
			params[i][0] = bean.getWeektime();
			params[i][1] = bean.getTeachername();
			params[i][2] = bean.getRemark();
			params[i][3] = bean.getChosenum();
		}
		SqlHelper.excuteUpdateMultiParams(sql, params);
		return -1;
	}

	public int delete(List<ChoseBean> data) {
		String sql = "delete from" + tableName + "_choseInfo where "
				+ "chosenum = ?";
		Object params[][] = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			ChoseBean bean = data.get(i);
			params[i][0] = bean.getChosenum();
		}
		SqlHelper.excuteUpdateMultiParams(sql, params);
		return -1;
	}

	public List<ChoseBean> GetChose() {
		String sql = "select * from " + tableName + "_choseInfo";
		List<ChoseBean> res = new ArrayList<ChoseBean>();
		ResultSet resultSet = SqlHelper.executeQuery(sql, null);

		try {
			while (resultSet.next()) {
				ChoseBean bean = new ChoseBean();
				bean.setChosenum(resultSet.getString(1));
				bean.setCoursenum(resultSet.getString(2));
				bean.setWeektime(resultSet.getString(3));
				bean.setTeachername(resultSet.getString(4));
				bean.setRemark(resultSet.getString(5));
				res.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
