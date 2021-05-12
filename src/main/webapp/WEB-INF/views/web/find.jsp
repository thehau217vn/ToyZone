<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
<spring:eval expression="@property.getProperty('product.detail')"
	var="detail" />
<spring:eval expression="@property.getProperty('category.products')"
	var="url" />
<html>
<head>
<meta charset="UTF-8">
<title>Tìm kiếm</title>
</head>
<body>
<div class="offers">
		<div class="container">
			<h3>Tìm kiếm sản phẩm</h3>
		</div>
	</div>

		<div class="container">
			<div class="items-sec">
				<c:forEach items="${products}" var="i">
					<div class="col-md-3 feature-grid">
						<a href="<c:url value="${url}${i.idCategory}${detail}${i.id}"/>"><img
							src="<c:url value="${i.image}"/>" />
							<div class="arrival-info">
								<h4>${i.name}</h4>
								<p>${i.price}VND</p>
								<span class="pric1"><del>${i.price} VND</del></span> <span
									class="disc">[${i.discount}% Off]</span>
							</div>
							<div class="viw">
								<a href="<c:url value="${url}${i.idCategory}${detail}${i.id}"/>"><span
									class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Chi
									Tiết</a>
							</div> 
							</a>
					</div>

				</c:forEach>


				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	
</body>
</html>