<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- 管理员页面 用户列表页面 --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>主页</title>
<%@include file="../../common/import.jsp"%>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="../../common/head.jsp"%>
		<div class="row">
			<div class="col-md-8">
				<c:forEach var="t" items="${userPageBean.list }">
						<div class="page-header">
							<h4>
								<c:if test="${t.status eq 2}">
									<span class="label label-danger">精</span>
								</c:if>
								<%@include file="userData.jsp"%>
							</h4>
						</div>
				</c:forEach>
				<%-- 分页  --%>
				<div style="float: right;">
					<a href="${pageContext.request.contextPath}/user/userlist/${userPageBean.currentPage==1?1:userPageBean.currentPage-1 }">
						上一页
					</a>
						第${userPageBean.currentPage }页/共${userPageBean.totalPage }页 
					<a href="${pageContext.request.contextPath}/user/userlist/${userPageBean.currentPage==userPageBean.totalPage?userPageBean.totalPage:userPageBean.currentPage+1 }">
						下一页
					</a>
				</div>
				<%-- 发帖框 --%>
				
				<%@include file="../../common/foot.jsp"%>
			</div>
			<div class="col-md-4">
				
				
				
			</div>
		</div>
	</div>
<script type="text/javascript"
	src="<c:url value="/resources/js/myJs.js"/>"></script>
<script type="text/javascript">

/*更改用户状态 */

function updateStatus(uid){
	$.get("${pageContext.request.contextPath}/user/updateUserStatus/"+uid,function(data){
		alert(data.data);
	location.href = "${pageContext.request.contextPath}/user/userlist/1";},"json");
}



</script>
<input id="context" type="hidden" value="${pageContext.request.contextPath}">
<%@include file="../../common/editor.jsp"%>
</body>
</html>
