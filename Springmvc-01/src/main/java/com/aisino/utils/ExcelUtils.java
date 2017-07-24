package com.aisino.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {
	/**
	 * 
	 * @param list<model>
	 *            dao传回的值
	 * @param clazz1
	 *            资源文件
	 * @param fileName
	 *            保存的文件名
	 * @param optionalKey
	 *            可选列key
	 * @throws Throwable
	 */
	public static Workbook export(List<?> list, Class<?> clazz1, Workbook wb)
			throws Throwable {
		Sheet sheet = wb.createSheet("表格1");
		Row row = sheet.createRow(0);
		Cell cell;
		Field[] fs = clazz1.getDeclaredFields();
		// 读出clazz1中的属性值，设置表头

		for (int i = 0; i < fs.length; i++) {

			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			String name = f.getName();

			if (!(name.equals("optional"))) {

				Object value = f.get(name);
				cell = row.createCell(i);
				cell.setCellValue(value.toString());
			}

		}
		// 向表中添加数据
		for (int j = 1; j <= list.size(); j++) {
			row = sheet.createRow(j);

			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				String name = f.getName();
				String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
				Method method = list.get(j - 1).getClass().getMethod("get" + methodName);
				String value = (String) method.invoke(list.get(j - 1));

				try {
					double d = Double.parseDouble(value);
					row.createCell(i).setCellValue(d);
				} catch (Exception e) {
					row.createCell(i).setCellValue(value);
				}

			}
		}

		return wb;
	}

}
