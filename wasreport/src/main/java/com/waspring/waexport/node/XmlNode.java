package com.waspring.waexport.node;

import java.util.Map;

import com.waspring.waexport.AbstractExportNode;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.IExport;
import com.waspring.waexport.handle.XmlExport;

/**
XML导出方式的声明
 * 
 * @author felly
 
 * 
 */
public class XmlNode extends AbstractExportNode implements ExportNode {
	public XmlNode(Map data) {
		super(XML, data);
	}

	@Override
	public IExport getExport()   {

		return new XmlExport(data);
	}

}
