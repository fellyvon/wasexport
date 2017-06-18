package com.waspring.waexport.util;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.waspring.waexport.IExport;

/**
 * excel操作工具类实现,包括导入工具和导入工具
 * 
 * @author felly
 * 
 * 
 */
public class ExcelUtil {

	/**
	 * 导出为Excel工具
	 * 
	 * @param header
	 * @param data
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook export(List header, List data) {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
		HSSFSheet sheet = workbook.createSheet();
		// HSSFSheet sheet = workbook.createSheet("SheetName");

		// 用于格式化单元格的数据

		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName(" 黑体 "); // 字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 宽度
		font.setItalic(false); // 是否使用斜体

		// font.setStrikeout(true);// 是否使用划线

		// 设置单元格类型
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setFont(font);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平布局：居中
		headStyle.setWrapText(true);

		// 创建新行(row),并将单元格(cell)放入其中. 行号从0开始计算.
		HSSFRow head = sheet.createRow((short) 0);// //标题行

		Iterator it = header.iterator();
		int index = 0;
		while (it.hasNext()) {// ///标题列
			Map item = (Map) it.next();
			String title = (String) item.get(IExport.TITLE);
			HSSFCell cell = head.createCell((short) index);
			HSSFRichTextString hssfString = new HSSFRichTextString(title);
			cell.setCellValue(hssfString);
			cell.setCellStyle(headStyle); // 设置单元格样式
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
			index++;
		}

		it = data.iterator();

		index = 1;
		while (it.hasNext()) {// ///内容
			List item = (List) it.next();
			HSSFRow row = sheet.createRow(index);// //标题行

			Iterator sub = item.iterator();
			int subIndex = 0;
			while (sub.hasNext()) {
				Object o = sub.next();
				if (o == null) {
					o = "";// //如果是空，直接强制为 空字符
				}
				HSSFCell cell = row.createCell((short) subIndex);
				HSSFRichTextString hssfString = new HSSFRichTextString(o.toString());
				cell.setCellValue(hssfString);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
				subIndex++;
			}

			index++;

		}
		it = header.iterator();

		index = 1;
		while (it.hasNext()) {// ///内容列
			it.next();
			sheet.autoSizeColumn((short) index); //
			index++;
		}

		return workbook;

	}

	/**
	 * 导出数据
	 * 
	 * @param data
	 *            格式如下： {\
	 *            "header\":[{\"title\":\"任务名称\",\"field\":\"jobName\",\"width\":\"10%\",\"sortable\":true,\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-jobName\"},{\"title\":\"任务分组\",\"field\":\"jobGroup\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-jobGroup\"},{\"title\":\"任务状态\",\"field\":\"jobStatus\",\"width\":\"5%\",\"boxWidth\":49,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-jobStatus\"},{\"title\":\"是否同步\",\"field\":\"isConcurrent\",\"width\":\"5%\",\"boxWidth\":49,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-isConcurrent\"},{\"title\":\"执行表达式\",\"field\":\"cronExpression\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-cronExpression\"},{\"title\":\"执行java类\",\"field\":\"beanClass\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-beanClass\"},{\"title\":\"执行方法\",\"field\":\"methodName\",\"width\":\"5%\",\"boxWidth\":49,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-methodName\"},{\"title\":\"创建人员\",\"field\":\"createNo\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-createNo\"},{\"title\":\"创建时间\",\"field\":\"createTime\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-createTime\"},{\"title\":\"最后修改时间\",\"field\":\"updateTime\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-updateTime\"},{\"title\":\"描述信息\",\"field\":\"description\",\"width\":\"15%\",\"boxWidth\":165,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-description\"}],\"data\":[[\"1\",\"1\",\"停止\",\"是\",\"
	 *            * * * * * ?\",\"1\",\"1\",null,\"2015-06-25
	 *            19:55:17.0\",\"2015-06-25
	 *            19:56:27.0\",\"test1\"],[\"全额\",\"133213\",\"停止\",\"否\",\"* *
	 *            * * * ?\",\"323\",\"3213\",null,\"2015-06-02
	 *            13:09:21.0\",\"2015-06-03 17:32:20.0\",\"321312\"]]}
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HSSFWorkbook export(Map<String, List> dat) {

		List<Map<String, String>> header = (List<Map<String, String>>) dat.get(IExport.HEADER);
		List<List<String>> data = (List<List<String>>) dat.get(IExport.DATA);
		return export(header, data);
	}

	/**
	 * 获取上传文件（Excel）
	 * 
	 * @param inputStream
	 *            输入流，导入的excel数据流
	 * @return 返回列表，key为标题列名，value为单元格值
	 */
	public static List<Map<String, String>> getExcelListMap(InputStream inputStream) {
		try {
			POIFSFileSystem poiFs = new POIFSFileSystem(inputStream);
			HSSFWorkbook wb = new HSSFWorkbook(poiFs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int lastRowNumber = sheet.getLastRowNum();

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			int firstColsNum = 0;
			for (int i = 0; i <= lastRowNumber; i++) {
				if (sheet != null) {
					HSSFRow row = sheet.getRow(i);
					Map<String, String> tempMap = new HashMap<String, String>();
					if (row != null) {
						if (firstColsNum == 0) {
							firstColsNum = row.getLastCellNum();
						}
						int nullCount = 0;
						for (int k = 0; k < firstColsNum; k++) {
							if (i == 0) {
								map.put(String.valueOf(k), checkNull(getCellValue(row, k)).toUpperCase());
							} else {
								String cellValue = getCellValue(row, k);
								if (cellValue == null || "".equals(cellValue) || "null".equals(cellValue)) {
									nullCount++;
								}
								tempMap.put(map.get(String.valueOf(k)), checkNull(getCellValue(row, k)));
							}
						}
						if (tempMap.size() > 0 && nullCount != firstColsNum) {
							list.add(tempMap);
						}
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取上传文件（Excel）,通过给定列名获取该列数据
	 * 
	 * @param inputStream
	 * @return
	 */
	public static List<Map<String, String>> getExcelListMap(InputStream inputStream, String need) {
		if (need == null || need.length() == 0)
			return null;

		try {
			POIFSFileSystem poiFs = new POIFSFileSystem(inputStream);
			HSSFWorkbook wb = new HSSFWorkbook(poiFs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int lastRowNumber = sheet.getLastRowNum();
			HSSFRow myRow = null;
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i <= lastRowNumber; i++) {
				if (sheet != null) {
					HSSFRow row = sheet.getRow(i);
					myRow = sheet.getRow(0);
					Map<String, String> tempMap = new HashMap<String, String>();
					if (row != null) {
						int tempNum = row.getLastCellNum();
						int nullCount = 0;
						for (int k = 0; k < tempNum; k++) {
							if (need.indexOf(checkNull(getCellValue(myRow, k)).toUpperCase()) != -1) {

								if (i == 0) {
									map.put(String.valueOf(k), checkNull(getCellValue(row, k)).toUpperCase());
								} else {
									String cellValue = getCellValue(row, k);
									if (cellValue == null || "".equals(cellValue) || "null".equals(cellValue)) {
										nullCount++;
									}
									tempMap.put(map.get(String.valueOf(k)), checkNull(getCellValue(row, k)));
								}

							} else {

								continue;
							}

						}
						if (tempMap.size() > 0 && nullCount != tempNum) {
							list.add(tempMap);
						}
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param str
	 * @return
	 */
	private static String checkNull(String str) {

		return StringUtils.stripToEmpty(str);
	}

	public static void main(String dfs[]) throws Exception {
		System.out.println("=" + checkNull(""));
	}

	/**
	 * 获取EXCEL文件单元列值
	 * 
	 * @param row
	 * @param point
	 * @return
	 */
	private static String getCellValue(HSSFRow row, int point) {
		String reString = "";
		try {
			HSSFCell cell = row.getCell((short) point);
			if (cell.getCellType() == 1)
				reString = cell.getStringCellValue();
			else if (cell.getCellType() == 0) {
				reString = convert(cell.getNumericCellValue());
				BigDecimal bd = new BigDecimal(reString);
				reString = bd.toPlainString();
			} else {
				reString = "";
			}
			System.out.println(cell.getCellType() + ":" + cell.getCellFormula());
		} catch (Exception localException) {
		}
		return checkNull(reString);
	}

	/**
	 * 数字转化为字符输出
	 * 
	 * @param pp
	 * @return
	 */
	private static String convert(double pp) {
		try {
			int dd = (int) pp;
			if (pp > dd)
				return String.valueOf(pp);
			if (pp == dd) {
				return String.valueOf(dd);
			}
			return String.valueOf(pp);
		} catch (Exception e) {
		}
		return String.valueOf(pp);
	}
}
