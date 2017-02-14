package com.waspring.waexport.node;

import java.util.Map;

import com.waspring.waexport.AbstractExportNode;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.IExport;
import com.waspring.waexport.handle.PdfExport;

/**
PDF导出方式的声明
 * 
 * @author felly
 
 * 
 */
public class PdfNode extends AbstractExportNode implements ExportNode {
	public PdfNode(Map data) {
		super(PDF, data);
	}

	@Override
	public IExport getExport()   {

		return new PdfExport(data);
	}

}
