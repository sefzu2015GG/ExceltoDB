package com.fzu.excelandmysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fzu.sqlutils.CourseBean;

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

		EtoM etom = new EtoM(path);
		List<CourseBean> data = etom.getExcelFile();
		etom.outpr(data);
		
		
	}

	String filePath = null;

	public EtoM(String filePath) {
		this.filePath = filePath;
	}

	public void outpr(List<CourseBean> list) {

		for (int i = 0; i < list.size(); i++) {
			CourseBean bean = list.get(i);
			System.out.println(bean.toString());
			System.out.println();
		}

	}

	public List<CourseBean> getExcelFile() {
		List<CourseBean> list = new ArrayList<CourseBean>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);

			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("utf-8");

			Workbook workbook = Workbook.getWorkbook(fileInputStream);

			int sheetNum = workbook.getNumberOfSheets();

			Sheet sheet = workbook.getSheet(0);

			int row = sheet.getRows();
			int col = sheet.getColumns();
			System.out.println("row = " + row + ", col = " + col);

			for (int i = 0; i < row; i++) {
				CourseBean bean = new CourseBean();

				for (int j = 0; j < col; j++) {
					bean.SetByKey(j, sheet.getCell(j, i).getContents());
					// System.out.print(sheet.getCell(j, i).getContents());
				}
				list.add(bean);
				System.out.println();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
