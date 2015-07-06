<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>图书管理系统</title>
	<link rel="stylesheet" href="CSS/index.css" />
	<script type="text/javascript" src='Script/jquery-1.7.2.min.js'></script>
	<script type="text/javascript" src='Script/index.js'></script>
	<base target="iframe" />
</head>
<body>
	<%
		Object username = session.getAttribute("username");
		if (username == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<div id="top">
		<div class='logo'></div>
		<div class='t_title'>图书管理系统</div>
		<div class='menu'>
			<ul>
				<li class='first first_cur'>
					<a href="#"><span>首&nbsp;页</span></a>
				</li>
				<li class='next'>
					<a href="bookInfo.jsp"><span>图书管理</span></a>
				</li>
				<li>
					<a href="readerInfo.jsp"><span>读者管理</span></a>
				</li>
				<li>
					<a href="borrow.jsp"><span>借阅管理</span></a>
				</li>
				<li>
					<a href="borrowBook.jsp"><span>统计分析</span></a>
				</li>
				<li class='last'>
					<a href="userInfo.jsp"><span>系统设置</span></a>
				</li>
			</ul>
			<div id='user'>
				<span class='user_state'>当前管理员：[<span>${username}</span>]</span>
				<a href="loginOut" target='_self' id='login_out'></a>
			</div>
		</div>
	</div>
	<div id='left'>
		<div class='nav'>
			<div class="nav_u"><span class="pos down">图书管理</span></div>
		</div>
		<ul class='option'>
			<li><a href='addBook.jsp'>添加图书</a></li>
			<li><a href='bookInfo.jsp'>图书列表</a></li>
		</ul>

		<div class='nav'>
			<div class="nav_u"><span class="pos down">读者管理</span></div>
		</div>
		<ul class='option'>
			<li><a href='addReader.jsp'>添加读者</a></li>
			<li><a href='readerInfo.jsp'>读者列表</a></li>
		</ul>
		<div class='nav'>
			<div class="nav_u"><span class="pos down">借阅管理</span></div>
		</div>
		<ul class='option'>
			<li><a href='borrow.jsp'>借阅图书</a></li>
			<li><a href='return.jsp'>归还图书</a></li>
		</ul>
		<div class='nav'>
			<div class="nav_u"><span class="pos down">统计分析</span></div>
		</div>
		<ul class='option'>
			<li><a href='borrowBook.jsp'>已借图书</a></li>
			<li><a href='history.jsp'>历史记录</a></li>
		</ul>
		<div class='nav'>
			<div class="nav_u"><span class="pos down">系统设置</span></div>
		</div>
		<ul class='option'>
			<li><a href='register.jsp'>添加用户</a></li>
			<li><a href='userInfo.jsp'>用户列表</a></li>
			<li><a href='loginOut'>退出系统</a></li>
		</ul>
	</div>
	<div id="right">
		<iframe src="#" frameborder="0" name='iframe' scrolling="auto" width="1100" height="800"></iframe>
	</div>
</body>
</html>