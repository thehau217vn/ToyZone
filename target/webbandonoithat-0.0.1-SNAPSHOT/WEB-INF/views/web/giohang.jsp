<%@page import="com.toyZone.dto.SessionGioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
 <spring:eval expression="@property.getProperty('cart.api')" var="addCart"/>
<spring:eval expression="@property.getProperty('cart.url')" var="cartUrl"/>
<c:url var="api_cart" value="${addCart}"/>
<c:url value="${cartUrl}" var="cart_url"></c:url>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ Hàng</title>
</head>
<body>
<div class="container">
	<c:if test="${not empty message}">
		<div class="alert alert-danger">
			<h1><strong>${message}</strong></h1>
		</div>
	</c:if>
	
	<div class="check-sec">	 
		<div class="col-md-3 cart-total">
			<a class="continue" href="${pageContext.request.contextPath}/home">Tiếp Tục Mua Hàng</a>
			<div class="price-details">
				<h3>Đơn Hàng Của Bạn</h3>
				
				<div class="clearfix"></div>				 
			</div>	
			<ul class="total_price">
			   <li class="last_price"> <h4>Tổng tiền</h4></li>	
			   <li class="last_price"><span><%=((SessionGioHang) request.getSession().getAttribute("gioHang")).getTotalPrice() %></span></li>			   
			</ul> 
			<div class="clearfix"></div>
			<div class="clearfix"></div>
			<div class="price-details"></div>
			<div class="price-details">
			
				<h3  style=" margin-top: 10px; margin-bottom: 10px;">Điền Thông Tin</h3>
				<c:url value="/order" var="ord"/>
				<form:form action="${ord}" method="POST" modelAttribute="orderdto">
					<form:input path="receiver" placeholder="Họ tên người nhận"/>
					
					<form:errors path="receiver" style="color:red;"></form:errors>
		
					
					<div style=" margin-top: 10px; margin-bottom: 10px;">
						<form:input path="phone" placeholder="Số điện thoại người nhận"/>
					</div>
					
					<form:errors path="phone" style="color:red;"></form:errors>
					
					<div style=" margin-top: 10px; margin-bottom: 10px;">
						<form:input path="deliveryAddress" placeholder="Địa chỉ giao hàng"/>
					</div>
					
					<form:errors path="deliveryAddress" style="color:red;"></form:errors>
					
					<div style=" margin-top: 10px; margin-bottom: 10px;">
					<form:input path="content" placeholder="Nỗi dung (nếu có)"/>
					</div>
					
					<h2 class="b2"><button class="label label-info">Đặt Hàng</button></h2>
				</form:form>
					
				<div class="clearfix"></div>				 
			</div>
			
		</div>
		<div class="col-md-9 cart-items">
			<h1>Giỏ Hàng Của Bạn</h1>
			
			<c:forEach var="i" items="${productsCart}">
				
				<script>$(document).ready(function(c) {
					$('.close${i.id}').on('click', function(c){
						$('.cart-header${i.id}').fadeOut('slow', function(c){
							$('.cart-header${i.id}').remove();
						});
						});	  
					});
			   </script>
			   <div class="cart-header${i.id}">
				
				<div class="close"><button class="xoaSanPhamGioHang" idProduct = "${i.id}" style="color: white; background-color: red;">X</button></div>
				<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<img src="<c:url value="${i.image}"/>" class="img-responsive" alt=""/>
						</div>
					   <div class="cart-item-info">
						    <h3><a href="single.html">${i.name}</a></h3>
							<ul class="qty">
								<li><p>Số lượng : ${i.totalItem}</p></li>
							</ul>
							<div class="delivery">
								 <p>Tổng tiền : <fmt:formatNumber value="${i.totalPrices}" type="number"   maxFractionDigits = "3"/>  VND</p>
								 <span>Giao hàng từ 2 đến 3 ngày</span>
								 <div class="clearfix"></div>
							</div>								
					   </div>
					   <div class="clearfix"></div>
											
				  </div>
			 </div>
			</c:forEach>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!-- //check out -->
<script type="text/javascript">
$("body").on("click",".xoaSanPhamGioHang",function(){
	  var id =$(this).attr("idProduct");
	  $.ajax({
	      url: '${api_cart}/'+id,
	      type: 'DELETE',
	      success: function (result) {
	    	  if(result=='success'){
	        		swal("Xóa thành công");
	        		location.reload();
	        	}
	      },
	      error: function (error) {
	    	  location.reload();
	      }
	  });
		});
	 		 
</script>
</body>
</html>