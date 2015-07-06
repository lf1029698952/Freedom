<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>课程名</th>
			<th>学分</th>
			<th>操作</th>
		</tr>
		<%-- <s:iterator value="#attr.users">
			<tr>
				<td><s:property value="schoolnum" /></td>
				<td><s:property value="uname" /></td>
				<td><s:property value="academy" /></td>
				<td><s:property value="enrollment" /></td>
				<td><s:property value="major" /></td>
				<td><s:a href="/admin/admindeleteuser?"> 删除</s:a></td>
			</tr>
		</s:iterator> --%>
		<c:forEach var="course" items="${courses}">
			<tr>
				<td>${course.courseName}</td>
				<td>${course.coursepoint}</td>
				<td><a href="admindeletecourse?courseid=${course.id} "> 删除</a></td>
			</tr>

		</c:forEach>

	</table>
	当前页面：
	<s:property value="#attr.currentpage" />
	总共页面：
	<s:property value="#attr.allpagenum" />
	<s:a href="admincourselist?pagenum=1">1</s:a>
	<s:a href="admincourselist?pagenum=2">2</s:a>
</body>
</html>