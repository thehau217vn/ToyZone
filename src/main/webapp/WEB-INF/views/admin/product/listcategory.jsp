<%@include file="/common/taglib.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<spring:eval expression="@property.getProperty('product.url')" var="url"/>
<spring:eval expression="@property.getProperty('product.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('product.api')" var="api"/>
<spring:eval expression="@property.getProperty('product.find')" var="find"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_product" value="${url}"/>
<c:url var="api_product" value="${api}"/>
<c:url var="edit_product" value="${edit}"/>
<c:url var="find_product" value="${find}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lí sản phẩm</title>
</head>

<body>
	<div class="main-content">
		
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/admin/trang-chu'/>">Trang
								chủ</a></li>
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_product}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Chọn danh mục</a></li>
					</ul>
					<!-- /.breadcrumb -->
					<!-- /.breadcrumb -->
					<div class="nav-search" id="nav-search">
						<form:form action="${find_product}" method="POST" modelAttribute="product" class="form-search">
								<span class="input-icon">
									<form:input  path="name" placeholder="Search ..." class="nav-search-input" id="name nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
						</form:form>
					</div>
					<!-- /.nav-search -->	
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>Tên Danh Mục</th>
													<th>Chọn</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${category.listResult}">
													<tr>
													<td>${item.name}</td>
													<td>
														<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Chi Tiết" href='${url_product}/${item.id}?page=${pageProp}&maxPageItem=${maxPageItemProp}'><i
																class="fa fa-pencil-square-o" aria-hidden="true"></i> </a>
																
														</td>	
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<form action="${url_category}" id="formSubmit" method="get">
											<ul class="pagination" id="pagination"></ul>
											<input type="hidden" value="" id="page" name="page" /> 
											<input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
	
	</div>
	<script>
		var totalPages = ${category.totalPage};
		var currentPage = ${category.page};
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
