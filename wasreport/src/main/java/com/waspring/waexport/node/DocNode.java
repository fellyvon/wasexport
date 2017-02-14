package com.waspring.waexport.node;

import java.util.Map;

import com.waspring.waexport.AbstractExportNode;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.IExport;
import com.waspring.waexport.handle.DocExport;

/**
 *DOC导出方式的声明
 * 
 * @author felly
 
 * 
 */
public class DocNode extends AbstractExportNode implements ExportNode {
	public DocNode(Map data) {
		super(DOC, data);
	}

	@Override
	public IExport getExport() {

		return new DocExport(data);
	}

}
