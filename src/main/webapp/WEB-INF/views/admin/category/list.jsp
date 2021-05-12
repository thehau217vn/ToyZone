<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglib.jsp"%>



<spring:eval expression="@property.getProperty('category.url')" var="url"/>
<spring:eval expression="@property.getProperty('category.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('category.api')" var="api"/>
<spring:eval expression="@property.getProperty('category.find')" var="find"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_category" value="${url}"/>
<c:url var="api_category" value="${api}"/>
<c:url var="edit_category" value="${edit}"/>
<c:url var="find_category" value="${find}"/>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lí danh mục</title>
</head>

<body>
	<div class="main-content" id="main-content">
					
		
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/admin/trang-chu'/>">Trang
								chủ</a></li>
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_category}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản
								lí danh mục</a></li>
					
					</ul>
					<!-- /.breadcrumb -->
					<div class="nav-search" id="nav-search">
						<form:form action="${find_category}" method="POST" modelAttribute="category" class="form-search">
								<span class="input-icon">
									<form:input   path="name" placeholder="Search ..." class="nav-search-input" id="name nav-search-input" autocomplete="off" />
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
												data-toggle="tooltip" title='Thêm danh mục'
												href='${edit_category}'> <span> <i
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
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${category.listResult}">
													<tr>
														<td>${item.id}</td>
														<td>${item.createdDate}</td>
														<td>${item.name}</td>
														<td>
														<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Cập nhật" href='${edit_category}${item.id}'><i
																class="fa fa-pencil-square-o" aria-hidden="true"></i> </a>
																
														<button id="btnDelete" idcategory="${item.id}" type="button" 
																class="xoacategory dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																			</span>
															</button>
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
	<script type="text/javascript">
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
		
		  $("body").on("click",".xoacategory",function(){
			  var id =$(this).attr("idcategory");
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
				       
				        deleteCategory(id);
				    } else {
				       
				        window.location.href = "${url_category}?maxPageItem=${maxPageItemProp}&page=${pageProp}";
				    }
				});
				});
			 		 

		function deleteCategory(data) {
	        $.ajax({
	            url: '${api_category}'+data,
	            type: 'DELETE',
	            success: function (result) {
	                window.location.href = "${url_category}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "${url_category}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=error_system";
	            }
	        });
	    } 
	
	</script>
</body>
</html>
