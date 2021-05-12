<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglib.jsp"%>
   
    <spring:eval expression="@property.getProperty('product.detail')" var="detail"/>
    <spring:eval expression="@property.getProperty('category.products')" var="url"/>
    <spring:eval expression="@property.getProperty('page.page.product')" var="pageProp"/>
    <spring:eval expression="@property.getProperty('page.maxPageItem.product')" var="maxPageItemProp"/>
<c:url var="productofcate" value="${url}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản Phẩm</title>

</head>
<body>
<div class="product-model">
 	<div class="container">
 		<ol class="breadcrumb">
		  <li><a href="index.html">Trang chủ</a></li>
		  <li class="active">Sản phẩm</li>
		  </ol>
		  <h2>Nội Thất Của Chúng Tôi</h2>	
		  <div class="col-md-9 product-model-sec">
			  <c:forEach  items="${product.listResult}" var="i">
				  <div class="product-grid">
				 
				  			<a href="<c:url value="${url}${idCategory}${detail}${i.id}"></c:url>">
							<div class="more-product"><span> </span></div>						
							<div class="product-img b-link-stripe b-animate-go  thickbox">
								<img src="<c:url value="${i.image}"/>" class="img-responsive" alt="">
								<div class="b-wrapper">
								<h4 class="b-animate b-from-left  b-delay03">							
								<button><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>Chi tiết</button>
								</h4>
								</div>
							</div></a>						
							<div class="product-info simpleCart_shelfItem">
								<div class="product-info-cust prt_name">
									<h4>${i.name}</h4>					
									<c:if test="${i.discount>0}">
										<span class="item_price"><fmt:formatNumber value="${i.price-i.price*(i.discount/100)}" type="number" maxFractionDigits = "3"/> VND</span>
											<div class="ofr">
											  <p class="pric1"><del><fmt:formatNumber value="${i.price}" type="number"   maxFractionDigits = "3"/> VND</del></p>
									          <p class="disc">[${i.discount} % Off]</p>
											</div>
									</c:if>			
									<c:if test="${i.discount<=0}">
										<span class="item_price"><fmt:formatNumber value="${i.price}" type="number"   maxFractionDigits = "3"/></span>
										<div class="ofr">
										  <p class="pric1"><del>0 VND</del></p>
								          <p class="disc">[0 % Off]</p>
										</div>
									</c:if>	
									
									
									
								
									<div class="clearfix"> </div>
								</div>												
								
							</div>
						</div>	
						
			  </c:forEach>
		  		
		  </div>
		  
		 <div class="rsidebar span_1_of_left">
 	 		<section  class="sky-form">
					 <div class="product_right">
						 <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Danh mục</h4>
						 <div class="tab1">
							 <ul class="place">								
								 <li class="sort">Tất cả danh mục</li>
								 <li class="by"><img src="<c:url value = "/templateWeb/images/do.png"></c:url>" alt=""></li>
									<div class="clearfix"> </div>
							  </ul>
							 <div class="single-bottom">		
							 		<c:forEach var="mnCate" items="${menuCate}">
							 			<a href="${productofcate}${mnCate.id}?page=${pageProp}&&maxPageItem=${maxPageItemProp}"><p>${mnCate.name}</p></a>
							 		</c:forEach>				
									
									
						     </div>
					      </div>						  
						 
						  
						  <!--script-->
						<script>
							$(document).ready(function(){
								$(".tab1 .single-bottom").hide();
								
								$(".tab1 ul").click(function(){
									$(".tab1 .single-bottom").slideToggle(300);
								})
								
							});
						</script>
						</div>
						<!-- script -->					 
				 </section>
 	</div>
 	 <form action="${productofcate}${idCategory}" id="formSubmit" method="get">
					<ul class="pagination pagination-lg" id="pagination"></ul>
			
					<input type="hidden" value="" id="page" name="page" /> 
					<input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
		</form>
 	</div>
 	
</div>


<script type="text/javascript">
var totalPages = ${product.totalPage};
var currentPage = ${product.page};
var limit = ${maxPageItemProp};
$(function() {
	window.pagObj = $('#pagination').twbsPagination({
		totalPages : totalPages,
		visiblePages : 10,
		startPage : currentPage,
		onPageClick : function(event, page) {
			if (currentPage != page) {
				$('#maxPageItem').val(limit);
				$('#page').val(page);
				$('#formSubmit').submit();
			}
		}
	});
});


</script>
</body>
</html>