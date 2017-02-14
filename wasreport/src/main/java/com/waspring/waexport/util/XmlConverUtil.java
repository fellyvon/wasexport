package com.waspring.waexport.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlConverUtil {

	/**
	 * Map转XML
	 * 
	 * @param doc
	 * @return
	 */

	public static String mapToXML(Map map) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element header = root.addElement("header");
		List head = (List) map.get("header");
		int size = head.size();
		for (int i = 0; i < size; i++) {
			Map sub = (Map) head.get(i);

			// 第二种:通过Map.entrySet()使用iterator()遍历key和value
			Iterator<Map.Entry<Integer, String>> iterator = sub.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, String> entry = iterator.next();

				header.addElement(String.valueOf(entry.getKey())).addText(
						String.valueOf(entry.getValue()));

			}

			break;
		}

		Element bodys = root.addElement("body");
		List data = (List) map.get("data");
		size = data.size();
		for (int i = 0; i < size; i++) {
			List sub = (List) data.get(i);
			int len = sub.size();
			Element body = bodys.addElement("row");
			for (int j = 0; j < len; j++) {
				body.addElement("col").addText(String.valueOf(sub.get(j)));

			}

		}

		return document.asXML();

	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> Dom2Map(Document doc) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List list = e.elements();
			if (list.size() > 0) {
				map.put(e.getName(), Dom2Map(e));
			} else
				map.put(e.getName(), e.getText());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static Map Dom2Map(Element e) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {
					Map m = Dom2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals(
								"java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals(
								"java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals(
								"java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals(
								"java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}
}
