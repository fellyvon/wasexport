package com.waspring.waexport.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.waspring.waexport.ExportNode;
import com.waspring.waexport.exp.NodeException;
import com.waspring.waexport.node.ExcelNode;
import com.waspring.waexport.util.EscapeUtil;
import com.waspring.waexport.util.NodeUtil;

public class ReportServlet extends HttpServlet {
	/* long serialVersionUID */
	private static final long serialVersionUID = 7969131818891585800L;
	private boolean isFormat = true;
	private Map extMap = new HashMap();
	{
		extMap.put(ExportNode.EXCEL, "xls");
		extMap.put(ExportNode.DOC, "doc");
		extMap.put(ExportNode.HTML, "html");
		extMap.put(ExportNode.PDF, "pdf");
		extMap.put(ExportNode.TXT, "txt");
		extMap.put(ExportNode.XML, "xml");
	}

	public ReportServlet() {
	}

	/**
	 * 通用导出文件方法
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, UnsupportedEncodingException, IOException {

		isFormat = true;
		String content = request.getParameter("content");
		String ext = (String) request.getParameter("ext");
		
		if (ext==null||"".equals(ext)) {// ///默认就是excel
			ext = ExportNode.EXCEL;
		}
		String fileName = request.getParameter("fileName");
		if (fileName==null||"".equals(fileName)) {
			fileName = ext + System.currentTimeMillis();
		}
		ServletOutputStream servletOut = response.getOutputStream();

		String filename = EscapeUtil.codeISO(fileName);

		if (ext.toLowerCase().equals("print")) {
			response.setContentType("text/html; charset=utf-8");
		} else {
			String exp = (String) extMap.get(ext);
			if (exp==null||"".equals(exp)) {
				exp = "xls";
			}
			response.setContentType("application/x-msdownload;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ filename + "." + exp);
		}
		Map data = JSON.parseObject(content, Map.class);
		try {

			ExportNode node = NodeUtil.createExportNode(data);
			node.node(ext.toLowerCase()).export(servletOut);
		} catch (NodeException e) {
			e.printStackTrace();
			(new ExcelNode(data)).getExport().export(servletOut);
		}

		finally {
			servletOut.flush();
			servletOut.close();
		}
	}
}