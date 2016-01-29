<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>show</title>
</head>
<body>
	<table>
		<tr>
			<td>窗宽:</td>
			<td>${dicomfile.getWindowWidth()}</td>
		</tr>
		<tr>
			<td>窗位:</td>
			<td>${dicomfile.getWindowCenter()}</td>
		</tr>
		<tr>
			<td>姓名:</td>
			<td>${dicomfile.getPatientName()}</td>
		</tr>
		<tr>
			<td>年龄:</td>
			<td>${dicomfile.getPatientAge()}</td>
		</tr>
		<tr>
			<td>性别:</td>
			<td>${dicomfile.getPatientSex()}</td>
		</tr>
		<tr>
			<td>就诊日期:</td>
			<td>${dicomfile.getStudyDate()}</td>
		</tr>
	</table>
</body>
</html>