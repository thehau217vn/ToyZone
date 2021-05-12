<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<spring:eval expression="@property.getProperty('user.login')" var="userLogin"/>
<spring:eval expression="@property.getProperty('user.register')" var="userRegis"/>
<c:url value="${userLogin}" var="urlLogin" ></c:url>
<c:url value="${userRegis}" var="urlReg" ></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="login_sec">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="">Trang chủ</a></li>
				<li class="active">Đăng Nhập</li>
			</ol>
			<c:if test="${ not empty message }">
				<div class="alert alert-danger">
				<strong>${message}</strong>	
				</div>
			</c:if>
			<h2>Đăng Nhập</h2>
			<div class="col-md-6 log">
				<form:form action="${urlLogin}" method="POST" modelAttribute="user" >
					
					<h5>Tài khoản</h5>	
					
					<form:input path="account"/>
					
					<div style="color: red"><form:errors path="account"/></div>
					<h5>Mật khẩu</h5>
					<form:password path="password"/>
					<div style="color: red"><form:errors path="password"/></div>
					<input type="submit" value="Đăng Nhập" />
					<a class="acount-btn" href="${urlReg}">Đăng Ký</a>
				</form:form>
				
			</div>

			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>