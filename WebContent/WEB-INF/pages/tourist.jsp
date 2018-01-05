<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/tourist.css">
<title>游客界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".flag").hide();
		$("select[name='deptId']").change(function(){
			var did = $(this).val();
			$.ajax({
				url:"${pageContext.request.contextPath }/user/positions",
				type:"post",
				data:{did:did},
				dataType:"json",
				success:function(data){
					$("select[name=pid]").empty();
//					$("select[name=positon]").append("<option>职位</option>");
					$.each(data,function(idx,item){
						$("select[name=pid]").append("<option value="+item.pid+">"+item.name+"</option>");
					})
				}
			})
		})
		$("input[name='oldPassword']").blur(function(){
			var oldPassword = $(this).val();
			$.ajax({
				url:"${pageContext.request.contextPath }/user/checkPassword",
				type:"post",
				data:{oldPassword:oldPassword},
				datatype:"text",
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
				datatype:"text",
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
	
	function queryResume(){
		$(".flag").hide();
		$("#resume").show();
	}
	function goBack(){
		$(".flag").hide();
	}
	function updatePassword(){
		$(".flag").hide();
		$("#update").show();
	}
	function quit(){
		if(!confirm("是否确认退出？")){
			return false;
		}		
	}
</script>
</head>
<body>
	<div id="left">
		<ul id="navigation" >
			<li><a href="#">反馈</a></li>
			<li><a href="#" onclick="queryResume()">查看简历</a></li>
			<li><a href="#" onclick="updatePassword()">修改密码</a></li>
			<li><a href="#">其它</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginPage" onclick="quit()">退出</a></li>
		</ul>
	</div>
	<div id="right" >
		<form action="${pageContext.request.contextPath }/resume/add" method="post">
			<input type="hidden" name="resumeId" value="${requestScope.resume.rid }">
			<table border="2" cellpadding="10" cellspacing="0" id="resume" class="flag" >
				<tr>
					<td colspan="4" align="center">简历</td>
				</tr>
				<tr>
					<td>真实姓名</td>
					<td><input type="text" name="realName" value="${requestScope.resume.realName }" placeholder="请输入您的真实姓名"></td>
					<td>性别</td>
					<td>
					<c:if test="${requestScope.resume.sex eq '男'}">
						<input type="radio" value="男" name="sex" checked="checked">男
						<input type="radio" value="女" name="sex">女
					</c:if>
					<c:if test="${requestScope.resume.sex eq '女'}">
						<input type="radio" value="男" name="sex">男
						<input type="radio" value="女" name="sex" checked="checked">女
					</c:if>
					<c:if test="${empty requestScope.resume.sex}">
						<input type="radio" value="男" name="sex">男
						<input type="radio" value="女" name="sex">女
					</c:if>
					</td>			
				</tr>
				<tr>
					<td>年龄</td>
					<td><input type="number" name="age" value="${requestScope.resume.age }"></td>
					<td>学历</td>
					<td>
						<select name="education">
							<option>学历</option>
							<option value="大学专科" <c:if test="${requestScope.resume.education eq '大学专科' }">selected</c:if> >大学专科</option>
							<option value="大学本科" <c:if test="${requestScope.resume.education eq '大学本科' }">selected</c:if>>大学本科</option>
							<option value="硕士" <c:if test="${requestScope.resume.education eq '硕士' }">selected</c:if>>硕士</option>
							<option value="博士" <c:if test="${requestScope.resume.education eq '博士' }">selected</c:if>>博士</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td><input type="tel" name="phoneNumber"  value="${requestScope.resume.phoneNumber }"></td>
					<td>e-mail</td>
					<td><input type="email" name="email" value="${requestScope.resume.email }"></td>
				</tr>
				<tr>
					<td>应聘职位</td>
					<td>
						<select name="deptId">
							<option>部门</option>
							<c:forEach items="${requestScope.departments }" var="department">
								<option value="${department.did }" <c:if test="${department.did==requestScope.resume.position.department.did }">selected</c:if>>${department.deptName }</option>
							</c:forEach>
						</select>
						<select name="pid">
							<option value="${requestScope.position.pid }">${requestScope.position.name }</option>
						</select>
					</td>
					<td>政治面貌</td>
					<td>
						<select name="politicalStatus">
							<option value="群众" <c:if test="${requestScope.resume.politicalStatus eq '群众' }">selected</c:if>>群众</option>
							<option value="共产党员" <c:if test="${requestScope.resume.politicalStatus eq '共产党员' }">selected</c:if>>共产党员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>上份工作</td>
					<td><input type="text" name="previousJob" value="${requestScope.resume.previousJob }"></td>
					<td>工作经验</td>
					<td><input type="text" name="workExperience" value="${requestScope.resume.workExperience }"></td>
				</tr>
				<tr>
					<td>期望薪资</td>
					<td>
						<select name="expectedSalary">
							<option value="2000-5000" <c:if test="${requestScope.resume.expectedSalary eq '2000-5000' }">selected</c:if>>2000-5000</option>
							<option value="5000-8000"<c:if test="${requestScope.resume.expectedSalary eq '5000-8000' }">selected</c:if>>5000-8000</option>
							<option value="8000-10000" <c:if test="${requestScope.resume.expectedSalary eq '8000-10000' }">selected</c:if>>8000-10000</option>
							<option value="10000以上" <c:if test="${requestScope.resume.expectedSalary eq '10000以上' }">selected</c:if>>10000以上</option>
						</select>
					</td>
					<td>兴趣爱好</td>
					<td><input type="text" name="hobbys" value="${requestScope.resume.hobbys }"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="保存"></td>
					<td colspan="2" align="center"><input type="button" value="返回" onclick="goBack()"></td>
				</tr>
			</table>
		</form>
		<div align="center" class="flag" id="update">
			<h2>修改密码</h2>
			<form action="#" method="post">
				原&nbsp;密&nbsp;码:<input type="password" name="oldPassword"><br/><br/>
				<span class="span2" style="color:rgb(80,80,80)"></span><br/>
				新&nbsp;密&nbsp;码:<input type="password" name="newPassword"><br/><br/>
				确认密码:<input type="password" name="checkPassword"><br/><br/>
				<span class="span1" style="color:rgb(80,80,80)"></span><br/>
				<input type="submit" value="修改" name="update">
			</form>
		</div>
	</div>
</body>
</html>