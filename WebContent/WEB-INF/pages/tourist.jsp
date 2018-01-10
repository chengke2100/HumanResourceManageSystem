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
<title>游客界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".flag").hide();
		if(${!empty requestScope.recruits}){
			$("#queryRecruits").show();
		}
		if(${empty requestScope.resume}){
			$("select[name='pid']").append("<option>职位</option>");
		}
		if(${requestScope.interview.isInterview eq "未确认"}){
			alert("通知:您有一份面试邀请，请在反馈中查看");
		}
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
	}
	function goBack(){
		$(".flag").hide();
	}
	function updatePassword(){
		$(".flag").hide();
		$("#update").show();
	}
	
	function feedback(){
		$(".flag").hide();
		$("#feedback").show();
	}
	
	function join(interId){
		if(!confirm("确认参加此次面试吗？")){
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath }/interview/join",
			type:"post",
			data:{interId:interId},
			dataType:"text",
			success:function(data){
				$("#isInterview").empty();
				$("#isInterview").text("按时面试");
			}
		})
	}
	
	function skip(interId){
		if(!confirm("确认取消此次面试吗？")){
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath }/interview/skip",
			type:"post",
			data:{interId:interId},
			dataType:"text",
			success:function(data){
				$("#isInterview").empty();
				$("#isInterview").text("不参加面试");
			}
		})
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
			<li><a href="javascript:feedback()">反馈</a></li>
			<li><a href="#" onclick="queryResume()">查看简历</a></li>
			<li><a href="#" onclick="updatePassword()">修改密码</a></li>
			<li><a href="${pageContext.request.contextPath }/recruit/showAll" onclick="queryRecuit()">查看招聘信息</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginPage" onclick="return quit()">退出</a></li>
		</ul>
	</div>
	<div id="right" >
		<div class="flag" id="feedback">
			<form action="#" method="post">
				<table border="2" cellpadding="10" cellspacing="0">
					<tr>
						<td colspan="5" align="center">反馈信息</td>
					</tr>
					<tr>
						<td>投递时间</td>
						<td>是否查看</td>
						<td>是否面试</td>
						<td>面试时间</td>
						<td>是否录用</td>
					</tr>
					<tr>
						<td><f:formatDate value="${requestScope.interview.apply.deliverTime}" pattern="yyyy-MM-dd"/></td>
						<td>
							<c:choose>
								<c:when test="${requestScope.interview.apply.isRead }">已查看</c:when>
								<c:otherwise>未查看</c:otherwise>
							</c:choose>
						</td>
						<td id="isInterview">
							<c:choose>
								<c:when test="${requestScope.interview.isInterview eq '按时面试' }">按时面试</c:when>
								<c:when test="${requestScope.interview.isInterview eq '不参加面试' }">不参加面试</c:when>
								<c:otherwise>
									<input type="button" value="是" onclick="join(${requestScope.interview.interId})">
									<input type="button" value="否" onclick="skip(${requestScope.interview.interId})">
								</c:otherwise>
							</c:choose>				
						</td>
						<td><f:formatDate value="${requestScope.interview.interviewTime }" pattern="yyyy-MM-dd"/></td>
						<td>${requestScope.interview.isHire }</td>
					</tr>
				</table>
			</form>
		</div>
		<form action="${pageContext.request.contextPath }/resume/add" method="post">
			<input type="hidden" name="resumeId" value="${requestScope.resume.rid }">
			<table border="2" cellpadding="10" cellspacing="0" id="resume" class="flag" >
				<tr>
					<td colspan="4" align="center">简历</td>
				</tr>
				<tr>
					<td>真实姓名</td>
					<td><input type="text" name="realName" value="${requestScope.resume.realName }" placeholder="请输入您的真实姓名" required="required"></td>
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
						<input type="radio" value="男" name="sex" checked="checked">男
						<input type="radio" value="女" name="sex">女
					</c:if>
					</td>			
				</tr>
				<tr>
					<td>年龄</td>
					<td><input type="number" name="age" value="${requestScope.resume.age }" required="required"></td>
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
					<td><input type="tel" name="phoneNumber"  value="${requestScope.resume.phoneNumber }" required="required"></td>
					<td>e-mail</td>
					<td><input type="email" name="email" value="${requestScope.resume.email }" required="required"></td>
				</tr>
				<tr>
					<td>应聘职位</td>
					<td>
						<select name="deptId" required="required">
							<option>部门</option>
							<c:forEach items="${sessionScope.departments }" var="department">
								<option value="${department.did }" <c:if test="${department.did==requestScope.resume.position.department.did }">selected</c:if>>${department.deptName }</option>
							</c:forEach>
						</select>
						<select name="pid" required="required">
							<c:forEach items="${requestScope.resume.position.department.positions }" var="position">
								<option value="${position.pid }" <c:if test="${requestScope.resume.position.pid==position.pid }">selected</c:if> >${position.name }</option>
							</c:forEach>
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
					<td><input type="text" name="previousJob" value="${requestScope.resume.previousJob }" required="required"></td>
					<td>工作经验</td>
					<td><input type="text" name="workExperience" value="${requestScope.resume.workExperience }" required="required"></td>
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
					<td><input type="text" name="hobbys" value="${requestScope.resume.hobbys }" required="required"></td>
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
				原&nbsp;密&nbsp;码:<input type="password" name="oldPassword">
				<span class="span2" style="color:rgb(80,80,80)"></span><br/><br/>
				新&nbsp;密&nbsp;码:<input type="password" name="newPassword"><br/><br/>
				确认密码:<input type="password" name="checkPassword">
				<span class="span1" style="color:rgb(80,80,80)"></span><br/><br/>
				<input type="submit" value="修改" name="update">
			</form>
		</div>
		<div align="center" class="flag" id="queryRecruits">
			<c:forEach items="${requestScope.recruits }" var="recruit">
				<form action="#" method="post">
					
					<table border="2" cellpadding="10" cellspacing="0">
						<tr>
							<td>公司名称</td>
							<td>${recruit.companyName }</td>
						</tr>
						<tr>
							<td>招聘职位</td>
							<td>${recruit.position.department.deptName }&nbsp;&nbsp;${recruit.position.name }</td>
						</tr>
						<tr>
							<td>职位描述</td>
							<td>${recruit.jobInformation }</td>
						</tr>
						<tr>
							<td>岗位要求</td>
							<td>${recruit.requirements }</td>
						</tr>
						<tr>
							<td>薪资范围</td>
							<td>${recruit.salary }</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="hidden" name="recruitsId" value="${recruit.recruitsId }">
								<input type="submit" value="申请职位" name="apply">
							</td>
						</tr>
					</table>
				</form>
			</c:forEach>
		</div>
	</div>
</body>
</html>