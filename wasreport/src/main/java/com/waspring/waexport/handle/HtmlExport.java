package com.waspring.waexport.handle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 导出html实现
 * 
 * @author felly
 
 * 
 */
public class HtmlExport extends AbstractExport {

	public HtmlExport(Map data) {
		super(data);
	}

	@Override
    public void exportInnertion(OutputStream servletOut, List header,
			List data) throws IOException {
	     

	}

}
