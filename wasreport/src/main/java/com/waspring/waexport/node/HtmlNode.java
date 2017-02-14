package com.waspring.waexport.node;

import java.util.Map;

import com.waspring.waexport.AbstractExportNode;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.IExport;
import com.waspring.waexport.handle.HtmlExport;

/**
HTML导出方式的声明
 * 
 * @author felly
 
 * 
 */
public class HtmlNode extends AbstractExportNode implements ExportNode {
	public HtmlNode(Map data) {
		super(HTML, data);
	}

	@Override
	public IExport getExport()   {

		return new HtmlExport(data);
	}

}
