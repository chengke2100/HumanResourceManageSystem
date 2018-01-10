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
		
		/* $("select[name='deptId']").change(function(){
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
		$("input[name='apply']").click(function(){
			var recruitsId = $(this).prev().val();
			alert(recruitsId);
			$.ajax({
				url:"${pageContext.request.contextPath}/recruit/apply",
				data:{recruitsId:recruitsId},
				dataType:"text",
				success:function(data){
					if(data=="1"){
						alert("您还没有创建简历，请先去创建简历");
					}else if(data=="2"){
						alert("您简历上的应聘职位与招聘上的职位不相同 ，请先修改简历或者改投其它职位");
					}else if(data=="3"){
						alert("您还有未完结的应聘，请等待上个应聘完结之后在投递");
					}else{
						alert("申请成功,请注意关注企业反馈信息");
					}
				}
			})
		})
	})
	
	function queryResume(){
		$(".flag").hide();
		$("#resume").show();
		if(${empty requestScope.resume}){
			$.ajax({
				url:"${pageContext.request.contextPath}/user/findResume",
				data:{},
				type:"post",
				dataType:"text",
				success:function(data){
					if(data=="on"){
	//					 window.location.reload();
						 alert(aaa);
						 $("#resume").show();
					}
				}
			})
		}
	} */

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
		window.location.href = ${pageContext.request.contextPath}+"interview/noHire/{"+interId+"}";
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
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="录用">
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="不录用" onclick="noHire()">
			</form>
		</div>
	</div>
</body>
</html>