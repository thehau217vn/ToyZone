<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglib.jsp"%>



<spring:eval expression="@property.getProperty('order.url.giao')" var="urlDagiao"/>
<spring:eval expression="@property.getProperty('order.url.chuagiao')" var="urlchuagiao"/>
<spring:eval expression="@property.getProperty('order.edit')" var="edit"/>
<spring:eval expression="@property.getProperty('order.api')" var="api"/>
<spring:eval expression="@property.getProperty('order.find')" var="find"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>

<c:url var="url_order_dagiao" value="${urlDagiao}"/>
<c:url var="url_order_chuagiao" value="${urlchuagiao}"/>
<c:url var="edit_order" value="${edit}"/>
<c:url var="api_order" value="${api}"/>
<c:url var="find_order" value="${find}"/>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lí đơn hàng</title>
</head>

<body>
	<div class="main-content" id="main-content">
					
		
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value='/admin/trang-chu'/>">Trang
								chủ</a></li>
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${url_order_chuagiao}?page=${pageProp}&maxPageItem=${maxPageItemProp}">Quản
								lí dơn hàng</a></li>
					
					</ul>
					<!-- /.breadcrumb -->
					<div class="nav-search" id="nav-search">
						<form:form action="${find_order}" method="POST" modelAttribute="order" class="form-search">
								<span class="input-icon">
									<form:input path="id" placeholder="Tìm kiếm (số hóa đơn)" class="nav-search-input" id="name nav-search-input" autocomplete="off" />
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
									<div class="space"></div>
								</c:if>
							
							<div class="row">
							<a class="btn btn-success" href="${url_order_dagiao}?page=${pageProp}&maxPageItem=${maxPageItemProp}">
												<i class = "ace-icon fa fa-check"></i>
												Đã Giao
											</a>
							<a class="btn btn-danger" href="${url_order_chuagiao}?page=${pageProp}&maxPageItem=${maxPageItemProp}">
												<i class="ace-icon fa fa-bolt bigger-110"></i> 
												Chưa giao     
											</a>
							</div>
							<div class="space"></div>
							<div class="row">
								<div class="col-xs-12">
									<div id="data-category" class="table-responsive">
										<table class="table table-bordered" id="table-catgory">
											<thead>
												<tr>
													<th>ID</th>
													<th>Ngày đặt</th>
													<th>Người đặt</th>
													<th>Người nhận</th>
													<th>Tổng tiền</th>
													<th>Trạng thái</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${orderDto.listResult}">
													<tr>
														<td>${item.id}</td>
														<td>${item.createdDate}</td>
														<td>${item.userDto.fullName}</td>
														<td>${item.receiver}</td>
														<td><fmt:formatNumber value="${item.totalMoney}" type="number"   maxFractionDigits = "3"/></td>
														<td>${item.status==true ? 'Đã giao':'Chưa giao'}</td>
														
														<td>
														<a class="btn btn-sm btn-primary btn-edit"
																data-toggle="tooltip" title="Cập nhật" href='${edit_order}/${item.id}'><i
																class="fa fa-pencil-square-o" aria-hidden="true"></i> </a>
																
														<button id="btnDelete" idorder="${item.id}" type="button" 
																class="xoaorder dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa đơn hàng'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																			</span>
															</button>
														</td>
																
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<form action="${url_order}" id="formSubmit" method="get">
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
		var totalPages = ${orderDto.totalPage};
		var currentPage = ${orderDto.page};
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
		
		  $("body").on("click",".xoaorder",function(){
			  var id =$(this).attr("idorder");
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
				       
				    	deleteorder(id);
				    } else {
				       
				     
				    }
				});
				});
			 		 

		function deleteorder(data) {
	        $.ajax({
	            url: '${api_order}/'+data,
	            type: 'DELETE',
	            success: function (result) {
            		window.location.href = "${url_order_chuagiao}?maxPageItem=${maxPageItemProp}&page=${pageProp}&message=delete_success";
	            },
	            error: function (error) {
	            }
	        });
	    } 
	
	</script>
</body>
</html>
