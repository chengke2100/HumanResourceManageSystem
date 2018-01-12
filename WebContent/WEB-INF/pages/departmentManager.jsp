<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/tourist.css">
<title>部门管理员界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".flag").hide();
		if(${!empty requestScope.interviews}){
			alert("部门有应聘者，请注意前去面试");
		}
		if(${!empty requestScope.resume}){
			$("#resume").show();
		}
		if(${!empty requestScope.hire}){
			alert("录用成功");
		}
		if(${!empty requestScope.nohire}){
			alert("拒绝成功");
		}
		$("input[name='basicPay']").blur(function(){
			var basicPay = $(this).val();
			var regu = /^[1-9]\d*$/;
			if(!regu.test(basicPay)){
				alert("请输入合理的正整数");
				$("input[name='hire']").attr("disabled",true);
			}else{
				$("input[name='hire']").attr("disabled",false);
			}
		})
	})

	function inform(){
		$(".flag").hide();
		$("#inform").show();
	}
	
	function showinform(){
		$("#showTrainingNotice").hide();
		$("#showinform").show();
	}
	
	function showTrainingNotice(){
		$("#showinform").hide();
		$("#showTrainingNotice").show();		
	}
	
	function noHire(){
		var interId = $("input[name='interId']").val();
		window.location.href = "${pageContext.request.contextPath}/interview/noHire/"+interId;
	}
	
	function hire(){
		$("#hire").show();		
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
			<li><a href="javascript:inform()">通知管理</a></li>
			<li><a href="#" onclick="queryResume()">绩效管理</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginPage" onclick="return quit()">退出</a></li>
		</ul>
	</div>
	<div id="right" >
		<div class="flag" id="inform">
			<div>
				<input type="button" value="面试通知" onclick="showinform()">
				<input type="button" value="培训通知" onclick="showTrainingNotice()">
			</div>
			<div class="flag" id="showinform">
				<table border="2" cellpadding="10" cellspacing="0">
					<tr>
						<td>序号</td>
						<td>应聘者ID</td>
						<td>面试时间</td>
						<td>操作</td>
					</tr>
				<c:forEach items="${requestScope.interviews }" var="interview">
					<tr>
						<td>${interview.interId }</td>
						<td>${interview.apply.userId }</td>
						<td>${interview.interviewTime }</td>
						<td><a href="${pageContext.request.contextPath }/interview/doInterview/${interview.interId }">面试</a></td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<div class="flag" id="showTrainingNotice">
				<!-- 培训通知 -->
			</div>
		</div>
		<div align="center" class="flag" id="resume">
			<h2>简历详情</h2>
			
			<table border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td>姓名</td>
					<td>${requestScope.resume.realName }</td>
					<td>年龄</td>
					<td>${requestScope.resume.age }</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>${requestScope.resume.sex }</td>
					<td>学历</td>
					<td>${requestScope.resume.education }</td>
				</tr>
				<tr>
					<td>手机</td>
					<td>${requestScope.resume.phoneNumber }</td>
					<td>邮箱</td>
					<td>${requestScope.resume.email }</td>
				</tr>
				<tr>
					<td>应聘职位</td>
					<td>${requestScope.resume.position.department.deptName }&nbsp;&nbsp;${requestScope.resume.position.name }</td>
					<td>期望薪资</td>
					<td>${requestScope.resume.expectedSalary }</td>
				</tr>
				<tr>
					<td>工作经验</td>
					<td>${requestScope.resume.workExperience }</td>
					<td>上份工作</td>
					<td>${requestScope.resume.previousJob }</td>
				</tr>
				<tr>
					<td>政治面貌</td>
					<td>${requestScope.resume.politicalStatus }</td>
					<td>兴趣爱好</td>
					<td>${requestScope.resume.hobbys }</td>
				</tr>
			</table>			
			<form action="${pageContext.request.contextPath }/interview/hire" method="post">
				<input type="hidden" value="${requestScope.interId }" name="interId">
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="录用" onclick="hire()">
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="不录用" onclick="noHire()"><br/><br/>
				<div class="flag" id="hire">
					请输入此员工的基本薪资:<input type="number" name="basicPay" required="required"><br/><br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="确认" name="hire">
				</div>
			</form>
		</div>
	</div>
</body>
</html>