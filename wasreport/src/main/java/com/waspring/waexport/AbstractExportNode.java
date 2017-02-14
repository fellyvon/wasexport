package com.waspring.waexport;

import java.util.Map;

import com.waspring.waexport.exp.NodeException;

/**
 * 导出类型查找实现，工具指定导出类型查找到导出实现对象
 * 
 * @author felly
 * 
 */
public abstract class AbstractExportNode implements ExportNode {
	protected String name = "";
	protected Map data;// //数据
/**
 * 
 * @param name  定义导出类型
 * @param data  定义需要导出的数据
 */
	public AbstractExportNode(String name, Map data) {
		this.name = name;
		this.data = data;
	}

	private ExportNode next;

	public ExportNode next(ExportNode next) {
		this.next = next;
		return next;
	}

 
	public abstract IExport getExport() ;

	public IExport node(String name) throws NodeException {
		if (this.name.equals(name)) {
			return this.getExport();
		} else {
			if (next == null) {
				throw new NodeException("已经到达链尾！！");
			}
			return next.node(name);
		}
	}

}
