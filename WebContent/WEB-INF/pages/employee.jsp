<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/tourist.css">
<title>员工界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".flag").hide();
		$("#right2").show();
		if(${!empty requstScope.resume}){
			$("#information").show();
		}
		$("input[name='oldPassword']").blur(function(){
			var oldPassword = $(this).val();
			alert(oldPassword);
			$.ajax({
				url:"${pageContext.request.contextPath }/user/checkPassword",
				type:"post",
				data:{oldPassword:oldPassword},
				dataType:"text",
				success:function(data){
					if(data=="success"){
						$(".span2").html("");
					}else{
						$(".span2").html("密码错误");
					}
				}
				
			})
		})
		$("input[name='checkPassword']").blur(function(){
			var newPassword = $("input[name='newPassword']").val();
			var checkPassword = $(this).val();
			if(newPassword!=checkPassword){
				$(".span1").html("两次输入的密码不一样");
			}else{
				$(".span1").html("");
			}
		})
		$("input[name='update']").click(function(){
			var newPassword = $("input[name='newPassword']").val();
			$.ajax({
				url:"${pageContext.request.contextPath }/user/updatePassword",
				type:"post",
				data:{newPassword:newPassword},
				dataType:"text",
				success:function(data){
					if(data=="ok"){
						alert("修改成功");
						$(".flag").hide();
					}
				}
			})
			return false;
		})
	})
	
	
	function updatePassword(){
		$(".flag").hide();
		$("#update").show();
	}
	
	function quit(){
		if(confirm("是否确认退出？")){
			return true;	
		}else{
			return false;
		}
		return false;
	}
</script>
</head>
<body>
	<div id="left">
		<ul id="navigation" >
			<li><a href="${pageContext.request.contextPath }/resume/showInformation">个人信息</a></li>
			<li><a href="#" onclick="queryResume()">我的考勤</a></li>
			<li><a href="#" onclick="updatePassword()">修改密码</a></li>
			<li><a href="#">我的奖惩</a></li>
			<li><a href="#">部门职位</a></li>
			<li><a href="#">我的薪资</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginPage" onclick="return quit()">退出</a></li>
		</ul>
	</div>
	<div id="right" >
		<div class="flag" id="information">
			<form action="${pageContext.request.contextPath }/resume/saveInformation" method="post">
				<table border="2" cellpadding="10" cellspacing="0">
					<tr>
						<td colspan="4">个人信息</td>
					</tr>
					<tr>
						<td>真实姓名</td>
						<td><input type="text" name="realName" value="${requestScope.resume.realName }" readonly="readonly" ></td>
						<td>性别</td>
						<td>
							<c:if test="${requestScope.resume.sex eq '男'}">
								<input type="radio" value="男" name="sex" checked="checked" readonly="readonly">男
								<input type="radio" value="女" name="sex" readonly="readonly">女
							</c:if>
							<c:if test="${requestScope.resume.sex eq '女'}">
								<input type="radio" value="男" name="sex"readonly="readonly">男
								<input type="radio" value="女" name="sex" checked="checked"readonly="readonly">女
							</c:if>
						</td>
					</tr>
					<tr>
						<td>年龄</td>
						<td><input type="number" name="age" value="${requestScope.resume.age }" required="required"></td>
						<td>学历</td>
						<td><input type="text" value="${requestScope.resume.education }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>联系方式</td>
						<td><input type="tel" name="phoneNumber"  value="${requestScope.resume.phoneNumber }" required="required"></td>
						<td>e-mail</td>
						<td><input type="email" name="email" value="${requestScope.resume.email }" required="required"></td>
					</tr>
					<tr>
						<td>职位</td>
						<td>
							${requestScope.resume.position.department.deptName }&nbsp;&nbsp;&nbsp;
							${requestScope.resume.position.name }
						</td>
						<td>政治面貌</td>
						<td><input type="text" value="${requestScope.resume.politicalStatus }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>入职时间</td>
						<td><f:formatDate value="${sessionScope.user.entryDate }" pattern="yyyy-MM-dd"/></td>
						<td>爱好</td>
						<td><input type="text" value="${requestScope.resume.hobbys }" name="hobbys" required="required"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="保存"></td>
						<td colspan="2" align="center"><input type="button" value="取消"></td>
					</tr>
				</table>
			</form>
		</div>
		<div align="center" class="flag" id="update">
			<h2>修改密码</h2>
			<form action="#" method="post">
				原&nbsp;密&nbsp;码:<input type="password" name="oldPassword">
				<span class="span2" style="color:rgb(80,80,80)"></span><br/><br/>
				新&nbsp;密&nbsp;码:<input type="password" name="newPassword"><br/><br/>
				确认密码:<input type="password" name="checkPassword">
				<span class="span1" style="color:rgb(80,80,80)"></span><br/><br/>
				<input type="submit" value="修改" name="update">
			</form>
		</div>
		<div align="center" class="flag" id="queryRecruits">
			<!-- 考勤 -->
		</div>
	</div>
	<div id="right2" class="flag">
		<form action="${pageContext.request.contextPath }/pay/clockin" method="post">
			<input type="submit" value="上班打卡">
		</form>
	</div>
</body>
</html>