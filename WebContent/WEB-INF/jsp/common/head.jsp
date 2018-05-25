<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<a href="${pageContext.request.contextPath}/index.jsp">
	<img src="${pageContext.request.contextPath}/resources/imgs/logo.png" class="img-responsive" alt="锐动网">
	<div class="">
	<a href="${pageContext.request.contextPath}/news/list/1" target="_self">查看公告</a>
	<c:if test="${!empty admin}">
	<a href="${pageContext.request.contextPath}/user/userlist/1">用户管理</a>
	</c:if>
	</div>
</a>
<div class="search" style="float: right;padding-right: 35px;">
	<form action="${pageContext.request.contextPath}/article/search" method="post" class="navbar-form navbar-left" role="search" style="float: right;">
	    <input type="text"  name="key" class="form-control" placeholder="搜索帖子">
	  	<button type="submit" class="btn btn-default">
	  		<span class="glyphicon glyphicon-search"></span> 搜索帖子
	  	</button>
	</form>
	<br>
	<c:if test="${!empty admin}">
	  		<form action="${pageContext.request.contextPath}/user/search" method="post" class="navbar-form navbar-left" role="search" style="float: right;">
	    <input type="text"  name="key" class="form-control" placeholder="输入用户名搜索用户">
	  	<button type="submit" class="btn btn-default" >
	  		<span class="glyphicon glyphicon-search"></span> 搜索用户
	  	</button>
	</form>
	</c:if>
</div>
<script type="text/javascript">
	function lookNews(){
		$.post("${pageContext.request.contextPath}/news/list/1");
	}
</script>
