package com.waspring.waexport.handle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.waspring.waexport.util.ExcelUtil;

/**
 * 导出excel的实现，使用POI组件辅助实现
 * 
 * @author felly
 
 * 
 */
public class ExcelExport extends AbstractExport {

	public ExcelExport(Map data) {
		super(data);
	}

	@Override
    public void exportInnertion(OutputStream servletOut, List header,
			List data) throws IOException {
		if (header == null || header.isEmpty()) {
			return;
		}
		HSSFWorkbook workbook = ExcelUtil.export(header, data);
		workbook.write(servletOut);

	}

}
