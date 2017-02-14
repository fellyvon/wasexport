<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script src="export.js"></script>
<script>
var webRootPath="<%=request.getContextPath()%>";
</script>
</head>

<body>
	这是一个导出的例子
	<table id="bookTable">
		<thead>
			<tr>
				<th type="number">序号</th>
				<th type="string">书名</th>
				<th type="number">价格（元）</th>
				<th type="string">出版时间</th>
				<th type="number">印刷量（册）</th>
				<th type="ip">IP</th>
			</tr>
		</thead>
		<tbody>
			<tr class="hover">
				<td class="sequence">1</td>
				<td>狼图腾</td>
				<td>29.80</td>
				<td>2009-10</td>
				<td>50000</td>
				<td>192.168.1.125</td>
			</tr>
			<tr>
				<td class="sequence">2</td>
				<td>孝心不能等待</td>
				<td>29.80</td>
				<td>2009-09</td>
				<td>800</td>
				<td>192.68.1.125</td>
			</tr>
			<tr>
				<td class="sequence">3</td>
				<td>藏地密码2</td>
				<td>28.00</td>
				<td>2008-10</td>
				<td></td>
				<td>192.102.0.12</td>
			</tr>
			<tr>
				<td class="sequence">4</td>
				<td>藏地密码1</td>
				<td>24.80</td>
				<td>2008-10</td>
				<td></td>
				<td>215.34.126.10</td>
			</tr>
			<tr>
				<td class="sequence">5</td>
				<td>设计模式之禅</td>
				<td>69.00</td>
				<td>2011-12</td>
				<td></td>
				<td>192.168.1.5</td>
			</tr>
			<tr>
				<td class="sequence">6</td>
				<td>轻量级 Java EE 企业应用实战</td>
				<td>99.00</td>
				<td>2012-04</td>
				<td>5000</td>
				<td>192.358.1.125</td>
			</tr>
			<tr>
				<td class="sequence">7</td>
				<td>Java 开发实战经典</td>
				<td>79.80</td>
				<td>2012-01</td>
				<td>2000</td>
				<td>192.168.1.25</td>
			</tr>
			<tr>
				<td class="sequence" onclick="sortArray()">8</td>
				<td>Java Web 开发实战经典</td>
				<td>69.80</td>
				<td>2011-11</td>
				<td>2500</td>
				<td>215.168.54.125</td>
			</tr>
		</tbody>
	</table>
	<br>
	<input type='button' id='excelexport' value='导出excel' />
	<input type='button' id='docexport' value='导出doc' />
	<input type='button' id='txtexport' value='导出txt' />
	<input type='button' id='xmlexport' value='导出xml' />
	<input type='button' id='pdfexport' value='导出pdf' />

</body>
</html>
<script>
	$(function() {
		$("#excelexport").click(function(e) {
			exportFile("excel", $("#bookTable"));
		});
		$("#docexport").click(function(e) {
			exportFile("doc", $("#bookTable"));
		});
		$("#txtexport").click(function(e) {
			exportFile("txt", $("#bookTable"));
		});
		$("#xmlexport").click(function(e) {
			exportFile("xml", $("#bookTable"));
		});
		$("#pdfexport").click(function(e) {
			exportFile("pdf", $("#bookTable"));
		});
	});
</script>