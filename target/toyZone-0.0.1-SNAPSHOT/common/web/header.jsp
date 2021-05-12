<%@page import="com.toyZone.dto.SessionGioHang"%>
<%@page import="com.toyZone.dto.SessionUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>

<spring:eval expression="@property.getProperty('user.login')" var="loginUrl"/>
<spring:eval expression="@property.getProperty('product.discount')" var="discountPro"/>
<spring:eval expression="@property.getProperty('cart.url')" var="cartUrl"/>
<spring:eval expression="@property.getProperty('category.products')" var="url"/>
<spring:eval expression="@property.getProperty('page.page.product')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem.product')" var="maxPageItemProp"/>
<spring:eval expression="@property.getProperty('home')" var="home"/>
<spring:eval expression="@property.getProperty('user.register')" var="userRegis"/>
<c:url value="${userRegis}" var="user_regis" ></c:url>
<c:url var="productofcate" value="${url}"/>
<c:url var="homeURl" value="${home}"/>
<c:url value="${discountPro}" var="discount_url"></c:url>
<div class="header-top">
	 <div class="header-bottom">			
				<div class="logo">
					<h1><a href="${homeURl}">ShopToyHVN</a></h1>					
				</div>
			 <!---->		 
			 <div class="top-nav">
				<ul class="memenu skyblue">
				<li class="active"><a href="${homeURl}">TRANG CHỦ</a></li>
					
					<li class="grid"><a href="${discount_url}?page=${pageProp}&&maxPageItem=${maxPageItemProp}">Khuyến Mãi</a>
						
					</li>
					<li class="grid"><a href="#">DANH MỤC</a>
						<div class="mepanel">
							<div class="row">
								<div class="col1 me-one">
									<ul>
									<c:forEach  var="i" items="${menuCate}">
										<li><a href="${productofcate}${i.id}?page=${pageProp}&&maxPageItem=${maxPageItemProp}">${i.name}</a></li>
									</c:forEach>
										
									</ul>
								</div>
								
							</div>
						</div>
					</li>
					<li class="grid"><a href="<c:url value="${cartUrl}"/>">Giỏ Hàng</a></li>
								
				</ul>				
			 </div>
			
			 <!---->
			 <div class="cart box_1">
			   <%if(request.getSession().getAttribute("gioHang")!=null){%> 
			  	<a href="<c:url value="${cartUrl}"/>">
					<div id="carttotal" class="total">
						<span ><%= ((SessionGioHang) request.getSession().getAttribute("gioHang")).getTotalPrice() %></span> 
						(<span ><%=((SessionGioHang) request.getSession().getAttribute("gioHang")).getTotalItem() %></span>)
					</div>
					
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
				</a>
					<%if(((SessionGioHang) request.getSession().getAttribute("gioHang")).getTotalItem()<=0){%>
						<p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>
					<%}else{ %>
						<p><a href="" class="simpleCart_empty">View Cart</a></p>
					<%} %>
			  <%}else{ %>
			   <a href="<c:url value="${cartUrl}"/>">
					<div id="carttotal" class="total">
						<span >0</span> 
						(<span >0</span>)
					</div>
					
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
				</a>
				<p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>
			  <%} %>
				

			 	<div class="clearfix"> </div>
			
			 
			 <%if(session.getAttribute("sessionUser")!=null){%>
				 </div>
				  <h3 class="b3">
				  <span class = "label label-info">Welcome,<%=((SessionUser) request.getSession().getAttribute("sessionUser")).getFullName()%></span></a>
				  <a href="<c:url value='/logout'/>"><span class = "label label-danger">Thoát</span></a>
				  </h3>
				  <div class="clearfix"> </div>
			 <!---->			 
			 </div>
			 <%}else{%> 
				  </div>
				  <h3 class="b3">
				  <a href="<c:url value="${loginUrl}"/>"><span class = "label label-info">Đăng Nhập</span></a>
				  <a href="${user_regis}"><span class = "label label-info">Đăng ký</span></a>
				  </h3>
				  <div class="clearfix"> </div>
			 <!---->			 
			 </div>
			 <%}%>
			
				
			<div class="clearfix"> </div>
</div>

<div class="slider">
	  <div class="callbacks_container">
	     <ul class="rslides" id="slider">
	         <li>
				 <div class="banner1">				  
					  <div class="banner-info">
					 
					  </div>
				 </div>
	         </li>
	         <li>
				 <div class="banner2">
					 <div class="banner-info">
					 
					 </div>
				 </div>
			 </li>
	         <li>
	             <div class="banner3">
	        	 <div class="banner-info">
				 </div>
				 </div>
	         </li>
	      </ul>
	  </div>
  </div>