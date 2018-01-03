<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/register.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".register").click(function(){
			var password = $("input[name='password']").val();
			var password1 = $(".pass").val();
			if(password!=password1){
				$(".span2").html("两次输入的密码不一样");
				return false;
			}
		})
	})
</script>
</head>
<body>
	<div align="center">
		<h1 style="color:orange">注册</h1>
		<form action="${pageContext.request.contextPath }/user/register" method="post">
			<span style="color:yellow">用&nbsp;&nbsp;户&nbsp;名:&nbsp;</span><input type="text" name="userName" required="required"><br/>
			<span class="span1" style="color:rgb(80,80,80)"></span><br/>
			<span style="color:yellow">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</span><input type="password" name="password" required="required" ><br/><br/>
			<span style="color:yellow">确认密码:&nbsp;</span><input type="password" required="required" class="pass"><br/>
			<span class="span2" style="color:rgb(80,80,80)"></span><br/><br/>
			<input type="submit" value="注册" class="register" >
		</form><br/>
		<form action="${pageContext.request.contextPath }/user/loginPage" method="post">
			<input type="submit" value="已有账号，直接登录" class="login">
		</form>
	</div>
</body>
</html>