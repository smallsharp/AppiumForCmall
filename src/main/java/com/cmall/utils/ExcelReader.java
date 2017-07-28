package com.cmall.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.android.ddmlib.IDevice;
import com.cmall.script.Performance;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Excel 操作工具类
 * 
 * @author cm
 *
 */
public class ExcelReader {
	
	private Logger log = Logger.getLogger(ExcelReader.class);

	/**
	 * 获取sheet
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
				System.out.println("指定的sheet不存在！");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 
	 * @param sheet
	 *            sheet名称
	 * @param columnName
	 *            列名称
	 * @param lineName
	 *            行名称
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

	/**
	 * Android 性能读取并存入Excel
	 * @param file 文件
	 * @param sheetName Excel的sheet名称
	 * @param times 读取的次数
	 * @param intervals 读取的间歇，即多久取一次数据
	 * @throws WriteException
	 * @throws IOException
	 */
	public void writeToExcel(File file, String sheetName,int times,long intervals) throws WriteException, IOException {

		try {
			if (!file.exists()) {
				WritableWorkbook wwb = Workbook.createWorkbook(file);
				System.out.println("new one");
				WritableSheet play = wwb.createSheet("Play", 2);

				Label lab1 = new Label(0, 4, "Hello");// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容
				Label lab2 = new Label(2, 4, "World");
				play.addCell(lab1);
				play.addCell(lab2);
				// 开始写文件
				wwb.write();
				// 关闭Excel工作薄对象
				wwb.close();
			} else {
				System.out.println("ElSE");
				Workbook workbook = Workbook.getWorkbook(file);
				File tempfile = new File(System.getProperty("user.dir") + "\\tempfile.xls");
				WritableWorkbook wwb = Workbook.createWorkbook(tempfile, workbook);
				WritableSheet writableSheet = wwb.getSheet(sheetName);
				int num = workbook.getSheet("Play").getColumns();
				for (int i = 0; i < num; i++) {
					writableSheet.setColumnView(i, 25); // 设置列宽
				}
				
				DDMlib ddmlib = DDMlib.getInstance();
				ddmlib.init();
				IDevice device = ddmlib.getDevice();

				writableSheet.addCell(new Label(0, 0, "应用包名"));
				writableSheet.addCell(new Label(1, 0, "com.play.android"));

				writableSheet.addCell(new Label(0, 1, "应用名称"));
				writableSheet.addCell(new Label(1, 1, "Play"));

				writableSheet.addCell(new Label(0, 2, "应用PID"));
				writableSheet.addCell(new Label(1, 2, "32200"));

				writableSheet.addCell(new Label(0, 3, "总内存(MB)"));
				writableSheet.addCell(new Label(1, 3, Performance.getTotalMemory("com.play.android")+""));
				
				writableSheet.addCell(new Label(0, 4, "CPU型号"));
				writableSheet.addCell(new Label(1, 4, device.getProperty("ro.product.cpu.abilist")));

				writableSheet.addCell(new Label(0, 5, "Android系统版本")); 
				writableSheet.addCell(new Label(1, 5, device.getProperty("ro.build.version.release")));

				writableSheet.addCell(new Label(0, 6, "手机型号"));
				writableSheet.addCell(new Label(1, 6, device.getProperty("ro.product.name")));

				String title[] = { "时间", "应用占用内存PSS(M)", "应用使用内存占比(%)", "应用cpu使用占比(%)" };
				for (int i = 0; i < title.length; i++) {
					writableSheet.addCell(new Label(i, 7, title[i]));
				}
				
				for (int i = 0; i < times; i++) {
					List<String> appList = new Performance().getPerformance();
					log.info("第 "+i+" 次获取性能数据成功");
					for (int j = 0; j < appList.size(); j++) {
						log.info(appList.get(j));
					}
					for (int x = 0; x < appList.size(); x++) {
						//第0列,第8,9…n行，开始追加数据
						writableSheet.addCell(new Label(x, i + 8, appList.get(x)));
					}
					Thread.sleep(intervals);
				}
				ddmlib.finish();
				wwb.write();
				wwb.close();
				workbook.close();
				file.delete();
				tempfile.renameTo(file);
			}

			// 5、关闭流
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}