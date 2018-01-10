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
		if(${!empty requestScope.resume}){
			$("#resume").show();
		}
		if(${!empty requestScope.applys}){
			$("#apply").show();
		}
		if(${!empty requestScope.suc}){
			alert("修改成功");
		}
		if(${!empty requestScope.addInterview}){
			alert("邀请成功");
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
					$sel.next().empty();
//					$("select[name=positon]").append("<option>职位</option>");
					$.each(data,function(idx,item){
						$sel.next().append("<option value="+item.pid+">"+item.name+"</option>");
					})
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
	
	function showInterview(){
		$("#interviewTime").show();
	}
	function goback(){
		$("#resume").hide();
		window.location.href="${pageContext.request.contextPath }/recruit/showApplys";
		$("#apply").show();
		
	}
	function showDepartment(){
		$(".flag").hide();
		$("#department").show();
	}
	function updateDepartment(did){
		var deptName = prompt("请输入您要修改的部门名");
		if(deptName==null){
			return;
		}
		var $td = $("#"+did);
		$.ajax({
			url:"${pageContext.request.contextPath}/department/updateDepartment",
			type:"post",
			data:{did:did,deptName:deptName},
			dataType:"json",
			success:function(data){
				if(data==null){
					alert("名字为"+deptName+"的部门已经存在,请查证后在修改");
				}else{
					alert("修改成功");
					$td.empty();
					$td.append("<a href='#' onclick='showPositions("+data.did+")'>"+data.deptName+"</a>")
				}
			},
			error:function(x,msg,obj){
				alert(msg);
			}
		})
	}
	

	
	function showPositions(did){
		$.ajax({
			url:"${pageContext.request.contextPath}/user/positions",
			data:{did:did},
			type:"post",
			dataType:"json",
			success:function(data){
				$(".position").empty();
				$(".position").append("<h2>职位</h2>");
				$(".position").append("<table class='positions'></table>");
				if(data!=null){							
					$.each(data,function(idx,item){
						$(".positions").append("<tr>"+
						"<td class="+item.pid+"><a>"+item.name+"</a></td>"+
						"<td><a href='javascript:updatePosition("+item.pid+")'>修改</a></td>"+
						"<td><a href='javascript:deletePosition("+item.pid+")'>删除</a></td>"+
						"</tr>");
					})
					$(".position").append("<a href='javascript:addPosition("+did+")'>添加职位</a>");
				}else{
					$(".position").append("<a href='javascript:addPosition("+did+")'>添加职位</a>");
				}		
			}
		})
	}
	
	function addPosition(did){
		var pName =  prompt("请输入您要添加的职位名");
		if(pName==null){
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/department/addPosition",
			type:"post",
			data:{pName:pName,did:did},
			dataType:"json",
			success:function(data){
				if(data==null){
					alert("名字为"+pName+"的职位已经存在,添加失败")
				}else{
					alert("添加成功");
					$(".positions").append("<tr>"+
							"<td class="+data.pid+"><a>"+data.name+"</a></td>"+
							"<td><a href='javascript:updatePosition("+data.pid+")'>修改</a></td>"+
							"<td><a href='javascript:deletePosition("+data.pid+")'>删除</a></td>"+
							"</tr>");
				}
			},
			error:function(x,msg,obj){
				alert(msg);
			}
		})
	}
	
	function updatePosition(pid){
		var pName = prompt("请输入您要修改的职位名");
		if(pName==null){
			return;
		}
		var $td = $("."+pid);
		$.ajax({
			url:"${pageContext.request.contextPath}/department/updatePosition",
			type:"post",
			data:{pid:pid,pName:pName},
			dataType:"json",
			success:function(data){
				if(data==null){
					alert("名字为"+pName+"的职位已经存在,请查证后在修改");
				}else{
					alert("修改成功");
					$td.empty();
					$td.append("<a>"+data.name+"</a>")
				}
			},
			error:function(x,msg,obj){
				alert(msg);
			}
		})
	}
	
	function deletePosition(pid){
		var pName = $("."+pid).text();
		if(!confirm("是否确定删除职位名称为"+pName+"的职位")){
			return;
		}
		var $td = $("#"+pid).parent();
		$.ajax({
			url:"${pageContext.request.contextPath}/department/deletePosition",
			type:"post",
			data:{pid:pid},
			dataType:"text",
			success:function(data){
				if(data=="0"){
					alert("该职位下面有在职员工，删除失败");
				}else{
					alert("删除成功");
					$td.empty();
				}
			},
			error:function(x,msg,obj){
				alert(msg);
			}
		})
	}
	
	function deleteDepartment(did){
		var deptName = $("#"+did).text();
		if(!confirm("是否确定删除部门名为"+deptName+"的部门")){
			return;
		}
		var $td = $("#"+did).parent();
		$.ajax({
			url:"${pageContext.request.contextPath}/department/deleteDepartment",
			type:"post",
			data:{did:did},
			dataType:"text",
			success:function(data){
				if(data=="0"){
					alert("该部门下面有在职员工，删除失败");
				}else{
					alert("删除成功");
					$td.empty();
				}
			},
			error:function(x,msg,obj){
				alert(msg);
			}
		})
	}
	
	function addDepartment(){
		var deptName = prompt("请输入您要添加的部门名");
		if(deptName==null){
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/department/addDepartment",
			type:"post",
			data:{deptName:deptName},
			dataType:"json",
			success:function(data){
				if(data==null){
					alert("名字为"+deptName+"的部门已经存在,添加失败")
				}else{
					alert("添加成功");
					$("#departments").append("<tr>"+
							"<td id="+data.did+"><a href='#' onclick='showPositions("+data.did+")'>"+deptName+"</a></td>"+
							"<td><a href='javascript:updateDepartment("+data.did+")'>修改</a></td>"+
							"<td><a href='javascript:deleteDepartment("+data.did+")'>删除</a></td>"+
							"</tr>");
				}
			},
			error:function(x,msg,obj){
				alert(msg);
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
			<li><a href="#">反馈</a></li>
			<li><a href="#" onclick="postJob()">发布招聘信息</a></li>
			<li><a href="${pageContext.request.contextPath }/recruit/showAll" onclick="showJobs()">管理招聘信息</a></li>
			<li><a href="${pageContext.request.contextPath }/recruit/showApplys">应聘管理</a></li>
			<li><a href="#" onclick="showDepartment()">部门职位</a></li>
			<li><a href="#">培训管理</a></li>
			<li><a href="#">员工管理</a></li>
			<li><a href="#">奖罚管理</a></li>
			<li><a href="#">薪资管理</a></li>
			<li><a href="#">工资异议</a></li>
			<li><a href="${pageContext.request.contextPath }/user/loginPage" onclick="return quit()">退出</a></li>
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
									<c:forEach items="${sessionScope.departments }" var="department">
										<option value="${department.did }"<c:if test="${department.did==recruit.position.department.did }">selected</c:if>>${department.deptName }</option>
									</c:forEach>
								</select>
								<select name="pid">
									<c:forEach items="${recruit.position.department.positions}" var="position">
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
		<div class="flag" id="apply" align="center">
			<table border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td>序号</td>
					<td>应聘者ID</td>
					<td>投递时间</td>
					<td>查看状态</td>
					<td>面试状态</td>
					<td colspan="2" align="center">操作</td>
				</tr>
				<c:forEach items="${requestScope.applys }" var="apply">
					<tr>
						<td>${apply.applyId }</td>
						<td>${apply.userId }</td>
						<td>${apply.deliverTime }</td>
						<td>
						<c:choose>
							<c:when test="${apply.isRead }">已查看</c:when>
							<c:otherwise>未查看</c:otherwise>
						</c:choose>
						</td>
						<td>${apply.status }</td>
						<td><a href="${pageContext.request.contextPath }/resume/showResume/${apply.applyId }">查看</a></td>
						<td><a href="#">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="flag" id="resume">
			<h2>简历详情</h2>
			<form action="${pageContext.request.contextPath }/interview/addInterview" method="post">
				<table border="2" cellpadding="10" cellspacing="0">
					<tr>
						<td>姓名</td>
						<td>${resume.realName }</td>
						<td>年龄</td>
						<td>${resume.age }</td>
					</tr>
					<tr>
						<td>性别</td>
						<td>${resume.sex }</td>
						<td>学历</td>
						<td>${resume.education }</td>
					</tr>
					<tr>
						<td>手机</td>
						<td>${resume.phoneNumber }</td>
						<td>邮箱</td>
						<td>${resume.email }</td>
					</tr>
					<tr>
						<td>应聘职位</td>
						<td>${resume.position.department.deptName }&nbsp;&nbsp;${resume.position.name }</td>
						<td>期望薪资</td>
						<td>${resume.expectedSalary }</td>
					</tr>
					<tr>
						<td>工作经验</td>
						<td>${resume.workExperience }</td>
						<td>上份工作</td>
						<td>${resume.previousJob }</td>
					</tr>
					<tr>
						<td>政治面貌</td>
						<td>${resume.politicalStatus }</td>
						<td>兴趣爱好</td>
						<td>${resume.hobbys }</td>
					</tr>
				</table>
				<input type="hidden" value="${applyId }" name="applyId">
				&nbsp;&nbsp;<input type="button" value="邀请面试" onclick="showInterview()">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="goback()">
				<div class="flag" id="interviewTime">
					<input type="date" name="interviewTime"><br/><br/>
					<input type="submit" value="确认">
				</div>
			</form>		
		</div>
		<div class="flag" id="department" align="center">
			<div style="float:left" class="dept">
				<h2>部门</h2>
				<table id="departments">
					<c:forEach items="${sessionScope.departments }" var="department">
						<tr>
							<td id="${department.did}"><a href="#" onclick="showPositions(${department.did})">${department.deptName }</a></td>
							<td><a href="javascript:updateDepartment(${department.did})">修改</a></td>
							<td><a href="javascript:deleteDepartment(${department.did})">删除</a></td>
						</tr>
					</c:forEach>
				</table>
				<a href="javascript:addDepartment()">添加部门</a>
			</div>
			<div style="float:left" class="position">
				
			</div>
		</div>
	</div>
</body>
</html>