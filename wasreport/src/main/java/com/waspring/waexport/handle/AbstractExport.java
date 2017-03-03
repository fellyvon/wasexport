package com.waspring.waexport.handle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.waspring.waexport.IExport;

/**
 * 导出数据的抽象实现，统一定义数据头描述和数据体描述
 * 
 * 
 */
public abstract class AbstractExport implements IExport {

	public void export(OutputStream servletOut) throws IOException {
		if (this.valid())
			return;

		exportInnertion(servletOut, this.getHeader(), this.getData());
	}

	public abstract void exportInnertion(OutputStream servletOut, List header,
			List data) throws IOException;

	private Map data = null;

	/**
	 * 输入数据初始<br/>
	 * Map作为参数可以方便JSON格式与java对象的转化<br/>
	 * 如下为合适的格式： {\
	 * "header\":[{\"title\":\"任务名称\",\"field\":\"jobName\",\"width\":\"10%\",\"sortable\":true,\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-jobName\"},{\"title\":\"任务分组\",\"field\":\"jobGroup\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-jobGroup\"},{\"title\":\"任务状态\",\"field\":\"jobStatus\",\"width\":\"5%\",\"boxWidth\":49,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-jobStatus\"},{\"title\":\"是否同步\",\"field\":\"isConcurrent\",\"width\":\"5%\",\"boxWidth\":49,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-isConcurrent\"},{\"title\":\"执行表达式\",\"field\":\"cronExpression\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-cronExpression\"},{\"title\":\"执行java类\",\"field\":\"beanClass\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-beanClass\"},{\"title\":\"执行方法\",\"field\":\"methodName\",\"width\":\"5%\",\"boxWidth\":49,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-methodName\"},{\"title\":\"创建人员\",\"field\":\"createNo\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-createNo\"},{\"title\":\"创建时间\",\"field\":\"createTime\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-createTime\"},{\"title\":\"最后修改时间\",\"field\":\"updateTime\",\"width\":\"10%\",\"boxWidth\":107,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-updateTime\"},{\"title\":\"描述信息\",\"field\":\"description\",\"width\":\"15%\",\"boxWidth\":165,\"deltaWidth\":9,\"cellClass\":\"datagrid-cell-c1-description\"}],\"data\":[[\"1\",\"1\",\"停止\",\"是\"
	 * , \ " * * * * * ?\",\"1\",\"1\",null,\"2015-06-25
	 * 19:55:17.0\",\"2015-06-25
	 * 19:56:27.0\",\"test1\"],[\"全额\",\"133213\",\"停止\",\"否\",\"* * * * *
	 * ?\",\"323\",\"3213\",null,\"2015-06-02 13:09:21.0\",\"2015-06-03
	 * 17:32:20.0\",\"321312\"]]} <br/>
	 * 其中header对象的对应数组的长度与data对应的二维数组中每个元素的长度必须相等，
	 * 这是因为通过上述格式来构造一个严格意义上的Table,header定义table的列属性。 data定义table的行数据
	 * 
	 * @param data
	 */
	public AbstractExport(Map data) {
		this.data = data;
	}

	public boolean valid() {
		return data == null || data.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getHeader() {

		List<Map<String, String>> header = (List<Map<String, String>>) data
				.get(HEADER);
		return header;
	}

	@SuppressWarnings("unchecked")
	public List<List<String>> getData() {

		List<List<String>> da = (List<List<String>>) data.get(DATA);

		//避免出现空异常，若为空数据，允许导出头部，所以这里不做异常处理
		if (da == null) {
			da = new ArrayList<List<String>>();
		}
		return da;
	}

}
