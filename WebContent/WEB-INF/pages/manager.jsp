<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		if(${requestScope.employees!=null}){
			$("#employee").show();
		}
		if(${requestScope.user!=null}){
			$("#transfer").show();
		}
		if(${requestScope.dotransfer!=null}){
			alert("调动成功");
		}
		if(${requestScope.clockingList!=null}){
			$("#clocking").show();
		}
		if(${!empty requestScope.employeeResume}){
			$("#employeeResume").show();
		}
		if(${!empty requestScope.give}){
			alert("奖赏成功");
		}
		if(${requestScope.rewardsList!=null}){
			$("#reward").show();
		}
		if(${requestScope.trains!=null}){
			$("#trains").show();
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
		
	
		$("select[name='isOnJob']").change(function(){
			var isOnJob = $(this).val();
			window.location.href="${pageContext.request.contextPath }/employee/showEmployees/"+isOnJob;
		})
		
		$("select[name='year']").change(function(){
			var realName = "${requestScope.realName}";
			var userId = "${requestScope.userId}";
			var year = $(this).val();
			var month = $("select[name='month']").val();
			window.location.href="${pageContext.request.contextPath}/employee/showClockingIn/"+year+"/"+month+"/"+realName+"/"+userId;
		})
		
		$("select[name='month']").change(function(){
			var realName = "${requestScope.realName}";
			var userId = "${requestScope.userId}";
			var month = $(this).val();
			var year = $("select[name='year']").val();
			window.location.href="${pageContext.request.contextPath}/employee/showClockingIn/"+year+"/"+month+"/"+realName+"/"+userId;
		})
		
		$("select[name='year1']").change(function(){
			var year = $(this).val();
			var month = $("select[name='month1']").val();
			window.location.href="${pageContext.request.contextPath}/employee/findRewards/"+year+"/"+month;
		})
		
		$("select[name='month1']").change(function(){
			var month = $(this).val();
			var year = $("select[name='year1']").val();
			window.location.href="${pageContext.request.contextPath}/employee/findRewards/"+year+"/"+month;
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
//					window.location.href="${pageContext.request.contextPath }/recruit/showApplys";

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
	
	function addTrain(){
		$("#updateTrain").hide();
		$("#addTrain").show();
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
					alert("该部门下面职位，删除失败");
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
	
	function showGive(){
		$("#give").show();
	}
	function after(){
		$("#give").hide();
	}
	function updateTrain(tid){
		$.ajax({
			url:"${pageContext.request.contextPath}/train/updateTrain",
			type:"post",
			data:{tid:tid},
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
			<li><a href="${pageContext.request.contextPath }/train/showAll">培训管理</a></li>
			<li><a href="${pageContext.request.contextPath }/employee/showEmployees/在职员工">员工管理</a></li>
			<li><a href="${pageContext.request.contextPath }/employee/findRewards/0/0">查看奖罚</a></li>
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
		<div class="flag" id="employee">
			<select name="isOnJob">
				<option <c:if test="${requestScope.isOnJob eq '在职员工'}">selected</c:if> >在职员工</option>
				<option <c:if test="${requestScope.isOnJob eq '离职员工'}">selected</c:if> >离职员工</option>
			</select>
			<table border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td>编号</td>
					<td>姓名</td>
					<td colspan="4" align="center">操作</td>
				</tr>
				<c:forEach items="${requestScope.employees }" var="employee">
					<tr>
						<td>${employee.userId }</td>
						<td><a href="${pageContext.request.contextPath }/employee/message/${employee.userId }">${employee.realName }</a></td>
						<td><a href="${pageContext.request.contextPath }/employee/transfer/${employee.userId }">人事调动</a></td>
						<td><a href="${pageContext.request.contextPath }/employee/clocking/${employee.userId }/${employee.realName }">考勤</a></td>
						<td><a href="#">工资发放</a></td>
						<td><a href="#">开除</a></td>
					</tr><br/>
				</c:forEach>
			</table>
		</div>
		<div class="flag" id="transfer">
			<form action="${pageContext.request.contextPath }/employee/dotransfer" method="post">
				<input type="hidden" value="${requestScope.user.uid }" name="uid">
				<h2>请选择调动的部门职位</h2>
				<select name="deptId">
					<c:forEach items="${sessionScope.departments }" var="department">
						<option value="${department.did }"<c:if test="${department.did==user.department.did }">selected</c:if> >${department.deptName }</option>
					</c:forEach>
				</select>
				<select name="positionId">
					<c:forEach items="${user.department.positions }" var="position">
						<option value="${position.pid }" <c:if test="${position.pid==user.position.pid }">selected</c:if> >${position.name }</option>
					</c:forEach>
				</select><br/><br/>
				<input type="submit" value="确认调动">
			</form>
		</div>
		<div class="flag" id="clocking">
			<form action="#" method="post" >
				<select name="year">
					<option <c:if test="${requestScope.year==2016 }">selected</c:if>>2016</option>
					<option <c:if test="${requestScope.year==2017 }">selected</c:if>>2017</option>
					<option <c:if test="${requestScope.year==2018 }">selected</c:if>>2018</option>
				</select>
				<select name="month">
					<option <c:if test="${requestScope.month==1 }">selected</c:if>>1</option>
					<option <c:if test="${requestScope.month==2 }">selected</c:if>>2</option>
					<option <c:if test="${requestScope.month==3 }">selected</c:if>>3</option>
					<option <c:if test="${requestScope.month==4 }">selected</c:if>>4</option>
					<option <c:if test="${requestScope.month==5 }">selected</c:if>>5</option>
					<option <c:if test="${requestScope.month==6 }">selected</c:if>>6</option>
					<option <c:if test="${requestScope.month==7 }">selected</c:if>>7</option>
					<option <c:if test="${requestScope.month==8 }">selected</c:if>>8</option>
					<option <c:if test="${requestScope.month==9 }">selected</c:if>>9</option>
					<option <c:if test="${requestScope.month==10 }">selected</c:if>>10</option>
					<option <c:if test="${requestScope.month==11 }">selected</c:if>>11</option>
					<option <c:if test="${requestScope.month==12 }">selected</c:if>>12</option>
				</select>
				该月目前缺勤天数:${requestScope.absenteeismDays }天
			</form>
			<table border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td>员工</td>
					<td>上班时间</td>
					<td>下班时间</td>
					<td>是否迟到</td>
					<td>是否早退</td>
				</tr>
				<c:choose>
					<c:when test="${!empty requestScope.clockingList }">
						<c:forEach items="${requestScope.clockingList }"  var="clockingIn">
							<tr>
								<td>${requestScope.realName }</td>
								<td><f:formatDate value="${clockingIn.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><f:formatDate value="${clockingIn.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${clockingIn.isLate }</td>
								<td>${clockingIn.isEarly }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" align="center">暂未考勤</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td colspan="5" align="center"><a href="${pageContext.request.contextPath }/employee/showEmployees/在职员工">返回</a></td>
				</tr>
			</table>
		</div>
		<div class="flag" id="employeeResume" align="center">
			<table border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td colspan="4" align="center">员工信息</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>${requestScope.employeeResume.realName }</td>
					<td>性别</td>
					<td>${requestScope.employeeResume.sex}</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td>${requestScope.employeeResume.age }</td>
					<td>学历</td>
					<td>${requestScope.employeeResume.education }</td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td>${requestScope.employeeResume.phoneNumber }</td>
					<td>邮箱</td>
					<td>${requestScope.employeeResume.email }</td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><f:formatDate value="${requestScope.entryDate }" pattern="yyy-MM-dd"/> </td>
					<td>职位</td>
					<td>
						${requestScope.employeeResume.position.department.deptName }&nbsp;&nbsp;&nbsp;
						${requestScope.employeeResume.position.name }
					</td>
				</tr>
			</table><br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="赏" onclick="showGive()">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="取消" onclick="after()">
			<div class="flag" id="give" align="center">
				<h2>赏</h2>
				<form action="${pageContext.request.contextPath }/employee/give " method="post">
					<input type="hidden" value="${requestScope.employeeResume.uid }" name="userId">
					<input type="hidden" value="${requestScope.employeeResume.realName }" name="realName">
					<table>
						<tr>
							<td>奖赏理由:</td>
							<td><input type="text" name="season"></td>
						</tr>
						<tr>
							<td>奖金:</td>
							<td>
								<select name="bonus">
									<option value="200">200</option>
									<option value="400">400</option>
									<option value="600">600</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>时间：</td>
							<td><input type="date" name="rewardsTime1"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="确定"></td>
						</tr>
					</table>		
				</form>
			</div>
		</div>
		<div class="flag" id="reward" align="center">
			<form action="#" method="post" >
				<select name="year2">
					<option <c:if test="${requestScope.year==2016 }">selected</c:if>>2016</option>
					<option <c:if test="${requestScope.year==2017 }">selected</c:if>>2017</option>
					<option <c:if test="${requestScope.year==2018 }">selected</c:if>>2018</option>
				</select>
				<select name="month2">
					<option <c:if test="${requestScope.month==1 }">selected</c:if>>1</option>
					<option <c:if test="${requestScope.month==2 }">selected</c:if>>2</option>
					<option <c:if test="${requestScope.month==3 }">selected</c:if>>3</option>
					<option <c:if test="${requestScope.month==4 }">selected</c:if>>4</option>
					<option <c:if test="${requestScope.month==5 }">selected</c:if>>5</option>
					<option <c:if test="${requestScope.month==6 }">selected</c:if>>6</option>
					<option <c:if test="${requestScope.month==7 }">selected</c:if>>7</option>
					<option <c:if test="${requestScope.month==8 }">selected</c:if>>8</option>
					<option <c:if test="${requestScope.month==9 }">selected</c:if>>9</option>
					<option <c:if test="${requestScope.month==10 }">selected</c:if>>10</option>
					<option <c:if test="${requestScope.month==11 }">selected</c:if>>11</option>
					<option <c:if test="${requestScope.month==12 }">selected</c:if>>12</option>
				</select>
			</form>
			<table border="2" cellpadding="10" cellspacing="0">
				<tr>
					<td>编号</td>
					<td>奖惩人员</td>
					<td>奖惩缘由</td>
					<td>奖惩时间</td>
					<td>奖金金额</td>
					<td>奖惩类型</td>
				</tr>
				<c:forEach items="${requestScope.rewardsList }" var="rewards">
					<tr>
						<td>${rewards.rewardsId }</td>
						<td>${rewards.realName }</td>
						<td>${rewards.season }</td>
						<td><f:formatDate value="${rewards.rewardsTime }" pattern="yyyy-MM-dd"/></td>
						<td>${rewards.bonus }</td>
						<td>${rewards.type }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="flag" id="trains" align="center" style="position: relative;">
			<div style="position: absolute;">
				<h2>培训</h2>
				<c:forEach items="${requestScope.trains }" var="train">
					<a href="javascript:updateTrain(${train.tid })">${train.trainName }</a><p/>
				</c:forEach>
				<a href="javascript:addTrain()">添加培训</a>
			</div>
			<div style="position: absolute;right:500px" class="flag" id="updateTrain" align="center">
				
			</div>
			<div style="position: absolute;right:500px" class="flag" id="addTrain" align="center">
				<h2>培训--新增</h2>
				<form action="${pageContext.request.contextPath }/train/addTrain" method="post">
					<table>
						<tr>
							<td>培训名称:</td>
							<td><input type="text" name="trainName"></td>
						</tr>
						<tr>
							<td>培训时间:</td>
							<td><input type="date" name="trainTime"></td>
						</tr>
						<tr>
							<td>培训部门:</td>
							<td>
								<select name="departmentId">
									<c:forEach items="${sessionScope.departments }" var="department">
										<option value="${department.did }">${department.deptName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="确认">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="取消" onclick="">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>	
	</div>
</body>
</html>