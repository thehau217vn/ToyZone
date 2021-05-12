<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
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
															<b>Báo Cáo Doanh Thu</b>
														</div>
													</div>

													<div>
														<ul class="list-unstyled spaced">
															<li><i class="ace-icon fa fa-caret-right blue"></i>Từ ngày : 
																${tuNgay}</li>

															<li><i class="ace-icon fa fa-caret-right blue"></i>Đến ngày : 
																${denNgay}</li>
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
															<th class="hidden-480">Tồn</th>
															<th class="hidden-xs">Giá (VND)</th>
															<th>Tống bán</th>
															<th class="hidden-480">Tổng tiền (VND)</th>
														</tr>
													</thead>

													<tbody>

														<c:forEach items="${list}" var="i">
															<tr>
																<td>${i[0]}</td>
																<td class="hidden-xs">${i[1]}</td>
																<td>${i[3]}</td>
																<td class="hidden-480"><fmt:formatNumber value="${i[2]}" type="number"   maxFractionDigits = "3"/></td>
																<td>${i[5]}</td>
																<td><fmt:formatNumber value="${i[4]}" type="number"   maxFractionDigits = "3"/> VND</td>
																
															</tr>
														</c:forEach>



													</tbody>
												</table>
											</div>
											<div class="space"></div>
											<div class="row">
												<div class="col-sm-5 pull-right">
													<h4 class="pull-right">
														Tổng doanh thu : <span class="red"><fmt:formatNumber value="${totalMoney}" type="number"   maxFractionDigits = "3"/> VND</span>
													</h4>
												</div>
												
											</div>
											<div class="space"></div>
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
</body>
</html>