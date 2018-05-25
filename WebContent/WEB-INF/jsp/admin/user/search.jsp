<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>搜索结果</title>
<%@include file="../../common/import.jsp"%>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="../../common/head.jsp"%><br>
		<h4>查询关键字:"${key }",共查到${fn:length(resultList) }个用户！</h3>
		<table class="table table-hover">
			<c:forEach var="t" items="${resultList }">
			  <tr>
			  	<td>
				  	<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
				  	<%@include file="userData.jsp"%>
				</td>
			  </tr>
			</c:forEach>
		</table>
		<%@include file="../../common/foot.jsp"%>
	</div>
</body>
<script type="text/javascript">
function updateStatus(uid){
	$.get("${pageContext.request.contextPath}/user/updateUserStatus/"+uid,function(data){
		alert(data.data);
	location.href = "${pageContext.request.contextPath}/user/userlist/1";},"json");
}
</script>
</html>
