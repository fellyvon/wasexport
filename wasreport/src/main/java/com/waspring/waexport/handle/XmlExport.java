package com.waspring.waexport.handle;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.waspring.waexport.util.XmlConverUtil;

/**
 * 导出xml
 * 
 * @author felly
 
 * 
 */
public class XmlExport extends AbstractExport {

	public XmlExport(Map data) {
		super(data);
	}

	@Override
    public void exportInnertion(OutputStream servletOut, List header,
			List data) throws IOException {
		Map map = new HashMap(16);
		map.put(HEADER, header);
		map.put(DATA, data);

		// /////XmlConverUtil
		servletOut.write(XmlConverUtil.mapToXML(map).getBytes());
	}

}
