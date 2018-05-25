<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 用户列表的用户名 -->
<a href="${pageContext.request.contextPath}/user/manager/${t.uid }" target="_blank">
	<c:out value="${t.username }"></c:out>
</a>
	<br />
</h4>
<span class="label label-success">#${t.id_num }#</span>

<p style="float: right;">
<c:if test="${t.status eq 1}">
	<span class="label label-success">正常用户</span>
	<button type="button" class="btn btn-danger" onclick="updateStatus(${t.uid })">禁言</button>
</c:if>
<c:if test="${t.status eq 0}">
	<span class="label label-danger">禁言用户</span>
	<button type="button" class="btn btn-danger" onclick="updateStatus(${t.uid })">恢复正常</button>
</c:if>



</p>
<br>