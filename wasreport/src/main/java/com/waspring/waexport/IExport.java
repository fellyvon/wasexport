package  com.waspring.waexport;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 导出抽象高层接口，目标将数据导出到输出流
 * 
 * @author felly
 * 
 */

public interface IExport {
	String TAB = "\t\t\t";
	String ENTER = "\r\n";

	// ///导出
    void export(OutputStream servletOut) throws IOException;

	String HEADER = "header";
	String DATA = "data";
	
	String TITLE="title";
}
