<%@include file="/common/taglib.jsp"%>


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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản Lí Sản Phẩm</title>
</head>

<body>
	<div class="main-content" id="main-content">
					
		
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/admin/trang-chu'/>">Trang
								chủ</a></li>
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_product}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Chọn
								 danh mục</a></li>
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_product}/${idCategory}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản Lí
								Sản Phẩm</a></li>
					
					</ul>
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
								  <c:if test="${not empty message}">
									<div class="alert alert-${alert}">
  										${message}
									</div>
						</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm sản phẩm'
												href='${url_product}/${idCategory}${edit}'> <span> <i
													class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
											</a>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
									<div id="data-category" class="table-responsive">
										<table class="table table-bordered" id="table-catgory">
											<thead>
												<tr>
													<th>ID</th>
													<th>Ngày tạo</th>
													<th>Tên</th>
													<th>Số lượng</th>
													<th>Giá</th>
													<th>Giảm Giá</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${product.listResult}">
													<tr>
														<td>${item.id}</td>
														<td>${item.createdDate}</td>
														<td>${item.name}</td>
														<td>${item.count}</td>
														<td><fmt:formatNumber value="${item.price}" type="number"   maxFractionDigits = "3"/></td>
														<td>${item.discount}</td>
														<td>
														<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Cập nhật" href='${url_product}/${idCategory}${edit}${item.id}'><i
																class="fa fa-pencil-square-o" aria-hidden="true"></i> </a>
																
														<button id="btnDelete" idproduct="${item.id}" type="button" 
																class="xoaproduct dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa sản phẩm'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																			</span>
															</button>
														</td>
																
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<form action="${url_product}/${idCategory}" id="formSubmit" method="get">
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
		
		   $("body").on("click",".xoaproduct",function(){
			  var id =$(this).attr("idproduct");
			  swal({
				    title: "Xác nhận xóa",
				    text: "Bạn có chắc chắn muốn xóa hay không",
				    type: "warning",
				    showCancelButton: true,
				    confirmButtonClass: "btn-success",
					  cancelButtonClass: "btn-danger",
				    confirmButtonText: "Xác nhận",
				    cancelButtonText: "Hủy bỏ",
				}).then((result) => {
				    if (result.value) {
				       
				    	deleteProduct(id);
				    } else {
				       
				        window.location.href = "${url_product}/${idCategory}?maxPageItem=${maxPageItemProp}&page=${pageProp}";
				    }
				});
				});
			 		 

		function deleteProduct(data) {
	        $.ajax({
	            url: '${api_product}/'+data,
	            type: 'DELETE',
	            success: function (result) {
	                window.location.href = "${url_product}/${idCategory}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "${url_product}/${idCategory}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=error_system";
	            }
	        });
	    }   
	
	</script>
</body>
</html>
