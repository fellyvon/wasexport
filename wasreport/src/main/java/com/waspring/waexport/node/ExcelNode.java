package com.waspring.waexport.node;

import java.util.Map;

import com.waspring.waexport.AbstractExportNode;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.IExport;
import com.waspring.waexport.handle.ExcelExport;

/**
 * excel导出方式的声明
 * @author felly
 
 * 
 */
public class ExcelNode extends AbstractExportNode implements ExportNode {
	public ExcelNode(Map data) {
		super(EXCEL, data);
	}

	@Override
	public IExport getExport(){

		return new ExcelExport(data);
	}

}
