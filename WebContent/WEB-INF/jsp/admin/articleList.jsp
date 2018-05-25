<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- 管理员页面 帖子列表页面 --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>主页</title>
<%@include file="../common/import.jsp"%>
</head>
<body>
	
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="../common/head.jsp"%>
		<div class="row">
			<div class="col-md-8">
				<%-- 置顶的帖子 --%>
				<c:forEach var="t" items="${topArticle }">
						<div class="page-header">
							<h4>
								<span class="label label-primary">置顶</span>
								<c:if test="${t.status eq 3}">
									<span class="label label-danger">精</span>
								</c:if>
						<%@include file="../admin/common/articleData.jsp"%>
						</div>
				</c:forEach>
				<%-- 未置顶的帖子 --%>
				<c:forEach var="t" items="${articlePageBean.list }">
					<c:if test="${t.status ne 1 and t.status ne 3}">
						<div class="page-header">
							<h4>
								<c:if test="${t.status eq 2}">
									<span class="label label-danger">精</span>
								</c:if>
								<%@include file="../admin/common/articleData.jsp"%>
						</div>
					</c:if>
				</c:forEach>
				<%-- 分页  --%>
				<div style="float: right;">
					<a href="${pageContext.request.contextPath}/article/list/${articlePageBean.currentPage==1?1:articlePageBean.currentPage-1 }">
						上一页
					</a>
						第${articlePageBean.currentPage }页/共${articlePageBean.totalPage }页 
					<a href="${pageContext.request.contextPath}/article/list/${articlePageBean.currentPage==articlePageBean.totalPage?articlePageBean.totalPage:articlePageBean.currentPage+1 }">
						下一页
					</a>
				</div>
				<%-- 发帖框 --%>
				<c:choose>
					<c:when test="${!empty user }">
						<div style="padding-top: 200px;">
							<input type="text" class="form-control" maxlength="50" id="title" placeholder="输入帖子标题"><br />
							<div id="content" style="height:240px;"></div><br>
							<button type="button" class="btn btn-primary" id="addbtn"
								onclick="doClick()" style="float: right;">发布</button>
								<input type="text" class="form-control" maxlength="20" id="lable" placeholder="#输入帖子标签#" style="width: 200px;float: right;"/>
						</div>
					</c:when>
					<c:otherwise>
						<center>
							<h3>登陆后才可进行发帖！</h3>
						</center>
					</c:otherwise>
				</c:choose>
				<%@include file="../common/foot.jsp"%>
			</div>
			<div class="col-md-4">
				<%-- 用户头像昵称 --%>
				<div style="padding-top: 120px;">
					<center>
						<c:choose>
							<c:when test="${empty admin }">	
								<img src="<c:url value="/resources/imgs/head.png"/>" class="img-circle"  data-toggle="modal" data-target="#myModal">
								<br><br>用户名：
								<a href="javascript:void(0);" data-toggle="modal" data-target="#myModal">未登录</a>
								<a href="javascript:void(0);" data-toggle="modal" data-target="#adminModal">管理员登录</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/admin/manager/${admin.aid }" target="_blank">
									<img alt="${admin.admin_name }" src="<c:url value="${admin.headimg }"/>" class="img-circle" style="width: 224px;height: 224px;">
								</a>
								<br><br>管理员名：
								${admin.admin_name }
								<br><a href="<c:url value="/user/exit"/>">安全退出</a>
								<a href="${pageContext.request.contextPath}/user/userlist/1">用户管理</a>
							</c:otherwise>
						</c:choose>
					</center>
				</div>
				<!-- 弹出对话框 -->
				<%@include file="../common/login.jsp" %>
				<%@include file="../admin/login.jsp" %>
			</div>
		</div>
	</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/myJs.js"/>"></script>
<script type="text/javascript">
/*发帖*/
function doClick() {
	var markupStr = editor.$txt.html();
	var url = "${pageContext.request.contextPath}/article/add";
	var params = {
		title : $('#title').val(),
		content : markupStr,
		uid : '${user.uid}',
		lable : $('#lable').val()
	};
	$.ajax({
		'url' : url,
		'data' : params,
		'contentType' : "application/x-www-form-urlencoded; charset=utf-8",
		'type' : 'POST',
		'success' : function(data) {
			alert(data.data);
			location.href = "${pageContext.request.contextPath}/article/list/1";
		},
		'error' : function() {
			alert("发帖失败！");
		}
	});
}

/* 管理员注册*/
function admin_regist() {
	var url = "${pageContext.request.contextPath}/admin/regist";
	var params = {
		admin_name : $('#admin_name').val(),
		password : $('#password').val(),
		aid_num : $('#aid_num').val(),
		authorization_code : $('#authorization_code').val()
	};
	$.post(url, params, function(data) {
		alert(data.data);
	}, "json");
}

/* 管理员登录*/
function admin_login() {
	var url = "${pageContext.request.contextPath}/admin/login";
	var params = {
		admin_name : $('#admin_name').val(),
		password : $('#password').val()
	};
	$.post(url, params, function(data) {
		alert(data.data);
		location.href = "${pageContext.request.contextPath}/article/list/1";
	}, "json");
}

/*删除帖子*/
function deleteArticle(aid){
	$.get("${pageContext.request.contextPath}/admin/delete/"+aid,function(data){
		alert(data.data);
	},"json");
	location.href = "${pageContext.request.contextPath}/article/list/1"
}
</script>
<input id="context" type="hidden" value="${pageContext.request.contextPath}">
<%@include file="../common/editor.jsp"%>
</body>
</html>
