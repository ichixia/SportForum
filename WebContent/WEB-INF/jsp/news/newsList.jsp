<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- 公告列表页面 --%>
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
				<c:forEach var="t" items="${topNews }">
						<div class="page-header">
							<h4>
								<span class="label label-primary">置顶</span>
								<c:if test="${t.status eq 3}">
									<span class="label label-danger">精</span>
								</c:if>
						<%@include file="newsData.jsp"%>
						</div>
				</c:forEach>
				<%-- 未置顶的帖子 --%>
				<c:forEach var="t" items="${newsPageBean.list }">
					<c:if test="${t.status ne 1 and t.status ne 3}">
						<div class="page-header">
							<h4>
								<c:if test="${t.status eq 2}">
									<span class="label label-danger">精</span>
								</c:if>
								<%@include file="newsData.jsp"%>
						</div>
					</c:if>
				</c:forEach>
				<%-- 分页  --%>
				<div style="float: right;">
					<a href="${pageContext.request.contextPath}/news/list/${newsPageBean.currentPage==1?1:newsPageBean.currentPage-1 }">
						上一页
					</a>
						第${newsPageBean.currentPage }页/共${newsPageBean.totalPage }页 
					<a href="${pageContext.request.contextPath}/news/list/${newsPageBean.currentPage==newsPageBean.totalPage?newsPageBean.totalPage:newsPageBean.currentPage+1 }">
						下一页
					</a>
				</div>
				<%-- 发公告框 --%>
				<c:choose>
					<c:when test="${!empty admin }">
						<div style="padding-top: 200px;">
							<input type="text" class="form-control" maxlength="50" id="title" placeholder="输入公告标题"><br />
							<div id="content" style="height:240px;"></div><br>
							<button type="button" class="btn btn-primary" id="addbtn"
								onclick="doAdminClick()" style="float: right;">发布</button>
								<input type="text" class="form-control" maxlength="20" id="lable" placeholder="#输入公告标签#" style="width: 200px;float: right;"/>
						</div>
					</c:when>
					<c:otherwise>
						<center>
							<h3>管理員登陆后才可新增公告！</h3>
						</center>
					</c:otherwise>
				</c:choose>
				<%@include file="../common/foot.jsp"%>
			</div>
			<div class="col-md-4">
				<%-- 管理員头像昵称 --%>
				<div style="padding-top: 120px;">
					<center>
						<c:choose>
							<c:when test="${!empty admin }">	
								<a href="${pageContext.request.contextPath}/admin/manager/${admin.aid }" target="_blank">
									<img alt="${admin.admin_name }" src="<c:url value="${admin.headimg }"/>" class="img-circle" style="width: 224px;height: 224px;">
								</a>
								<br><br>管理員名：
								${admin.admin_name }
								<br><a href="<c:url value="/admin/exit"/>">安全退出</a>
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:choose>
					</center>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/myJs.js"/>"></script>
<script type="text/javascript">
/*发帖*/
function doAdminClick() {
	var markupStr = editor.$txt.html();
	var url = "${pageContext.request.contextPath}/news/add";
	var params = {
		title : $('#title').val(),
		content : markupStr,
		aid : '${admin.aid}',
		lable : $('#lable').val()
	};
	$.ajax({
		'url' : url,
		'data' : params,
		'contentType' : "application/x-www-form-urlencoded; charset=utf-8",
		'type' : 'POST',
		'success' : function(data) {
			alert(data.data);
			location.href = "${pageContext.request.contextPath}/news/list/1";
		},
		'error' : function() {
			alert("發佈失败！");
		}
	});
}


</script>
<input id="context" type="hidden" value="${pageContext.request.contextPath}">
<%@include file="../common/editor.jsp"%>
</body>
</html>
