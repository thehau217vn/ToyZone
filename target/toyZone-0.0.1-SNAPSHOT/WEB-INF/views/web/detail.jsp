<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>


 <spring:eval expression="@property.getProperty('cart.api')" var="addCart"/>
  <spring:eval expression="@property.getProperty('product.detail')" var="detail"/>
    <spring:eval expression="@property.getProperty('category.products')" var="url"/>
    <spring:eval expression="@property.getProperty('page.page.product')" var="pageProp"/>
    <spring:eval expression="@property.getProperty('page.maxPageItem.product')" var="maxPageItemProp"/>
<c:url var="productofcate" value="${url}"/>
<c:url var="api_cart" value="${addCart}"/>
<html>

<head>
<meta charset="UTF-8">
<title>Chi Tiết Sản Phẩm</title>

</head>
<body>
<div class="product">
	<div class="container">
		 <div class="product-price1">
			 <div class="top-sing">
					  <div class="col-md-7 single-top">	
						 <div class="flexslider">
								  <ul class="slides">
								  	<c:url value="${product.image}" var="image"/>
									<li data-thumb="${image}">
									 	<div class="thumb-image"> <img src="${image}" data-imagezoom="true" class="img-responsive" alt=""/> </div>
									</li>
									
								  </ul>
							</div>					 					 
						 
	
					 </div>	
				     <div class="col-md-5 single-top-in simpleCart_shelfItem">
						  <div class="single-para ">
							 <h4>${product.name}</h4>	
							 <c:if test="${product.discount >0}">
							 	<h5 class="item_price">Giá : <fmt:formatNumber value="${product.price - product.price*(product.discount/100)}" type="number"   maxFractionDigits = "3"/>  VND [Discount : ${product.discount}%]</h5>
							 </c:if>
							 <c:if test="${product.discount <= 0}">
							 		<h5 class="item_price">Giá : <fmt:formatNumber value="${product.price}" type="number"   maxFractionDigits = "3"/>  VND</h5>
							 </c:if>							
							<c:if test="${product.count>0}">
								<h5 class="item_price">Số lượng : ${product.count}</h5>		
								${product.content}
								
							<div class="check">
							 	<form id="themGioHang" class="navbar-form">
							 		 <div class="form-group">
										<input type="text" id="count" name="count" class="form-control" placeholder="Nhập số lượng"/>
									  </div>
										 <input type="hidden" id="idProduct" name="idProduct" value="${product.id}"/>
										<input type="hidden" id="price" name="price" value="${product.price}"/>
										<div class="form-group"><input type="button" id="btnthemGiohang" value="Thêm giỏ hàng" class="lablel label-info"></div>
							 	</form>
							 
							 </div>			
							</c:if>
							<c:if test="${product.count<=0}">
								<h5>Số lượng : Hết Hàng</h5>		
								${product.content}
							</c:if>					
									
						 </div>
						 
						
					 </div>
					 <div class="clearfix"> </div>
				 </div>
		 </div>
		 <div class="bottom-prdt">
		 <h3>Sản Phẩm Cùng Loại</h3>
			<div class="items-sec">
			<c:forEach var="i" items="${product.listResult}">
				<div class="col-md-3 feature-grid">
					 <a href="<c:url value="${url}${idCategory}${detail}${i.id}"/>"><img src="<c:url value="${i.image}"/>" />	
						 <div class="arrival-info">
						  <h4>${i.name}</h4>
						 <c:if test="${i.discount <= 0}">
							 <p><fmt:formatNumber value="${i.price}" type="number" maxFractionDigits = "3"/> VND</p>
							 <span class="pric1"><del>0 VND</del></span>
							 <span class="disc">[0% Off]</span>
						 </c:if>
							<c:if test="${i.discount >0}">
							 <p><fmt:formatNumber value="${i.price-i.price*(i.discount/100)}" type="number" maxFractionDigits = "3"/> VND VND</p>
							 <span class="pric1"><del><fmt:formatNumber value="${i.price}" type="number" maxFractionDigits = "3"/> VND</del></span>
							 <span class="disc">[${i.price}% Off]</span>
						 </c:if>
						 </div>
						 <div class="viw">
							<a href="<c:url value="${url}${idCategory}${detail}${i.id}"/>"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Xem</a>
						 </div>
					  </a>
				 </div>
				 </c:forEach>
			</div>
			 
		 </div>
	</div>
	
</div>



<script type="text/javascript">

$('#btnthemGiohang').click(function (e) {
    e.preventDefault();
    console.log("vao day");
    var data = {};
    var formData = $('#themGioHang').serializeArray();
    $.each(formData, function (i, v) {
        data[""+v.name+""] = v.value;
    });
    console.log(data);
    $.ajax({
        url: '${api_cart}',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
        	if(result=='success'){
        		swal("Thêm giỏ hàng thành công");
        		
        	}else{
        		swal("Số lượng của sản phẩm không phù hợp");
        		
        	}
        	//location.reload();
        },
    });
    
});
</script>
</body>
</html>