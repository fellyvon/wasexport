package com.waspring.waexport.util;

import java.util.Map;

import com.waspring.waexport.ExportNode;
import com.waspring.waexport.node.DocNode;
import com.waspring.waexport.node.ExcelNode;
import com.waspring.waexport.node.HtmlNode;
import com.waspring.waexport.node.PdfNode;
import com.waspring.waexport.node.TxtNode;
import com.waspring.waexport.node.XmlNode;

/**
 * 
 * 
 * @author felly
 
 * 
 */
public final class NodeUtil {

	public static ExportNode createExportNode(Map data)  {
		ExportNode root = new ExcelNode(data);
		root.next(new PdfNode(data)).next(new HtmlNode(data)).next(
				new XmlNode(data)).next(new DocNode(data)).next(
				new TxtNode(data));
		return root;
	}
}
