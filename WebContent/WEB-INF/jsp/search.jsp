<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<title>搜索结果</title>
<%@include file="common/import.jsp"%>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="common/head.jsp"%><br>
		<h4>查询关键字:"${key }",共查到${fn:length(resultList) }篇帖子！</h3>
		<table class="table table-hover">
			<c:forEach var="s" items="${resultList }">
			  <tr>
			  	<td>
				  	<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
				  	<a href="${pageContext.request.contextPath}/article/details/${s.aid }" target="_blank">
				  	<c:out value="${s.title }"></c:out>
				  	</a>
				  	<div style="float: right;">
				  		 / <fmt:formatDate value="${s.date }" pattern="yyyy/MM/dd HH:mm:ss" />
				  		 <c:choose>
				  		 	<c:when test="${!empty admin}">
				  		 		<button type="button" class="btn btn-danger" onclick="deleteArticle(${s.aid })">删除</button>
				  		 	</c:when>
				  		 </c:choose>
				  	</div>
				</td>
			  </tr>
			</c:forEach>
		</table>
		<%@include file="common/foot.jsp"%>
	</div>
</body>
<script type="text/javascript">
/*删除帖子*/
function deleteArticle(aid){
	$.get("${pageContext.request.contextPath}/admin/delete/"+aid,function(data){
		alert(data.data);
	location.href = "${pageContext.request.contextPath}/article/list/1";},"json");
}
</script>
</html>
