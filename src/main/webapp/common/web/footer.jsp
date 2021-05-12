<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<spring:eval expression="@property.getProperty('user.login')" var="loginUrl"/>
<spring:eval expression="@property.getProperty('product.discount')" var="discountPro"/>
<spring:eval expression="@property.getProperty('cart.url')" var="cartUrl"/>
<spring:eval expression="@property.getProperty('product.web.find')" var="findProduct"/>
<spring:eval expression="@property.getProperty('home')" var="home"/>
<spring:eval expression="@property.getProperty('user.register')" var="userRegis"/>
<spring:eval expression="@property.getProperty('category.products')" var="url"/>
<spring:eval expression="@property.getProperty('page.page.product')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem.product')" var="maxPageItemProp"/>
<spring:eval expression="@property.getProperty('cart.url')" var="cartUrl"/>
<c:url var="productofcate" value="${url}"/>
<c:url var="find_product" value="${findProduct}"></c:url>
<c:url var="homeURl" value="${home}"/>
<c:url value="${discountPro}" var="discount_url"></c:url>
<c:url value="${userRegis}" var="user_regis" ></c:url>
<!DOCTYPE html>
<div class="subscribe">
	 <div class="container">
		 <form action="${find_product}" method="POST">
			 <input type="text" id="name" name="name" class="text">
			 <input type="submit" value="Tìm Kiếm">
		 </form>
	 </div>
</div>
<div class="footer">
	 <div class="container">
		 <div class="footer-grids">
			 <div class="col-md-3 about-us">
				 <h3>Về chúng tôi</h3>
				 <p>Chuyên cung cấp các sản phẩm nội thất với chất lượng cao , gía cả hợp lý, phù hợp với người tiêu dùng</p>
			 </div>
			 <div class="col-md-3 ftr-grid">
					<h3>Thông tin</h3>
					<ul class="nav-bottom">
						<li><a href="${homeURl}">Trang chủ</a></li>
						<li><a href="${discount_url}?page=${pageProp}&&maxPageItem=${maxPageItemProp}">Khuyến Mãi</a></li>
						<li><a href="<c:url value="${cartUrl}"/>">Giỏ Hàng</a></li>	
					</ul>					
			 </div>
			 <div class="col-md-3 ftr-grid">
					<h3>Tài khoản</h3>
					<ul class="nav-bottom">
						<li><a href="login.html">Đăng nhập</a></li>
						<li><a href="#">Đăng ký</a></li>
					</ul>					
			 </div>
			 <div class="col-md-3 ftr-grid">
					<h3>Danh Mục</h3>
					<ul class="nav-bottom">
					<c:forEach items="${menuCate}" var="i">
						<li><a href="${productofcate}${i.id}?page=${pageProp}&&maxPageItem=${maxPageItemProp}">${i.name}</a></li>
					</c:forEach>
					</ul>					
			 </div>
			 <div class="clearfix"></div>
		 </div>
	 </div>
</div>

<div class="copywrite">
	 <div class="container">
		 <div class="copy">
		 </div>
		 <div class="social">							
				<a href="#"><i class="facebook"></i></a>
				<a href="#"><i class="twitter"></i></a>
				<a href="#"><i class="dribble"></i></a>	
				<a href="#"><i class="google"></i></a>	
				<a href="#"><i class="youtube"></i></a>	
		 </div>
		 <div class="clearfix"></div>
	 </div>
</div>