<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 公告列表的每一列 -->
<!-- 公告列表的标题 -->
<a href="${pageContext.request.contextPath}/news/details/${t.nid }" target="_blank">
	<c:out value="${t.title }"></c:out>
</a>
<!-- 公告列表的管理员 -->
<div style="float: right;">
	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
	<a href="${pageContext.request.contextPath}/admin/info/${t.admin.aid}" target="_blank">${t.admin.admin_name }</a>
	</div>
	<br />
</h4>
<c:if test="${!empty t.lable }">
	<span class="label label-success">#${t.lable }#</span>
</c:if>
<p style="float: right;">
<fmt:formatDate value="${t.date }" pattern="MM/dd HH:mm:ss" />
</p>
<br>