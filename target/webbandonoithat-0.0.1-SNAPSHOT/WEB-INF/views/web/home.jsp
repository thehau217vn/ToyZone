<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp" %>
<spring:eval expression="@property.getProperty('product.detail')"
             var="detail"/>
<spring:eval expression="@property.getProperty('category.products')"
             var="url"/>
<c:url var="productofcate" value="${url}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="offers">
    <div class="container">
        <h3>Sản Phẩm Mới</h3>
    </div>
</div>

<div class="container">
    <div class="items-sec">
        <c:forEach items="${products}" var="i">
            <div class="col-md-3 feature-grid">
                <a href="<c:url value="${url}${i.idCategory}${detail}${i.id}"/>"><img
                        src="<c:url value="${i.image}"/>"/>
                    <div class="arrival-info">
                        <h4>${i.name}</h4>
                        <c:if test="${i.discount>0}">
                            <p><fmt:formatNumber value="${i.price-i.price*(i.discount/100)}" type="number"
                                                 maxFractionDigits="3"/> VND</p>
                            <span class="pric1"><del><fmt:formatNumber value="${i.price}" type="number"
                                                                       maxFractionDigits="3"/> VND</del></span> <span
                                class="disc">[${i.discount} % Off]</span>
                        </c:if>
                        <c:if test="${i.discount <= 0}">
                            <p><fmt:formatNumber value="${i.price}" type="number" maxFractionDigits="3"/> VND</p>
                            <span class="pric1"><del>0 VND</del></span><span
                                class="disc">[0.0 % Off]</span>
                        </c:if>
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
<div class="offers">
    <div class="container">
        <h3>Sản Phẩm Khuyến Mãi</h3>
    </div>
</div>
<div class="container">
    <div class="items-sec">
        <c:forEach items="${listKm}" var="item">
            <div class="col-md-3 feature-grid">
                <a href="<c:url value="${url}${item.idCategory}${detail}${item.id}"/>"><img
                        src="<c:url value="${item.image}"/>"/>
                    <div class="arrival-info">
                        <h4>${item.name}</h4>
                        <c:if test="${item.discount>0}">
                            <p><fmt:formatNumber value="${item.price-item.price*(item.discount/100)}" type="number"
                                                 maxFractionDigits="3"/> VND</p>
                            <span class="pric1"><del><fmt:formatNumber value="${item.price}" type="number"
                                                                       maxFractionDigits="3"/> VND</del></span> <span
                                class="disc">[${item.discount} % Off]</span>
                        </c:if>
                        <c:if test="${item.discount <= 0}">
                            <p><fmt:formatNumber value="${item.price}" type="number" maxFractionDigits="3"/> VND</p>
                            <span class="pric1"><del>0 VND</del></span><span
                                class="disc">[0.0 % Off]</span>
                        </c:if>
                    </div>
                    <div class="viw">
                        <a href="<c:url value="${url}${item.idCategory}${detail}${item.id}"/>"><span
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
<div class="offers">
    <div class="container">
        <h3>Sản Phẩm Hot</h3>
    </div>
</div>
<div class="container">
    <div class="items-sec">
        <c:forEach items="${listhot}" var="item">
            <div class="col-md-3 feature-grid">
                <a href="<c:url value="${url}${item.idCategory}${detail}${item.id}"/>"><img
                        src="<c:url value="${item.image}"/>"/>
                    <div class="arrival-info">
                        <h4>${item.name}</h4>
                        <c:if test="${item.discount>0}">
                            <p><fmt:formatNumber value="${item.price-item.price*(item.discount/100)}" type="number"
                                                 maxFractionDigits="3"/> VND</p>
                            <span class="pric1"><del><fmt:formatNumber value="${item.price}" type="number"
                                                                       maxFractionDigits="3"/> VND</del></span> <span
                                class="disc">[${item.discount} % Off]</span>
                        </c:if>
                        <c:if test="${item.discount <= 0}">
                            <p><fmt:formatNumber value="${item.price}" type="number" maxFractionDigits="3"/> VND</p>
                            <span class="pric1"><del>0 VND</del></span><span
                                class="disc">[0.0 % Off]</span>
                        </c:if>
                    </div>
                    <div class="viw">
                        <a href="<c:url value="${url}${item.idCategory}${detail}${item.id}"/>"><span
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