package com.waspring.waexport.handle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * 导出pdf
 * 
 * @author felly
 * 
 * 
 */
public class PdfExport extends AbstractExport {

	public PdfExport(Map data) {
		super(data);
	}

	/**
	 * 
	 */
	@Override
	public void exportInnertion(OutputStream servletOut, List header, List data)
			throws IOException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, servletOut);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		// step 3
		document.open();
		// step 4
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new java.io.StringReader(
				HtmlExport.createHTML(header, data)));
		// step 5
		document.close();

	}

}
