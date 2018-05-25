<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 帖子列表的标题 -->
<a href="${pageContext.request.contextPath}/admin/details/${t.aid }" target="_blank">
	<c:out value="${t.title }"></c:out>
</a>
<!-- 帖子列表的楼主 -->
<div style="float: right;">
	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
	<a href="${pageContext.request.contextPath}/user/info/${t.author.uid}" target="_blank">${t.author.username }</a>
	</div>
	<br />
</h4>
<c:if test="${!empty t.lable }">
	<span class="label label-success">#${t.lable }#</span>
</c:if>
<p style="float: right;">
<fmt:formatDate value="${t.date }" pattern="MM/dd HH:mm:ss" />
<button type="button" class="btn btn-danger" onclick="deleteArticle(${t.aid })">删除</button>
</p>
<br>