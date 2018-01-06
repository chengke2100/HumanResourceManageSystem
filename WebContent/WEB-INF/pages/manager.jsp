<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/manager.css">
<title>管理员界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".flag").hide();
		if(${!empty requestScope.success}){
			alert("发布成功");
		}
		if(${!empty requestScope.suc}){
			alert("修改成功");
		}
		if(${!empty requestScope.recruits}){
			$("#showJobs").show();
		}
		$("select[name='deptId']").change(function(){
			var did = $(this).val();
			var $sel = $(this);
			$.ajax({
				url:"${pageContext.request.contextPath }/user/positions",
				type:"post",
				data:{did:did},
				dataType:"json",
				success:function(data){
					$("select[name=pid]").empty();
//					$("select[name=positon]").append("<option>职位</option>");
					$.each(data,function(idx,item){
						$sel.next().append("<option value="+item.pid+">"+item.name+"</option>");
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
		
	})
	
	function postJob(){
		$(".flag").hide();
		$("#recruit").show();
	}

	function showJobs(){
		$(".flag").hide();
		$("#showJobs").show();
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
			<li><a href="#" onclick="postJob()">发布招聘信息</a></li>
			<li><a href="${pageContext.request.contextPath }/recruit/showAll" onclick="showJobs()">管理招聘信息</a></li>
			<li><a href="#" onclick="">应聘管理</a></li>
			<li><a href="#">部门职位</a></li>
			<li><a href="#">培训管理</a></li>
			<li><a href="#">员工管理</a></li>
			<li><a href="#">奖罚管理</a></li>
			<li><a href="#">薪资管理</a></li>
			<li><a href="#">工资异议</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginPage" onclick="quit()">退出</a></li>
		</ul>
	</div>
	<div id="right" >
		<form action="${pageContext.request.contextPath }/recruit/add" method="post">
			<table border="2" cellpadding="10" cellspacing="0" id="recruit" class="flag" >
				<tr>
					<td>公司名称</td>
					<td><input type="text" name="companyName" required="required"></td>
				</tr>
				<tr>
					<td>职位</td>
					<td>
						<select name="deptId">
							<option>部门</option>
							<c:forEach items="${sessionScope.departments }" var="department">
								<option value="${department.did }">${department.deptName }</option>
							</c:forEach>
						</select>
						<select name="pid">
							<option>职位</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>职位描述</td>
					<td>
						<textarea rows="5" cols="30" name="jobInformation" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td>岗位要求</td>
					<td>
						<textarea rows="5" cols="30" name="requirements" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td>薪资范围</td>
					<td>
						<select name="salary">
							<option value="2000-5000" >2000-5000</option>
							<option value="5000-8000">5000-8000</option>
							<option value="8000-10000">8000-10000</option>
							<option value="10000以上" >10000以上</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="发布" >
					</td>
				</tr>
			</table>
		</form>
		<div align="center" class="flag" id="showJobs">
			<c:forEach items="${requestScope.recruits }" var="recruit">
				<form action="${pageContext.request.contextPath }/recruit/update" method="post">
					<input type="hidden" value="${recruit.recruitsId }" name="recruitsId">
					<table border="2" cellpadding="10" cellspacing="0" >
						<tr>
							<td>公司名称</td>
							<td><input type="text" name="companyName" required="required" value="${recruit.companyName }"></td>
						</tr>
						<tr>
							<td>职位</td>
							<td>
								<select name="deptId">
									<option>部门</option>
									<c:forEach items="${sessionScope.departments }" var="department">
										<option value="${department.did }"<c:if test="${department.did==recruit.position.department.did }">selected</c:if>>${department.deptName }</option>
									</c:forEach>
								</select>
								<select name="pid">
									<option>职位</option>
									<c:forEach items="${recruit.position.department.positions }" var="position">
										<option value="${position.pid }" <c:if test="${position.pid==recruit.position.pid }">selected</c:if>>${position.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>职位描述</td>
							<td>
								<textarea rows="5" cols="30" name="jobInformation" required="required" >${recruit.jobInformation }</textarea>
							</td>
						</tr>
						<tr>
							<td>岗位要求</td>
							<td>
								<textarea rows="5" cols="30" name="requirements" required="required" >${recruit.requirements }</textarea>
							</td>
						</tr>
						<tr>
							<td>薪资范围</td>
							<td>
								<select name="salary">
									<option value="2000-5000" <c:if test="${recruit.salary eq '2000-5000'}">selected</c:if> >2000-5000</option>
									<option value="5000-8000" <c:if test="${recruit.salary eq '5000-8000'}">selected</c:if> >5000-8000</option>
									<option value="8000-10000" <c:if test="${recruit.salary eq '8000-10000'}">selected</c:if> >8000-10000</option>
									<option value="10000以上" <c:if test="${recruit.salary eq '10000以上'}">selected</c:if> >10000以上</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="保存" >
							</td>
						</tr>
					</table><br/>
				</form>
			</c:forEach>
		</div>
	</div>
</body>
</html>