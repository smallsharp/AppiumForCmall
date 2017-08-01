package com.cmall.utils;

import java.io.File;
import org.apache.log4j.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Excel 操作工具类
 * 
 * @author cm
 *
 */
public class ExcelReader {

	private static Logger log = Logger.getLogger(ExcelReader.class);

	/**
	 * 获取sheet
	 * 
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static Sheet getSheet(String filePath, String sheetName) {
		Sheet sheet = null;
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			sheet = book.getSheet(sheetName);
			if (null == sheet) {
				log.error("指定的sheet不存在！");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 
	 * @param sheet sheet 名称
	 * @param columnName 列名称
	 * @param lineName 行名称
	 * @return 行列对应的值
	 */
	public static String getValue(Sheet sheet, String columnName, String lineName) {

		if (sheet == null) {
			System.out.println("检查是否存在这个sheet，如需使用，请先getsheet('xxx')");
			return null;
		}
		int rowNum = sheet.getRows(); // 获得该sheet的行数
		int colNum = sheet.getColumns();
		int col = 0, row = 0;

		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				Cell cell = sheet.getCell(j, i);
				String content = cell.getContents();
				if (content.equals(columnName)) {
					col = cell.getColumn();
				}
			}
		}

		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				Cell cell = sheet.getCell(j, i);
				String content = cell.getContents();
				if (content.equals(lineName)) {
					row = cell.getRow();
				}
			}
		}
		return sheet.getCell(col, row).getContents();
	}

}