package com.waspring.waexport.handle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 导出doc文档实现
 * 
 * @author felly
 
 * 
 */
public class DocExport extends AbstractExport {

	public DocExport(Map data) {
		super(data);
	}

	@Override
    public void exportInnertion(OutputStream servletOut, List header,
			List data) throws IOException {
	     
         
	}

}