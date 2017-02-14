package  com.waspring.waexport;

import com.waspring.waexport.exp.NodeException;

/**
 * 导出类型抽象
 * 
 * @author felly
 
 * 
 */
public interface ExportNode {
	IExport node(String name) throws NodeException;

	public ExportNode next(ExportNode next);

	String EXCEL = "excel";
	String HTML = "html";
	String PDF = "pdf";
	String XML = "xml";
	String TXT = "txt";
	String DOC = "doc";

}
