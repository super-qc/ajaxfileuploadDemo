<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>异步文件上传</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/ajaxfileupload.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<script src="js/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function() {

		// jquery-ui style
		$(" button").button().click(function(event) {
			event.preventDefault();
		});

		$("#msg").dialog({
			autoOpen : false,
			show : {
				effect : "blind",
				duration : 1000
			},
			hide : {
				effect : "explode",
				duration : 1000
			},
			title : "警告",
			buttons : {
				"ok" : function() {
					$(this).dialog("close");
				}
			}
		});

		// 清除已上传文件事件
		$("#buttonClear").on("click", function(event) {
			if ($("#fileToUpload").val() != "") {
				$("#fileToUpload")[0].value = null;
			}
			return false;
		});
		$("#buttonUpload").on("click", function(event) {
			ajaxFileUpload();
			return false;
		});
	});

	function ajaxFileUpload() {
		// 判断是否选中文件
		if ($("#fileToUpload").val() === "") {
			$("#msg").html("请选择上传文件!")
			$("#msg").dialog("open");
		} else {
			$("#loading").ajaxStart(function() {
				$(this).show();
			}).ajaxComplete(function() {
				$(this).hide();
			});

			$.ajaxFileUpload({
				url : 'FileUploadServlet',
				secureuri : false,
				fileElementId : 'fileToUpload',
				dataType : 'json',
				data : {

				},
				success : function(data, status) {
					console.log("上传成功..");
					console.log(data);
					$("#file_path").val(data.file_path);
					$("#message").val(data.message);
				},
				error : function(data, status, e) {
					console.log("请求失败。")
					console.log(data);
					$("#msg").html("上传失败!错误信息：" + e)
					$("#msg").dialog("open");
				}
			});
		}
	}
</script>
</head>

<body>
	<hr />
	<div>
		<div align="center" style="margin-top: 10px;">
			<h2>
				<strong>ajax文件上传案例</strong>
			</h2>
		</div>
		<hr>
		<div style="margin-left: 28px;">
			<font>使用步骤:</font> <font color="orange">1.点击"选择文件",选中要上传的文件 </font> <font
				color="orange">2. 点击 "上传"。 </font>
		</div>
		<div style="margin-left: 20px; margin-top: 20px;">
			<img id="loading" src="image/loading.gif" style="display: none;">

			<form name="form" method="POST" enctype="multipart/form-data">
				<table cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td><input id="fileToUpload" type="file" name="fileToUpload"></td>

						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<button class="button" id="buttonClear">clear</button>
								<button id="buttonUpload">上传</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</form>
			<br>
			<hr>
			<div align="left" id="div_result">
				<h3>上传结果：</h3>
				<table style="margin-left: 30px;">
					<tbody>
						<tr>
							<td><em>文件路径：</em></td>
							<td><input type="text" id="file_path" style="width: 237px;"
								disabled="disabled"></input></td>
						</tr>
						<tr>
							<td><em>错误信息:</em></td>
							<td><textarea id="message" rows="5" cols="30"
									readonly="readonly" disabled="disabled"></textarea>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="msg"></div>
</body>
</html>