/////////////////文件導出用到////////////////////////////////////////////////////////////
function exportFile(ext, exportGrid, fileName) {
	var f = $('<form action=\"' + webRootPath + '/ReportServer?u='
			+ getRandomId()
			+ '\" method="post" id="export_ReportServletForm"></form>');
	var i = $('<input type="hidden" id="export_content" name="content" />');
	var l = $('<input type="hidden" id="export_fileName" name="fileName" />');
	var ex = $('<input type="hidden" id="export_ext" name="ext" />');

	i.val(JSON.stringify(changeToTableForJquery(exportGrid)));
	i.appendTo(f);
	l.val(fileName);
	l.appendTo(f);

	ex.val(ext);
	ex.appendTo(f);

	f.appendTo(document.body).submit();
	document.body.removeChild(f);
}
/** **jquery的实现* */
function changeToTableForJquery(printDatagrid) {

	var frozenColumns = printDatagrid.children('thead').find('tr').find('th'); // 获取table对象下的thead
																				// .tr
																				// .th
	var rows = printDatagrid.children('tbody').find('tr'); // 获取table对象下的tbody
															// .tr
	var nameList = new Array();
	// 载入title

	frozenColumns.each(function(index) {
        var item=new Object();
        item.title=$(this).text();
		nameList.push(item);

	});

	var content = new Array();
	rows.each(function() {
		var cols = $(this).find('td');
		var curRow = new Array();
		cols.each(function(index) {
			curRow.push($(this).text());

		});
		content.push(curRow);
	});

	var obj = new Object();
	obj.header = nameList;
	obj.data = content;

	return obj;
}
/** **easyui框架的实现* */
function changeToTableForEasyUi(printDatagrid) {

	var frozenColumns = printDatagrid.datagrid("options").frozenColumns; // 得到frozenColumns对象
	var columns = printDatagrid.datagrid("options").columns; // 得到columns对象
	var nameList = new Array();

	// 载入title
	if (typeof columns != 'undefined' && columns != '') {
		$(columns)
				.each(
						function(index) {

							if (typeof frozenColumns != 'undefined'
									&& typeof frozenColumns[index] != 'undefined') {
								for ( var i = 0; i < frozenColumns[index].length; ++i) {
									if (!frozenColumns[index][i].hidden) {

										if (typeof frozenColumns[index][i].field != 'undefined'
												&& frozenColumns[index][i].field != '') {
											nameList
													.push(frozenColumns[index][i]);
										}

									}
								}
							}
							for ( var i = 0; i < columns[index].length; ++i) {
								if (!columns[index][i].hidden) {

									if (typeof columns[index][i].field != 'undefined'
											&& columns[index][i].field != '') {
										nameList.push(columns[index][i]);
									}

								}
							}

						});
	}
	// 载入内容
	var rows = printDatagrid.datagrid("getRows"); // 这段代码是获取当前页的所有行
	var content = new Array();
	for ( var i = 0; i < rows.length; ++i) {
		var curRow = new Array();
		for ( var j = 0; j < nameList.length; ++j) {
			var e = nameList[j].field.lastIndexOf('_0');
			if (e + 2 == nameList[j].field.length) {
				curRow.push(rows[i][nameList[j].field.substring(0, e)]);
			} else {
				curRow.push(rows[i][nameList[j].field]);
			}

		}
		content.push(curRow);
	}

	var obj = new Object();
	obj.header = nameList;
	obj.data = content;

	return obj;
}

// ////////////////文件導出結束///////////////////////////////////////////////////////////


/**
 * 获取随机不重复数字
 */
function getRandomId() {
	return (new Date()).getTime();
}
