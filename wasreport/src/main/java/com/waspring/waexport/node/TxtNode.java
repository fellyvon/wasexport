package com.waspring.waexport.node;

import java.util.Map;

import com.waspring.waexport.AbstractExportNode;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.IExport;
import com.waspring.waexport.handle.TxtExport;

/**
TXT
导出方式的声明
 * 
 * @author felly
 
 * 
 */
public class TxtNode extends AbstractExportNode implements ExportNode {
	public TxtNode(Map data) {
		super(TXT, data);
	}

	@Override
	public IExport getExport()   {

		return new TxtExport(data);
	}

}
