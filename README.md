# wasreport
>一款将数据导出为Excel,Doc,Txt,XML,PDF等常用格式的导出组件，简约而极具扩展性，一键完成页面数据多种格式导出。
>目前仅支持表格形式矩阵数据导出
 
本项目主要包含两个模块，wasreport和wareport-client:
1.wasreport模块是本项目的核心，用于实现JDBC的操作。
2.wareport-client模块是wasreport使用的一个实现，包括一个web版本简单实例，可以部署到容器中运行。


#如何使用?
1.建立新工程并引入wasreport-1.0.0.jar
   可以使用maven方式引入:
 ``` xml
     	<dependency>
			<groupId>com.waspring</groupId>
			<artifactId>wasreport</artifactId>
			<version>1.0.0</version>
		</dependency>
```
2.在配置文件web.xml中配置servlet用于导出文件:
    ``` xml
 
	<servlet>
		<servlet-name>ReportServer</servlet-name>
		<servlet-class>
		com.waspring.waexport.servlet.ReportServlet
		</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>ReportServer</servlet-name>
		<url-pattern>/ReportServer</url-pattern>
	</servlet-mapping>
 
```
3.建立jsp并引入jquery和export.js
 ``` xml
<script src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script src="export.js"></script>
<script>
var webRootPath="<%=request.getContextPath()%>";
</script>
 ```
4.初始化表格数据：

 ``` xml
    <table id="bookTable">
	 <thead>
			<tr>
				<th type="number">...
			 
			</tr>
		</thead>
		<tbody>
			<tr class="hover">
				<td>...</td>
				...
			</tr>
			 ...
			</tbody>
		
	</table>
 ```
 5.导出表格数据
  ``` xml
  	<input type='button' id='excelexport' value='导出excel' />
	<input type='button' id='docexport' value='导出doc' />
	<input type='button' id='txtexport' value='导出txt' />
	<input type='button' id='xmlexport' value='导出xml' />
	<input type='button' id='pdfexport' value='导出pdf' />
	
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
   ```