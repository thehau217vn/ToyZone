<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<spring:eval expression="@property.getProperty('user.register')" var="userRegis"/>
<c:url value="${user_regis}" var="userRegis" ></c:url>
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
				<li class="active">Đăng ký</li>
			</ol>
			<c:if test="${ not empty message }">
				<div class="alert alert-${alert}">
				<strong>${message}</strong>	
				</div>
			</c:if>
			<h2>Đăng Ký</h2>
			<div class="col-md-6 log">
				<form:form action="${user_regis}" method="POST" modelAttribute="userDk" >
					<h5>Họ tên</h5>	
					<form:input path="fullName"/>
					<div style="color: red"><form:errors path="fullName"/></div>
					
					
					<h5>Gmail</h5>	
					<form:input path="email"/>
					<div style="color: red"><form:errors path="email"/></div>
					
					
					<h5>Số điện thoại</h5>	
					<form:input path="phone"/>
					<div style="color: red"><form:errors path="phone"/></div>
					
					<h5>Tài khoản</h5>	
					<form:input path="account"/>
					<div style="color: red"><form:errors path="account"/></div>
					
					
					<h5>Mật khẩu</h5>
					<form:password path="password"/>
					<div style="color: red"><form:errors path="password"/></div>
					
					<input type="submit" value="Đăng ký" />
				</form:form>
			</div>

			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>