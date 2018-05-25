<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>帖子内容</title>
<%@include file="../common/import.jsp"%>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="../common/head.jsp"%>
		<div class="page-header">
			<h4><c:out value="${news.title }"></c:out></h4>
		</div>
		<div class="row">
			<div class="panel panel-default" style="border-radius : 0;">
				<div class="panel-body">
					<div class="col-md-2">
						<center>
							<a href="${pageContext.request.contextPath}/admin/info/${news.admin.aid}" target="_blank">
							<img alt="headimg" style="width: 111px;" src="<c:url value="${news.admin.headimg }"/>" class="img-thumbnail"></a>
							<br><br>管理员 :<a href="${pageContext.request.contextPath}/admin/info/${news.admin.aid }" target="_blank">${news.admin.admin_name }</a>
						</center>
					</div>
					<div class="col-md-8">
						<c:out value="${news.content }" escapeXml="false"></c:out>
					</div>
				</div>
				<h6 style="float: right;">
					发布时间:
					<fmt:formatDate value="${news.date }"
						pattern="MM/dd HH:mm:ss" />&nbsp;
				</h6>
			</div>
		</div>

		<%-- 修改按钮 --%>
		<c:choose>
			<c:when test="${!empty admin }">
				<div style="padding-top: 300px;">
					<div id="content" style="height:240px;"></div>
					<br>
					<button type="button" class="btn btn-primary" id="addbtn"
						onclick="change()" style="float: right;">修改</button>
				</div>
			</c:when>
		</c:choose>
	<%@include file="../common/foot.jsp"%>
	<%@include file="../common/editor.jsp"%>
	</div>
<script type="text/javascript">
	
</script>
</body>
</html>
