package com.fzu.excelandmysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fzu.sqlutils.ChoseSqlUtils;
import com.fzu.sqlutils.CourseBean;
import com.fzu.sqlutils.CourseSqlUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class EtoM {

	/**
	 * 单元测试
	 * 
	 * @param argg0
	 */
	public static void main(String[] argg0) {
		System.out.println("test1");
		String path = "data/计算机 1 - 副本.xls";
		String tname = "test1";
		EtoM etom = new EtoM(path, tname);
	}

	private String filePath = null;
	private String tablename = null;
	private static CourseSqlUtils courseSqlUtils = null;
	private static ChoseSqlUtils choseSqlUtils = null;
	
	
	public EtoM(String filePath, String tablename) {
		this.filePath = filePath;
		this.tablename = tablename;
		courseSqlUtils = new CourseSqlUtils(tablename);
		choseSqlUtils = new ChoseSqlUtils(tablename);
		List<CourseBean> data = getExcelFile();
		outpr(data);
		courseSqlUtils.insert(data);
	}

	public static CourseSqlUtils getCourseSqlUtils( String tablename) {
		if(courseSqlUtils==null){
			courseSqlUtils = new CourseSqlUtils(tablename);
		}
		return courseSqlUtils;
	}
	
	public static ChoseSqlUtils getChoseSqlUtils(String tablename) {
		if(choseSqlUtils==null){
			choseSqlUtils = new ChoseSqlUtils(tablename);
		}
		return choseSqlUtils;
	}
	
	public void outpr(List<CourseBean> list) {

		for (int i = 0; i < list.size(); i++) {
			CourseBean bean = list.get(i);
			System.out.println(bean.toString());
			System.out.println();
		}

	}

	private List<CourseBean> getExcelFile() {
		List<CourseBean> list = new ArrayList<CourseBean>();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filePath);

			WorkbookSettings workbookSettings = new WorkbookSettings();
			// workbookSettings.setEncoding("utf-8");
			workbookSettings.setEncoding("gbk");

			Workbook workbook = Workbook.getWorkbook(fileInputStream);

			int sheetNum = workbook.getNumberOfSheets();

			Sheet sheet = workbook.getSheet(0);

			int row = sheet.getRows();
			int col = sheet.getColumns();
			System.out.println("row = " + row + ", col = " + col);

			for (int i = 0; i < row; i++) {
				CourseBean bean = new CourseBean();

				bean.setCoursenum("031202" + i);

				bean.setGrade(sheet.getCell(0, i).getContents()
						.replaceAll(String.valueOf((char) 160), ""));

				bean.setMajor(sheet.getCell(1, i).getContents()
						.replaceAll(String.valueOf((char) 160), ""));

				String peoplenum = sheet.getCell(2, i).getContents()
						.replaceAll(String.valueOf((char) 160), "");
				if (peoplenum == "") {
					peoplenum = "0";
				}
				bean.setPeoplenum(Integer.parseInt(peoplenum));

				bean.setCoursename(sheet.getCell(3, i).getContents()
						.replaceAll(String.valueOf((char) 160), ""));

				bean.setType(sheet.getCell(4, i).getContents()
						.replaceAll(String.valueOf((char) 160), ""));

				String credit = sheet.getCell(5, i).getContents()
						.replaceAll(String.valueOf((char) 160), "");
				if (credit == "") {
					credit = "0";
				}
				bean.setCredit(Float.parseFloat(credit));

				String period = sheet.getCell(6, i).getContents()
						.replaceAll(String.valueOf((char) 160), "");
				if (period == "") {
					period = "0";
				}
				bean.setPeriod(Integer.parseInt(period));

				String testperiod = sheet.getCell(7, i).getContents()
						.replaceAll(String.valueOf((char) 160), "");
				if (testperiod == "") {
					testperiod = "0";
				}
				bean.setTestperiod(Integer.parseInt(testperiod));

				String fuckcomputerperiod = sheet.getCell(8, i).getContents()
						.replaceAll(String.valueOf((char) 160), "");
				if (fuckcomputerperiod == "") {
					fuckcomputerperiod = "0";
				}
				bean.setFuckcomputerperiod(Integer.parseInt(fuckcomputerperiod));
				list.add(bean);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fileInputStream = null;
		}
		return list;
	}
}
