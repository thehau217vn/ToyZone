<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
<spring:eval expression="@property.getProperty('order.api')" var="api"/>
<spring:eval expression="@property.getProperty('order.url.chuagiao')" var="url"/>
<spring:eval expression="@property.getProperty('page.page')" var="pageProp"/>
<spring:eval expression="@property.getProperty('page.maxPageItem')" var="maxPageItemProp"/>
<c:url var="api_order" value="${api}"/>
<c:url var="url_order" value="${url}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="row">
							<div class="col-sm-10 col-sm-offset-1">
								<div class="widget-box transparent">
									<div class="widget-header widget-header-large">
										<h3 class="widget-title grey lighter">
											<i class="ace-icon fa fa-leaf green"></i> Chi Tiết Đơn Hàng
										</h3>

										<div class="widget-toolbar no-border invoice-info">
											<span class="invoice-info-label">Số hóa đơn:</span> <span
												class="red">#${orderDto.id}</span> <br /> <span
												class="invoice-info-label">Ngày đặt:</span> <span
												class="blue">${orderDto.createdDate}</span>
										</div>

										<div class="widget-toolbar hidden-480">
											<a href="#"> <i class="ace-icon fa fa-print"></i>
											</a>
										</div>
									</div>
									<div class="widget-body">
										<div class="widget-main padding-24">
											<div class="row">
												<div class="col-sm-6">
													<div class="row">
														<div
															class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
															<b>Thông Tin Đơn Hàng</b>
														</div>
													</div>

													<div>
														<ul class="list-unstyled spaced">
															<li><i class="ace-icon fa fa-caret-right blue"></i>Người đặt : 
																${orderDto.userDto.fullName}</li>

															<li><i class="ace-icon fa fa-caret-right blue"></i>Người nhận : 
																${orderDto.receiver}</li>

															<li><i class="ace-icon fa fa-caret-right blue"></i>Địa chỉ giao :
																${orderDto.deliveryAddress}</li>

															<li><i class="ace-icon fa fa-caret-right blue"></i>
																Sổ điện thoại : <b class="red">${orderDto.phone}</b></li>

															<li class="divider"></li>

															<li><i class="ace-icon fa fa-caret-right blue"></i>
																Nội dung (nếu có) : ${orderDto.content}</li>
														</ul>
													</div>
												</div>
												<!-- /.col -->
											</div>
											<div class="space"></div>
											<div>
												<table class="table table-striped table-bordered">
													<thead>
														<tr>
															<th class="center">ID</th>
															<th>Tên Sản Phẩm</th>
															<th class="hidden-xs">Số lượng</th>
															<th class="hidden-480">Giá</th>
															<th class="hidden-480">Giảm giá</th>
															<th>Tổng tiền</th>
														</tr>
													</thead>

													<tbody>

														<c:forEach items="${productOrderDtos}" var="item"
															varStatus="i">
															<tr>
																<td class="center">${item.idProduct}</td>

																<td><a href="#">${productDtos[i.index].name}</a></td>
																<td class="hidden-xs">${item.count}</td>
																<td class="hidden-480"><fmt:formatNumber value="${productDtos[i.index].price}" type="number"   maxFractionDigits = "3"/> VND</td>
																<td>${productDtos[i.index].discount} %</td>
																<td><fmt:formatNumber value="${item.price}" type="number"   maxFractionDigits = "3"/> VND</td>
															</tr>


														</c:forEach>



													</tbody>
												</table>
											</div>
											<div class="space"></div>
											<div class="row">
												<div class="col-sm-5 pull-right">
													<h4 class="pull-right">
														Tổng tiền đơn hàng : <span class="red"><fmt:formatNumber value="${totalMoney}" type="number"   maxFractionDigits = "3"/> VND</span>
													</h4>
												</div>
												
											</div>
											<div class="space"></div>
											<c:if test="${orderDto.status==false}">
												<button class="btn btn-lg btn-success" id="btngiaohang">
													<i class = "ace-icon fa fa-check"></i>
													Giao hàng
												</button>
											</c:if>
											
											<button class="btn btn-lg btn-danger" id="btnquaylai">
												<i class="ace-icon fa fa-bolt bigger-110"></i> Quay lại     
											</button>
											
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	 $('#btngiaohang').click(function (e) {
	        e.preventDefault();
	        $.ajax({
	            url: '${api_order}/'+${orderDto.id},
	            type: 'PUT',
	            contentType: 'application/json',
	            success: function (result) {
	            	if(result=='success'){
	            		swal("Xử lý đơn hàng thành công");
	            		 location.reload(true);
	            	}
	            },
	          
	        });
	    });
	 $('#btnquaylai').click(function (e) {
		 e.preventDefault();
		 window.location.href = "${url_order}?maxPageItem=${maxPageItemProp}&page=${pageProp}"
	 });
	</script>
</body>
</html>