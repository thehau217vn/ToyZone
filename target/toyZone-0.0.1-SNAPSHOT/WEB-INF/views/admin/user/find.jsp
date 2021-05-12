<%@include file="/common/taglib.jsp"%>


<spring:eval expression="@property.getProperty('user.url')" var="url"/>
<spring:eval expression="@property.getProperty('user.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('user.api')" var="api"/>
<spring:eval expression="@property.getProperty('user.find')" var="find"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_user" value="${url}"/>
<c:url var="api_user" value="${api}"/>
<c:url var="edit_user" value="${edit}"/>
<c:url var="find_user" value="${find}"/>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lí danh mục</title>
</head>
<body>
<div class="main-content">
		
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/admin/trang-chu'/>">Trang
								chủ</a></li>
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_user}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản
								lí người dùng</a></li>
					</ul>
					<div class="nav-search" id="nav-search">
						<form:form action="${find_user}" method="POST" modelAttribute="user" class="form-search">
								<span class="input-icon">
									<form:input   path="fullName" placeholder="Search ..." class="nav-search-input" id="name nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
						</form:form>
					</div>
					<!-- /.breadcrumb -->
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
												data-toggle="tooltip" title='Thêm khách hàng'
												href='${edit_user}'> <span> <i
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
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>Ngày</th>
													<th>Họ tên</th>
													<th>Email</th>
													<th>Số điện thoại</th>
													<th>Account</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${user.listResult}">
													<tr>
														<td>${item.createdDate}</td>
														<td>${item.fullName}</td>
														<td>${item.email}</td>
														<td>${item.phone}</td>
														<td>${item.account}</td>
														<td>
														<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Cập nhật" href='${edit_user}/${item.id}'><i
																class="fa fa-pencil-square-o" aria-hidden="true"></i> </a>
																
														<button id="btnDelete" iduser="${item.id}" type="button" 
																class="xoauser dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa khách hàng'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																			</span>
															</button>
														</td>
																
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
	</div>
	<script type="text/javascript">
		
		  $("body").on("click",".xoauser",function(){
			  var id =$(this).attr("iduser");
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
				       
				    	deleteUSer(id);
				    } else {
				       
				        window.location.href = "${url_user}?maxPageItem=${maxPageItemProp}&page=${pageProp}";
				    }
				});
				});
			 		 

		function deleteUSer(data) {
	        $.ajax({
	            url: '${api_user}/'+data,
	            type: 'DELETE',
	            success: function (result) {
	                window.location.href = "${url_user}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "${url_user}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=error_system";
	            }
	        });
	    } 
	
	</script>
	
	
	</body>
	</html>